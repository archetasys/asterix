if (MSVC AND "${CMAKE_SYSTEM_NAME}" MATCHES "Windows")
    set(CMAKE_WINDOWS_EXPORT_ALL_SYMBOLS ON)
    set(BUILD_SHARED_LIBS ON)
endif ()

if (MSVC AND "${CMAKE_SYSTEM_NAME}" MATCHES "Windows")
    set(ASTERIX_LIB_WINSOCK_LIBS wsock32 ws2_32 Iphlpapi)
    set(WSAPOLL_PROTOTYPE_EXISTS True)
endif ()

if (MSVC)
    set(ASTERIX_STATIC_LIB_LINK_OPTS "")
else()
    # Because dlsym() is used to load strategies.
    set(ASTERIX_STATIC_LIB_LINK_OPTS "-rdynamic")
endif()

if (WSAPOLL_PROTOTYPE_EXISTS)
    add_definitions(-DHAVE_WSAPOLL)
endif ()

if ("${CMAKE_SYSTEM_NAME}" MATCHES "Linux")
    set(CMAKE_REQUIRED_DEFINITIONS "-D_GNU_SOURCE")
    add_definitions(-D_DEFAULT_SOURCE)
endif ()

set(SOURCES
    ast_version.c
    ast_thread.c
    ast_alloc.c
    ast_error.c
    ast_bits_value.c
    asterix.c)

set(HEADERS
    ast_platform.h
    ast_private.h
    asterix.h)

add_library(asterix SHARED ${SOURCES} ${HEADERS})
target_include_directories(asterix
    PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

add_library(asterix_static STATIC ${SOURCES} ${HEADERS})
target_include_directories(asterix_static
    PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

set(CMAKE_C_FLAGS_RELEASE "${CMAKE_C_FLAGS_RELEASE} -DDISABLE_BOUNDS_CHECKS")

if ("${CMAKE_SYSTEM_NAME}" MATCHES "Linux")
    set(ASTERIX_LIB_M_LIBS m)
    if ("${CMAKE_SYSTEM_PROCESSOR}" MATCHES "aarch64")
        set(ASTERIX_LIB_ATOMIC_LIBS atomic)
    endif ()
endif ()

target_link_libraries(
    asterix
    ${CMAKE_DL_LIBS}
    ${ASTERIX_LIB_M_LIBS}
    ${ASTERIX_LIB_ATOMIC_LIBS}
    ${CMAKE_THREAD_LIBS_INIT}
    ${ASTERIX_LIB_WINSOCK_LIBS})

target_link_libraries(
    asterix_static INTERFACE
    ${CMAKE_DL_LIBS}
    ${ASTERIX_LIB_M_LIBS}
    ${ASTERIX_LIB_ATOMIC_LIBS}
    ${CMAKE_THREAD_LIBS_INIT}
    ${ASTERIX_LIB_WINSOCK_LIBS}
    ${ASTERIX_STATIC_LIB_LINK_OPTS}
    )