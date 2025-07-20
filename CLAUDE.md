# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

ScrcpyKt is a Kotlin client library for scrcpy (Android screen mirroring). This project creates a programmatic interface to control scrcpy functionality from Kotlin/JVM applications.

### Purpose
This library provides a Kotlin wrapper around scrcpy's CLI commands, allowing developers to integrate Android screen mirroring and control capabilities into their applications without directly managing CLI processes.

### Development Rules
- All functionality must be based on the official scrcpy specification: https://github.com/Genymobile/scrcpy
- The API should mirror scrcpy's CLI commnd structure and options
- Maintain compatibility with scrcpy's command-line interface patterns
- Follow scrcpy's naming conventions and parameter structures where applicable

## Development Commands

### Build and Testing
- **Build the project**: `./gradlew build`
- **Run tests**: `./gradlew test`
- **Clean build**: `./gradlew clean`
- **Check code quality**: `./gradlew check`

### Development
- **Compile Kotlin**: `./gradlew compileKotlin`
- **Compile test code**: `./gradlew compileTestKotlin`

## Project Structure

This is a standard Gradle Kotlin project with the following structure:
- `src/main/kotlin/` - Main Kotlin source code (currently empty)
- `src/test/kotlin/` - Test source code (currently empty)
- `build.gradle` - Main build configuration
- `settings.gradle` - Project settings

## Technical Details

- **Language**: Kotlin (JVM target)
- **Build System**: Gradle with Kotlin DSL
- **Kotlin Version**: 2.1.20
- **JVM Toolchain**: Java 17
- **Testing Framework**: JUnit Platform with kotlin-test
- **Code Style**: Official Kotlin code style (configured in gradle.properties)

## Key Configuration

- Group ID: `jp.kaleidot725.scrcpykt`
- Uses Gradle wrapper for consistent builds across environments
- Standard Maven Central repository for dependencies