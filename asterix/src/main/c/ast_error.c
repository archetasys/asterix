#if defined(__linux__)
#define _POSIX_C_SOURCE 200112L
#endif

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <memory.h>
#include "ast_private.h"

#define AST__ERROR_TRAILER "...\n"
#define AST__ERROR_DESCRIPTION_UNAVAILABLE "<Unable to get error description>";
#define AST__ERROR_MAX_TOTAL_LENGTH (8192)
#define AST__ERROR_BUFFER_SIZE (1024)

#if defined(AST_COMPILER_GCC)
static const char *ast__error_strerror_r(const int errcode, char *buffer, const size_t length)
{
	const int result = strerror_r(errcode, buffer, length);
	if (result < 0)
	{
		return AST__ERROR_DESCRIPTION_UNAVAILABLE;
	}

	return buffer;
}

#elif defined(AST_COMPILER_MSVC)
#include <windows.h>

static const char *ast__error_strerror_r(const int errcode, char *buffer, const size_t length)
{
	const errno_t result = strerror_s(buffer, length, errcode);

	if (0 != result)
	{
		return AST__ERROR_DESCRIPTION_UNAVAILABLE;
	}
	else
	{
		for (int i = (int)result; i > -1; i--)
		{
			if ('\0' == buffer[i] || isspace(buffer[i]))
			{
				buffer[i] = '\0';
			}
			else
			{
				break;
			}
		}
	}

	return buffer;
}

#else
#error Unsupported platform!
#endif

static AST__INIT_ONCE ast__error_is_initialized = AST__INIT_ONCE_VALUE;

#if defined(AST_COMPILER_MSVC)
static pthread_key_t ast__error_key = TLS_OUT_OF_INDEXES;
#else
static pthread_key_t ast__error_key;
#endif

static void ast__error_initialize_per_thread()
{
	if (ast__thread_key_create(&ast__error_key, free))
	{
		fprintf(stderr, "could not create per thread error key, exiting.\n");
		exit(EXIT_FAILURE);
	}
}

static void ast__error_initialize()
{
#if defined(AST_COMPILER_MSVC)
	if (ast__error_key != TLS_OUT_OF_INDEXES)
	{
		return;
	}
#endif

	ast__thread_once(&ast__error_is_initialized, ast__error_initialize_per_thread);
}

static ast__error_per_thread_t *ast__error_get_required_state()
{
	ast__error_initialize();

	ast__error_per_thread_t *error_state = ast__thread_get_specific(ast__error_key);

	if (NULL == error_state)
	{
		if (ast__alloc_no_err((void **)&error_state, sizeof(ast__error_per_thread_t)) < 0)
		{
			fprintf(stderr, "could not create per thread error state, exiting.\n");
			exit(EXIT_FAILURE);
		}

		if (ast__thread_set_specific(ast__error_key, error_state))
		{
			fprintf(stderr, "could not associate per thread error key, exiting.\n");
			exit(EXIT_FAILURE);
		}
	}

	return error_state;
}

static void ast__error_vprintf(
	ast__error_per_thread_t *error_state,
	const char *format,
	va_list args)
{
	if (error_state->offset >= sizeof(error_state->errmsg))
	{
		return;
	}

	const int result = vsnprintf(&error_state->errmsg[(int)error_state->offset],
								 sizeof(error_state->errmsg) - error_state->offset,
								 format, args);

	if (result < 0)
	{
		fprintf(stderr, "Failed to update err_msg: %d\n", result);
	}

	error_state->offset += result;
}

static void ast__error_printf(ast__error_per_thread_t *error_state, const char *format, ...)
{
	va_list args;
	va_start(args, format);
	ast__error_vprintf(error_state, format, args);
	va_end(args);
}

static void ast__error_update_entry(
	ast__error_per_thread_t *error_state,
	const char *function,
	const char *filename,
	const int line_number,
	const char *format,
	va_list args)
{
	ast__error_printf(error_state, "[%s, %s:%d] ", function, filename, line_number);
	ast__error_vprintf(error_state, format, args);
	ast__error_printf(error_state, "%s", "\n");
	strcpy(error_state->errmsg + (sizeof(error_state->errmsg) - (strlen(AST__ERROR_TRAILER) + 2)), AST__ERROR_TRAILER);
}

void ast__error_set_errno(const int errcode)
{
	errno = errcode;
#if defined(AST_COMPILER_MSVC)
	switch (errcode)
	{
	case 0:
		SetLastError(ERROR_SUCCESS);
		break;

	case EINVAL:
		SetLastError(ERROR_BAD_ARGUMENTS);
		break;

	case ENOMEM:
		SetLastError(ERROR_OUTOFMEMORY);
		break;

	default:
		break;
	}
#endif
}

