#include <stdint.h>

const char ast_version__full_str[] = "asterix version " ASTERIX_VERSION_TXT " built " __DATE__ " " __TIME__;
int ast_version__major = ASTERIX_VERSION_MAJOR;
int ast_version__minor = ASTERIX_VERSION_MINOR;
int ast_version__patch = ASTERIX_VERSION_PATCH;

const char *ast_version_full()
{
	return ast_version__full_str;
}

int ast_version_major()
{
	return ast_version__major;
}

int ast_version_minor()
{
	return ast_version__minor;
}

int ast_version_patch()
{
	return ast_version__patch;
}

int32_t ast__semantic_version_compose(uint8_t major, uint8_t minor, uint8_t patch)
{
	return (major << 16) | (minor << 8) | patch;
}

uint8_t ast__semantic_version_major(int32_t version)
{
	return (uint8_t)((version >> 16) & 0xFF);
}

uint8_t ast__semantic_version_minor(int32_t version)
{
	return (uint8_t)((version >> 8) & 0xFF);
}

uint8_t ast__semantic_version_patch(int32_t version)
{
	return (uint8_t)(version & 0xFF);
}
