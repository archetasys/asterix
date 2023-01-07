if ("${CMAKE_SYSTEM_NAME}" MATCHES "Linux")
    set(CMAKE_REQUIRED_DEFINITIONS "-D_GNU_SOURCE")
    add_definitions(-D_DEFAULT_SOURCE)
endif ()

if (LINK_SAMPLES_CLIENT_SHARED OR (MSVC AND "${CMAKE_SYSTEM_NAME}" MATCHES "Windows"))
    set(CLIENT_LINK_LIB "asterix")
    add_definitions(-DCLIENT_SHARED)
else()
    set(CLIENT_LINK_LIB "asterix_static")
endif ()

include_directories(${ASTERIX_SOURCE_PATH})

set(CMAKE_C_FLAGS_RELEASE "${CMAKE_C_FLAGS_RELEASE} -DDISABLE_BOUNDS_CHECKS")

include(FindRequiredPackage)
include(FindPCAP)
FindRequiredPackage(PCAP)

add_executable(astpcap astpcap.c)
target_link_libraries(astpcap ${CLIENT_LINK_LIB} ${PCAP_LIBRARY})