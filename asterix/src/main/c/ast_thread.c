#if defined(__linux__)
#define _BSD_SOURCE
#define _GNU_SOURCE
#endif

#include "ast_private.h"

#if !defined(_WIN32)
#include <unistd.h>
#else
#include <windows.h>
#include <mmsystem.h>
#pragma comment(lib, "winmm.lib")

struct ast__thread_s
{
	HANDLE handle;
	void *(*callback)(void *);
	void *arg0;
	void *result;
};

#endif

#if defined(AST_COMPILER_GCC)

void ast__thread_set_name(const char *role_name)
{
#if defined(Darwin)
	pthread_setname_np(role_name);
#else
	pthread_setname_np(pthread_self(), role_name);
#endif
}

#elif defined(AST_COMPILER_MSVC)

static BOOL WINAPI ast__thread_once_callback(PINIT_ONCE init_once, void (*callback)(void), void **context)
{
	callback();
	return TRUE;
}

void ast__thread_once(AST_INIT_ONCE *s_init_once, void *callback)
{
	InitOnceExecuteOnce((PINIT_ONCE)s_init_once, (PINIT_ONCE_FN)ast__thread_once_callback, callback, NULL);
}

int ast__mutex_init(ast__mutex_t *mutex, void *attr)
{
	*mutex = CreateMutexA(NULL, FALSE, NULL);
	return *mutex ? 0 : -1;
}

int ast__mutex_lock(ast__mutex_t *mutex)
{
	return WaitForSingleObject(*mutex, INFINITE) == WAIT_OBJECT_0 ? 0 : EINVAL;
}

int ast__mutex_unlock(ast__mutex_t *mutex)
{
	return ReleaseMutex(*mutex) ? 0 : EINVAL;
}

int ast__mutex_destroy(ast__mutex_t *mutex)
{
	if (*mutex)
	{
		CloseHandle(*mutex);
		*mutex = 0;
		return 0;
	}

	return EINVAL;
}

int ast__thread_attr_init(pthread_attr_t *attr)
{
	return 0;
}

static DWORD WINAPI ast__thread_proc(LPVOID parameter)
{
	ast__thread_t *thread = (ast__thread_t *)parameter;
	(*thread)->result = (*thread)->callback((*thread)->arg0);

	return 0;
}

int ast__thread_create(ast__thread_t *thread_ptr, void *attr, void *(*callback)(void *), void *arg0)
{
	if (NULL == thread_ptr)
	{
		return -1;
	}

	if (aeron_alloc((void **)thread_ptr, sizeof(struct ast__thread_stct)) < 0)
	{
		return -1;
	}

	(*thread_ptr)->callback = callback;
	(*thread_ptr)->arg0 = arg0;
	DWORD id;

	(*thread_ptr)->handle = CreateThread(
		NULL,			   // default security attributes
		0,				   // use default stack size
		ast__thread_proc,  // thread function name
		thread_ptr,		   // argument to thread function
		0,				   // use default creation flags
		&id);			   // returns the thread identifier

	if (!(*thread_ptr)->handle)
	{
		aeron_free(*thread_ptr);
		return -1;
	}

	return 0;
}

void ast__thread_set_name(const char *role_name)
{
	size_t wchar_count = mbstowcs(NULL, role_name, 0);
	wchar_t *buf;
	if (aeron_alloc((void **)&buf, sizeof(wchar_t) * (wchar_count + 1)) < 0) // value-initialize to 0 (see below)
	{
		return;
	}

	mbstowcs(buf, role_name, wchar_count + 1);
	SetThreadDescription(GetCurrentThread(), buf);

	aeron_free(buf);
}

int ast__thread_join(ast__thread_t thread, void **value_ptr)
{
	if (!thread)
	{
		return EINVAL;
	}

	int result = 0;
	if (thread->handle)
	{
		WaitForSingleObject(thread->handle, INFINITE);
		CloseHandle(thread->handle);
		if (value_ptr)
		{
			*value_ptr = thread->result;
		}
	}
	else
	{
		result = EINVAL;
	}

	aeron_free(thread);

	return result;
}

int ast__thread_key_create(pthread_key_t *key_ptr, void (*destr_func)(void *))
{
	DWORD dkey = TlsAlloc();
	if (dkey != TLS_OUT_OF_INDEXES)
	{
		*key_ptr = dkey;
		return 0;
	}
	else
	{
		return EAGAIN;
	}
}

int ast__thread_key_delete(pthread_key_t key)
{
	if (TlsFree(key))
	{
		return 0;
	}
	else
	{
		return EINVAL;
	}
}

int ast__thread_set_specific(pthread_key_t key, const void *pointer)
{
	if (TlsSetValue(key, (LPVOID)pointer))
	{
		return 0;
	}
	else
	{
		return EINVAL;
	}
}

void *ast__thread_get_specific(pthread_key_t key)
{
	return TlsGetValue(key);
}

int sched_yield(void)
{
	SwitchToThread();
	return 0;
}

#else
#error Unsupported platform!
#endif

// sched

#if defined(AST_COMPILER_GCC)

#include <sched.h>

void proc_yield()
{
#if !defined(AST_CPU_ARM)
	__asm__ volatile("pause\n"
					 :
					 :
					 : "memory");
#endif
}

#elif defined(AST_COMPILER_MSVC)

#else
#error Unsupported platform!
#endif
