# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /tmp/tmp.uqC0tufxzr

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /tmp/tmp.uqC0tufxzr/cmake-build-remote-pi

# Include any dependencies generated for this target.
include CMakeFiles/RPINeopixel.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/RPINeopixel.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/RPINeopixel.dir/flags.make

CMakeFiles/RPINeopixel.dir/src/main.cpp.o: CMakeFiles/RPINeopixel.dir/flags.make
CMakeFiles/RPINeopixel.dir/src/main.cpp.o: ../src/main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/tmp/tmp.uqC0tufxzr/cmake-build-remote-pi/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/RPINeopixel.dir/src/main.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/RPINeopixel.dir/src/main.cpp.o -c /tmp/tmp.uqC0tufxzr/src/main.cpp

CMakeFiles/RPINeopixel.dir/src/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/RPINeopixel.dir/src/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /tmp/tmp.uqC0tufxzr/src/main.cpp > CMakeFiles/RPINeopixel.dir/src/main.cpp.i

CMakeFiles/RPINeopixel.dir/src/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/RPINeopixel.dir/src/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /tmp/tmp.uqC0tufxzr/src/main.cpp -o CMakeFiles/RPINeopixel.dir/src/main.cpp.s

# Object files for target RPINeopixel
RPINeopixel_OBJECTS = \
"CMakeFiles/RPINeopixel.dir/src/main.cpp.o"

# External object files for target RPINeopixel
RPINeopixel_EXTERNAL_OBJECTS =

RPINeopixel: CMakeFiles/RPINeopixel.dir/src/main.cpp.o
RPINeopixel: CMakeFiles/RPINeopixel.dir/build.make
RPINeopixel: CMakeFiles/RPINeopixel.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/tmp/tmp.uqC0tufxzr/cmake-build-remote-pi/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable RPINeopixel"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/RPINeopixel.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/RPINeopixel.dir/build: RPINeopixel

.PHONY : CMakeFiles/RPINeopixel.dir/build

CMakeFiles/RPINeopixel.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/RPINeopixel.dir/cmake_clean.cmake
.PHONY : CMakeFiles/RPINeopixel.dir/clean

CMakeFiles/RPINeopixel.dir/depend:
	cd /tmp/tmp.uqC0tufxzr/cmake-build-remote-pi && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /tmp/tmp.uqC0tufxzr /tmp/tmp.uqC0tufxzr /tmp/tmp.uqC0tufxzr/cmake-build-remote-pi /tmp/tmp.uqC0tufxzr/cmake-build-remote-pi /tmp/tmp.uqC0tufxzr/cmake-build-remote-pi/CMakeFiles/RPINeopixel.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/RPINeopixel.dir/depend
