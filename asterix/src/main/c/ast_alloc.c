#include <stdlib.h>
#include <inttypes.h>
#include <errno.h>

#include "ast_private.h"

typedef struct ast__allocator_s
{
	ast_malloc_func local_malloc;
	ast_realloc_func local_realloc;
	ast_calloc_func local_calloc;
	ast_free_func local_free;
} ast__allocator_t;

static ast__allocator_t ast__allocator = {
	malloc,
	realloc,
	calloc,
	free,
};

int ast_replace_allocator(ast_malloc_func malloc_func,
						  ast_realloc_func realloc_func,
						  ast_calloc_func calloc_func,
						  ast_free_func free_func)
{

	if (NULL == malloc_func || NULL == realloc_func ||
		NULL == calloc_func || NULL == free_func)
	{
		return -EINVAL;
	}

	ast__allocator.local_malloc = malloc_func;
	ast__allocator.local_realloc = realloc_func;
	ast__allocator.local_calloc = calloc_func;
	ast__allocator.local_free = free_func;

	return 0;
}

void *ast__malloc(size_t size)
{
	if (size > 0)
	{
		return ast__allocator.local_malloc(size);
	}

	return NULL;
}

void ast__free(void *ptr)
{
	/* Expects that free() does not clobber errno.  The system allocator
	 * honors that assumption but custom allocators may not be so careful.
	 */
	const int saved_errno = errno;
	ast__allocator.local_free(ptr);
	errno = saved_errno;
}

void *ast__calloc(size_t count, size_t size)
{
	return ast__allocator.local_calloc(count, size);
}

void *ast__realloc(void *ptr, size_t size)
{
	if (size > 0)
	{
		return ast__allocator.local_realloc(ptr, size);
	}

	ast__free(ptr);
	return NULL;
}

void *ast__reallocf(void *ptr, size_t size)
{
	void *newptr;

	newptr = ast__realloc(ptr, size);
	if (newptr == NULL)
	{
		if (size > 0)
		{
			ast__free(ptr);
		}
	}

	return newptr;
}

int ast__alloc(void **ptr, size_t size)
{
	*ptr = ast__malloc(size);
	if (NULL == *ptr)
	{
		AST__ERROR_SET(ENOMEM, "failed to allocate %" PRIu64 " bytes", (uint64_t)size);
		return -1;
	}

	memset(*ptr, 0, size);

	return 0;
}

int ast__alloc_no_err(void **ptr, size_t size)
{
	*ptr = ast__malloc(size);

	if (NULL == *ptr)
	{
		return -1;
	}

	memset(*ptr, 0, size);

	return 0;
}

char *ast__strdup(const char *s)
{
	const size_t len = strlen(s) + 1;
	char *m = ast__malloc(len);
	if (m == NULL)
	{
		return NULL;
	}

	return memcpy(m, s, len);
}

char *ast__strndup(const char *s, size_t n)
{
	size_t len = strlen(s);
	if (n < len)
	{
		len = n;
	}

	char *m = ast__malloc(len + 1);
	if (m == NULL)
	{
		return NULL;
	}

	m[len] = '\0';
	return memcpy(m, s, len);
}
