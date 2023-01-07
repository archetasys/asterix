#ifndef __ASTERIX_H__
#define __ASTERIX_H__

#ifdef __cplusplus
extern "C"
{
#endif

#include <stdint.h>
#include <stddef.h>

const char *ast_version_full();
int ast_version_major();
int ast_version_minor();
int ast_version_patch();

typedef void *(*ast_malloc_func)(size_t size);
typedef void *(*ast_realloc_func)(void *ptr, size_t size);
typedef void *(*ast_calloc_func)(size_t count, size_t size);
typedef void (*ast_free_func)(void *ptr);

int ast_replace_allocator(ast_malloc_func malloc_func,
						  ast_realloc_func realloc_func,
						  ast_calloc_func calloc_func,
						  ast_free_func free_funcioc);

int ast_error_code();
const char *ast_error_message();
const char *ast_error_code_str(int errcode);
void ast_error_clear();

typedef struct ast_bits_value_s ast_bits_value_t;
uint8_t ast_bits_value_get(const ast_bits_value_t *v);
const char *ast_bits_value_description(const ast_bits_value_t *v);

#ifdef __cplusplus
}
#endif

#endif /* __ASTERIX_H__ */
