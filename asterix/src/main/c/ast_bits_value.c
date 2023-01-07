#include "ast_private.h"

typedef struct ast_bits_value_s
{
	uint8_t value;
	char *description;
} ast_bits_value_t;

int ast__bits_value_init(ast_bits_value_t **v, uint8_t value, const char *description)
{
	ast_bits_value_t *b = NULL;
	if (NULL == v || NULL == description)
	{
		AST__ERROR_SET(
			EINVAL,
			"parameters must not be null, v: %s, description: %s",
			AST__NULL_STR(v),
			AST__NULL_STR(description));
		goto error;
	}

	if (ast__alloc((void **)&b, sizeof(ast_bits_value_t)) < 0)
	{
		AST__ERROR_APPEND("%s", "failed to allocate bits value");
		goto error;
	}

	b->value = value;
	b->description = ast__strdup(description);
	*v = b;

	return 0;

error:
	if (NULL != b)
	{
		if (NULL != b->description)
		{
			ast__free(b->description);
		}

		ast__free(b);
	}

	return -1;
}

void ast__bits_value_destroy(ast_bits_value_t *v)
{
	if (NULL == v)
	{
		AST__ERROR_SET(EINVAL, "parameter must not be null, v=%s", AST__NULL_STR(v));
		return;
	}

	if (NULL != v->description)
	{
		ast__free(v->description);
	}

	ast__free(v);
}

uint8_t ast_bits_value_get(const ast_bits_value_t *v)
{
	if (NULL == v)
	{
		AST__ERROR_SET(EINVAL, "parameter must not be null, v=%s", AST__NULL_STR(v));
		return 0;
	}

	return v->value;
}

const char *ast_bits_value_description(const ast_bits_value_t *v)
{
	if (NULL == v)
	{
		AST__ERROR_SET(EINVAL, "parameter must not be null, v=%s", AST__NULL_STR(v));
		return NULL;
	}

	return v->description;
}