void ast__error_set(
	const int errcode, const char *function, const char *filename,
	const int line_number, const char *format, ...)
{
	ast__error_per_thread_t *error_state = ast__error_get_required_state();

	error_state->errcode = errcode;
	ast__error_set_errno(errcode);
	error_state->offset = 0;

	char err_buf[AST__ERROR_BUFFER_SIZE] = {0};
	const char *system_err_message;
	const int error_code = ast_error_code();
	if (error_code <= 0)
	{
		system_err_message = ast_error_code_str(-error_code);
	}
	else
	{
		system_err_message = ast__error_strerror_r(error_code, &err_buf[0], AST__ERROR_BUFFER_SIZE);
	}

	const char *err_str = system_err_message;
	ast__error_printf(error_state, "(%d) %s\n", error_code, err_str);
	va_list args;
	va_start(args, format);
	ast__error_update_entry(error_state, function, filename, line_number, format, args);
	va_end(args);
}

#if defined(AST_COMPILER_MSVC)

bool ast__error_dll_process_attach()
{
	if (ast__error_key != TLS_OUT_OF_INDEXES)
	{
		return false;
	}

	ast__error_key = TlsAlloc();

	return ast__error_key != TLS_OUT_OF_INDEXES;
}

void ast__error_dll_thread_detach()
{
	if (ast__error_key == TLS_OUT_OF_INDEXES)
	{
		return;
	}

	ast__error_per_thread_t *error_state = ast__thread_get_specific(ast__error_key);

	if (NULL != error_state)
	{
		ast__free(error_state);
		ast__thread_set_specific(ast__error_key, NULL);
	}
}

void ast__error_dll_process_detach()
{
	if (ast__error_key == TLS_OUT_OF_INDEXES)
	{
		return;
	}

	ast__error_dll_thread_detach();

	ast__thread_key_delete(ast__error_key);
	ast__error_key = TLS_OUT_OF_INDEXES;
}

void ast__error_set_windows(
	const int errcode, const char *function,
	const char *filename, const int line_number, const char *format, ...)
{
	ast__error_per_thread_t *error_state = ast__error_get_required_state();

	error_state->errcode = errcode;
	error_state->offset = 0;

	char err_buf[1024] = {0};
	const char *system_err_message;
	const int error_code = ast_error_code();

	if (error_code <= 0)
	{
		system_err_message = ast_error_code_str(-error_code);
	}
	else
	{
		DWORD num_chars = FormatMessage(
			FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
			NULL,
			error_code,
			MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
			(LPTSTR)err_buf,
			1024,
			NULL);

		if (0 == num_chars)
		{
			system_err_message = "<error text unavailable>";
		}
		else
		{
			for (int i = (int)num_chars; i > -1; i--)
			{
				if ('\0' == err_buf[i] || isspace(err_buf[i]))
				{
					err_buf[i] = '\0';
				}
				else
				{
					break;
				}
			}

			system_err_message = err_buf;
		}
	}

	ast__error_printf(error_state, "(%d) %s\n", error_code, system_err_message);
	va_list args;
	va_start(args, format);
	ast__error_update_entry(error_state, function, filename, line_number, format, args);
	va_end(args);
}

#endif

void ast__error_append(const char *function, const char *filename, const int line_number, const char *format, ...)
{
	ast__error_per_thread_t *error_state = ast__error_get_required_state();
	va_list args;
	va_start(args, format);
	ast__error_update_entry(error_state, function, filename, line_number, format, args);
	va_end(args);
}

int ast_error_code()
{
	ast__error_initialize();

	const ast__error_per_thread_t *error_state = ast__thread_get_specific(ast__error_key);
	int result = 0;

	if (NULL != error_state)
	{
		result = error_state->errcode;
	}

	return result;
}

const char *ast_error_message()
{
	ast__error_initialize();

	const ast__error_per_thread_t *error_state = ast__thread_get_specific(ast__error_key);
	if (NULL != error_state)
	{
		return error_state->errmsg;
	}
	else
	{
		return "no error";
	}
}

const char *ast_error_code_str(const int errcode)
{
	switch (errcode)
	{
	case AST__ERROR_CODE_UNUSED:
	case AST__ERROR_CODE_GENERIC_ERROR:
		return "generic error, see message";

	default:
		return "unknown error code";
	}
}

void ast_error_clear()
{
	ast__error_per_thread_t *error_state = ast__error_get_required_state();

	ast__error_set_errno(0);
	error_state->errcode = 0;
	strcpy(error_state->errmsg, "no error");
}
