cmake_minimum_required(VERSION 3.14 FATAL_ERROR)
cmake_policy(VERSION 3.14)

set(CMAKE_MODULE_PATH ${CMAKE_CURRENT_SOURCE_DIR}/cmake ${CMAKE_MODULE_PATH})
include(FetchContent)

file(STRINGS version.txt ASTERIX_VERSION_TXT LIMIT_COUNT 1 REGEX "^[0-9]+(\\.[0-9]+)+")
string(REGEX REPLACE "^([0-9]+(\\.[0-9]+)+).*$" "\\1" ASTERIX_VERSION_FROM_FILE "${ASTERIX_VERSION_TXT}")

if (${CMAKE_CURRENT_SOURCE_DIR} STREQUAL ${CMAKE_CURRENT_BINARY_DIR})
    message(FATAL_ERROR "In-source builds not allowed. Please make a new directory (called a build directory)"
        "and run CMake from there. You may need to remove CMakeCache.txt.")
endif ()

if (${CMAKE_SOURCE_DIR} STREQUAL ${CMAKE_CURRENT_SOURCE_DIR})
    set(STANDALONE_BUILD TRUE)
endif ()

option(C_WARNINGS_AS_ERRORS "Enable warnings as errors for C" OFF)
option(SANITISE_BUILD "Enable sanitise options" OFF)
option(ASTERIX_TESTS "Enable tests" ${STANDALONE_BUILD})
option(ASTERIX_BUILD_TOOLS "Enable building the tools projects" ${STANDALONE_BUILD})
option(ASTERIX_BUILD_SAMPLES "Enable building the sample projects" ${STANDALONE_BUILD})
option(LINK_SAMPLES_CLIENT_SHARED "Enable shared linking for sample projects" OFF)
option(ASTERIX_INSTALL_TARGETS "Enable installation step" ${STANDALONE_BUILD})

if (UNIX)
    option(ASTERIX_ENABLE_NONSTANDARD_OPTIMIZATIONS "Enable Ofast for release builds" ${STANDALONE_BUILD})
endif ()

unset(STANDALONE_BUILD)

include(ExternalProject)

# CMAKE_OSX_ARCHITECTURES can be set to arm64 or x86_64 (or both) to make a specific architecture version. Requires
# CMake 3.21. Must be set before project(), etc.
#set(CMAKE_OSX_ARCHITECTURES x86_64)

project("asterix" VERSION "${ASTERIX_VERSION_FROM_FILE}")

if (ASTERIX_TESTS)
    enable_testing()
    include(CTest)
endif ()

# default built type is Release
if (NOT CMAKE_BUILD_TYPE)
    set(CMAKE_BUILD_TYPE "Release" CACHE STRING "Choose the type of build" FORCE)
endif (NOT CMAKE_BUILD_TYPE)

##########################################################
# Platform flags, etc.

find_package(Threads)

if (NOT DEFINED CMAKE_C_STANDARD)
    set(CMAKE_C_STANDARD 11)
endif ()

if (NOT DEFINED CMAKE_C_EXTENSIONS)
    set(CMAKE_C_EXTENSIONS OFF)
endif ()

add_definitions(-DASTERIX_VERSION_TXT="${ASTERIX_VERSION_TXT}")
add_definitions(-DASTERIX_VERSION_MAJOR=${asterix_VERSION_MAJOR})
add_definitions(-DASTERIX_VERSION_MINOR=${asterix_VERSION_MINOR})
add_definitions(-DASTERIX_VERSION_PATCH=${asterix_VERSION_PATCH})

# all UNIX-based platform compiler flags
if (UNIX)
    add_compile_options(-Wall -Wpedantic -Wextra -Wno-unused-parameter)

    if (C_WARNINGS_AS_ERRORS)
        add_compile_options($<$<COMPILE_LANGUAGE:C>:-Werror>)
    endif (C_WARNINGS_AS_ERRORS)

    if (SANITISE_BUILD)
        set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fsanitize=address -fsanitize=leak -fsanitize=undefined -fno-omit-frame-pointer -DASTERIX_SANITIZE_ENABLED")
    endif (SANITISE_BUILD)
endif ()

# platform specific flags
if (APPLE)
    add_compile_options(-Wsign-compare)
    add_definitions(-DDarwin)
    add_compile_options(-Wno-deprecated-register)
elseif (CYGWIN)
    add_definitions(-DWIN32)
    if (ASTERIX_TESTS)
        add_definitions(-DGTEST_HAS_PTHREAD)
    endif ()
elseif (MSVC)
    add_definitions(-DWIN32)
    add_definitions(-D_CRT_SECURE_NO_WARNINGS)
    add_definitions(-D_CRT_NONSTDC_NO_WARNINGS)
    add_definitions(-DNOMINMAX)

    if (${MSVC_VERSION} GREATER_EQUAL 1915)
        # Acknowledge that we understand MSVC resolved a byte alignment issue in this compiler
        add_definitions(-D_ENABLE_EXTENDED_ALIGNED_STORAGE)
    endif ()

    set(CMAKE_C_FLAGS_DEBUG "${CMAKE_C_FLAGS_DEBUG} /MTd")
    set(CMAKE_C_FLAGS_RELEASE "${CMAKE_C_FLAGS_RELEASE} /MT")

    unset(CMAKE_C_STANDARD) # To avoid `/std:c11` in the compiler options

    if (C_WARNINGS_AS_ERRORS)
        add_compile_options($<$<COMPILE_LANGUAGE:C>:/WX>)
    endif (C_WARNINGS_AS_ERRORS)

    if (SANITISE_BUILD)
        set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} /fsanitize=address /Zi /D ASTERIX_SANITIZE_ENABLED")
    endif (SANITISE_BUILD)

