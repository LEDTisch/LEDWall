cmake_minimum_required(VERSION 3.13)
project(RPINeopixel)

set(CMAKE_CXX_STANDARD 14)
include_directories("${PROJECT_SOURCE_DIR}/*/")
add_executable(RPINeopixel src/main.cpp)