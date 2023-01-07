#ifndef __AST_INTERNAL_H__
#define __AST_INTERNAL_H__

#include <stdint.h>
#include <stddef.h>
#include <string.h>
#include <errno.h>
#include "ast_platform.h"
#include "asterix.h"

int32_t ast__semantic_version_compose(uint8_t major, uint8_t minor, uint8_t patch);
uint8_t ast__semantic_version_major(int32_t version);
uint8_t ast__semantic_version_minor(int32_t version);
uint8_t ast__semantic_version_patch(int32_t version);

void ast__thread_set_name(const char *role_name);

#if defined(AST_COMPILER_GCC)

#include <pthread.h>
typedef pthread_mutex_t ast__mutex_t;
#define AST__INIT_ONCE pthread_once_t
#define AST__INIT_ONCE_VALUE PTHREAD_ONCE_INIT

typedef pthread_t ast__thread_t;
#define ast__mutex_init pthread_mutex_init
#define ast__mutex_lock pthread_mutex_lock
#define ast__mutex_unlock pthread_mutex_unlock
#define ast__mutex_destroy pthread_mutex_destroy
#define ast__thread_once pthread_once
#define ast__thread_attr_init pthread_attr_init
#define ast__thread_create pthread_create
#define ast__thread_join pthread_join
#define ast__thread_key_create pthread_key_create
#define ast__thread_key_delete pthread_key_delete
#define ast__thread_get_specific pthread_getspecific
#define ast__thread_set_specific pthread_setspecific

#elif defined(AST_COMPILER_MSVC)

typedef void *ast__mutex_t;

struct ast__thread_s;
typedef struct ast__thread_s *ast__thread_t;

typedef union ast__init_once_union
{
    void *ptr;
} AST__INIT_ONCE;

typedef unsigned long pthread_attr_t;
typedef unsigned long pthread_key_t;

#define AST__INIT_ONCE_VALUE \
    {                        \
        0                    \
    }

void ast__thread_once(AST__INIT_ONCE *s_init_once, void *callback);

int ast__mutex_init(ast__mutex_t *mutex, void *attr);
int ast__mutex_destroy(ast__mutex_t *mutex);
int ast__mutex_lock(ast__mutex_t *mutex);
int ast__mutex_unlock(ast__mutex_t *mutex);
int ast__thread_attr_init(pthread_attr_t *attr);
int ast__thread_create(ast__thread_t *thread_ptr, void *attr, void *(*callback)(void *), void *arg0);
int ast__thread_join(ast__thread_t thread, void **value_ptr);
int ast__thread_key_create(pthread_key_t *key_ptr, void (*destr_func)(void *));
int ast__thread_key_delete(pthread_key_t key);
int ast__thread_set_specific(pthread_key_t key, const void *pointer);
void *ast__thread_get_specific(pthread_key_t key);

#else
#error Unsupported platform!
#endif

// sched

#if defined(AST_COMPILER_GCC)

void proc_yield();

#elif defined(AST_COMPILER_MSVC)

int sched_yield(void);
#define proc_yield _mm_pause

#else
#error Unsupported platform!
#endif

/* Allocator prototypes */
void *ast__calloc(size_t count, size_t size);
void *ast__malloc(size_t size);
void ast__free(void *ptr);
void *ast__realloc(void *ptr, size_t size);
void *ast__reallocf(void *ptr, size_t size);
int ast__alloc(void **ptr, size_t size);
int ast__alloc_no_err(void **ptr, size_t size);
char *ast__strdup(const char *s);
char *ast__strndup(const char *s, size_t n);

#define AST__ERROR_CODE_UNKNOWN_CODE_VALUE (-1)
#define AST__ERROR_CODE_UNUSED (0)
#define AST__ERROR_CODE_GENERIC_ERROR (1)
#define AST__ERROR_MAX_TOTAL_LENGTH (8192)

typedef struct ast__error_per_thread_s
{
    int errcode;
    size_t offset;
    char errmsg[AST__ERROR_MAX_TOTAL_LENGTH];
} ast__error_per_thread_t;

void ast__error_set_errno(const int errcode);
void ast__error_set(const int errcode, const char *function, const char *filename, int line_number, const char *format, ...);
void ast__error_append(const char *function, const char *filename, int line_number, const char *format, ...);

#if defined(AST_COMPILER_MSVC)
bool ast__error_dll_process_attach();
void ast__error_dll_thread_detach();
void ast__error_dll_process_detach();
void ast__error_set_windows(const int errcode, const char *function, const char *filename, int line_number, const char *format, ...);
#define __FILENAME__ (strrchr(__FILE__, '\\') ? strrchr(__FILE__, '\\') + 1 : __FILE__)
#define AST__ERROR_SET_WIN(errcode, fmt, ...) ast__error_set_windows(errcode, __func__, __FILENAME__, __LINE__, fmt, __VA_ARGS__)
#else
#define __FILENAME__ (strrchr(__FILE__, '/') ? strrchr(__FILE__, '/') + 1 : __FILE__)
#endif

#define AST__ERROR_SET(errcode, fmt, ...) ast__error_set(errcode, __func__, __FILENAME__, __LINE__, fmt, __VA_ARGS__)
#define AST__ERROR_APPEND(fmt, ...) ast__error_append(__func__, __FILENAME__, __LINE__, fmt, __VA_ARGS__)
#define AST__NULL_STR(v) NULL == v ? "NULL" : "OK"

int ast__bits_value_init(ast_bits_value_t **v, uint8_t value, const char *description);
void ast__bits_value_destroy(ast_bits_value_t *v);

#endif /*__AST_INTERNAL_H__*/