endif ()

##########################################################
# Project variables, etc.

if (NOT DEFINED GRADLE_WRAPPER)
    if (MSVC)
        set(GRADLE_WRAPPER "gradlew.bat" CACHE INTERNAL "Location of the Gradle wrapper script")
    else ()
        set(GRADLE_WRAPPER "./gradlew" CACHE INTERNAL "Location of the Gradle wrapper script")
    endif ()
endif ()

set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/binaries")
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/lib")
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/lib")

if (ASTERIX_BUILD_SAMPLES)
    set(ASTERIX_SAMPLES_PATH "${CMAKE_CURRENT_SOURCE_DIR}/asterix-samples/src/main/c")   
endif ()

if (ASTERIX_BUILD_TOOLS)
    set(ASTERIX_TOOLS_PATH "${CMAKE_CURRENT_SOURCE_DIR}/asterix-tools/src/main/c")   
endif ()

set(ASTERIX_SOURCE_PATH "${CMAKE_CURRENT_SOURCE_DIR}/asterix/src/main/c")

if (ASTERIX_TESTS)
    set(ASTERIX_TEST_PATH "${CMAKE_CURRENT_SOURCE_DIR}/asterix/src/test/c")
endif ()

if (ASTERIX_BUILD_SAMPLES)
    # hdr_histogram
    include_directories(${HDRHISTOGRAM_SOURCE_DIR}/include)
endif ()

if (ASTERIX_BUILD_TOOLS)
    # hdr_histogram
    include_directories(${HDRHISTOGRAM_SOURCE_DIR}/include)
endif ()

##########################################################

add_definitions(-D_FILE_OFFSET_BITS=64)

##########################################################
# HdrHistogram usage - use MD5 as means to identify snapshot

if (ASTERIX_BUILD_SAMPLES)
    # Avoid warning about DOWNLOAD_EXTRACT_TIMESTAMP in CMake 3.24:
 	if (CMAKE_VERSION VERSION_GREATER "3.24.0")
 		cmake_policy(SET CMP0135 NEW)
 	endif()

    set(HDR_LOG_REQUIRED "OFF" CACHE INTERNAL "Set log required option")
    set(HDR_HISTOGRAM_BUILD_PROGRAMS "OFF" CACHE INTERNAL "Set build programs option")
    FetchContent_Declare(
        hdr_histogram
        URL ${CMAKE_CURRENT_SOURCE_DIR}/cppbuild/HdrHistogram_c-0.11.4.zip
        URL_MD5 4d8f8ed3fe69422cf6b44fdabd98d462
    )

    FetchContent_GetProperties(hdr_histogram)
    if(NOT hdr_histogram_POPULATED)
        FetchContent_Populate(hdr_histogram)
        add_subdirectory(${hdr_histogram_SOURCE_DIR} ${hdr_histogram_BINARY_DIR} EXCLUDE_FROM_ALL)
    endif()
#    FetchContent_MakeAvailable(hdr_histogram)
endif ()

if (ASTERIX_BUILD_TOOLS)
    # Avoid warning about DOWNLOAD_EXTRACT_TIMESTAMP in CMake 3.24:
 	if (CMAKE_VERSION VERSION_GREATER "3.24.0")
 		cmake_policy(SET CMP0135 NEW)
 	endif()

    set(HDR_LOG_REQUIRED "OFF" CACHE INTERNAL "Set log required option")
    set(HDR_HISTOGRAM_BUILD_PROGRAMS "OFF" CACHE INTERNAL "Set build programs option")
    FetchContent_Declare(
        hdr_histogram
        URL ${CMAKE_CURRENT_SOURCE_DIR}/cppbuild/HdrHistogram_c-0.11.4.zip
        URL_MD5 4d8f8ed3fe69422cf6b44fdabd98d462
    )

    FetchContent_GetProperties(hdr_histogram)
    if(NOT hdr_histogram_POPULATED)
        FetchContent_Populate(hdr_histogram)
        add_subdirectory(${hdr_histogram_SOURCE_DIR} ${hdr_histogram_BINARY_DIR} EXCLUDE_FROM_ALL)
    endif()
#    FetchContent_MakeAvailable(hdr_histogram)
endif ()

add_subdirectory(${ASTERIX_SOURCE_PATH})

if (ASTERIX_TESTS)
    add_subdirectory(${ASTERIX_TEST_PATH})
endif ()

if (ASTERIX_BUILD_SAMPLES)
    add_subdirectory(${ASTERIX_SAMPLES_PATH})
endif ()

if (ASTERIX_BUILD_TOOLS)
    add_subdirectory(${ASTERIX_TOOLS_PATH})
endif ()

##########################################################
# package target

set(CPACK_RESOURCE_FILE_LICENSE "${CMAKE_CURRENT_SOURCE_DIR}/LICENSE")
set(CPACK_PACKAGE_VERSION_MAJOR "${asterix_VERSION_MAJOR}")
set(CPACK_PACKAGE_VERSION_MINOR "${asterix_VERSION_MINOR}")
set(CPACK_PACKAGE_VERSION_PATCH "${asterix_VERSION_PATCH}")

set(CPACK_GENERATOR "TGZ;STGZ")
include(CPack)
