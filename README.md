# ScrcpyKt

[![Kotlin](https://img.shields.io/badge/kotlin-2.2.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/kaleidot725/ScrcpyKt.svg)](https://jitpack.io/#kaleidot725/ScrcpyKt)

A Kotlin client library for [scrcpy](https://github.com/Genymobile/scrcpy) - enabling programmatic control of Android screen mirroring and device interaction from Kotlin/JVM applications.

## Features

- 🖥️ **Screen Mirroring** - Mirror Android device screens with customizable quality and display settings
- 📹 **Screen Recording** - Record device screens to video files with various codec options
- 📱 **Camera Mirroring** - Mirror device cameras with configurable resolution and frame rates  
- 🎮 **OTG Mode** - Control devices without screen mirroring for input-only scenarios
- ⚙️ **Flexible Configuration** - Comprehensive API mirroring scrcpy's CLI capabilities
- 🚀 **Process Control** - Full control over scrcpy processes from your application

## Requirements

- Java 17 or higher
- [scrcpy](https://github.com/Genymobile/scrcpy) installed and available in PATH
- Android device with USB debugging enabled or network connection configured

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("jp.kaleidot725.scrcpykt:scrcpykt:1.0.0")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'jp.kaleidot725.scrcpykt:scrcpykt:1.0.0'
}
```

### Maven

```xml
<dependency>
    <groupId>jp.kaleidot725.scrcpykt</groupId>
    <artifactId>scrcpykt</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start

### Basic Screen Mirroring

```kotlin
import jp.kaleidot725.scrcpykt.ScrcpyClient

val client = ScrcpyClient.create()

// Start basic mirroring
val result = client.mirror {
    display {
        windowTitle("My App")
        windowSize(800, 600)
    }
    video {
        maxSize(1920)
        maxFps(60)
    }
}

// Handle the result
when (result) {
    is ScrcpyResult.Success -> {
        println("Mirroring started")
        // Process control is now in your hands
        result.process.waitFor()
    }
    is ScrcpyResult.Error -> {
        println("Failed: ${result.message}")
    }
}
```

### Screen Recording

```kotlin
val result = client.record("output.mp4") {
    recording {
        format(RecordFormat.MP4)
        orientation(CaptureOrientation.ROTATION_0)
    }
    video {
        codec(VideoCodec.H264)
        bitRate(8000000)
        maxSize(1920)
    }
    audio {
        codec(AudioCodec.AAC)
        bitRate(128000)
    }
}
```

### Camera Mirroring

```kotlin
val result = client.camera {
    camera {
        size(1920, 1080)
        facing(CameraFacing.BACK)
        fps(30)
    }
    display {
        windowTitle("Camera View")
    }
}
```

### Advanced Configuration

```kotlin
val result = client.mirror {
    video {
        codec(VideoCodec.H265)
        bitRate(10000000)
        maxFps(60)
        maxSize(2560)
    }
    audio {
        codec(AudioCodec.OPUS)
        bitRate(256000)
        source(AudioSource.OUTPUT)
    }
    display {
        windowTitle("Advanced Mirror")
        windowPosition(100, 100)
        windowSize(1280, 720)
        alwaysOnTop()
        fullscreen()
    }
    input {
        keyboard(KeyboardMode.UHID)
        mouse(MouseMode.UHID)
        gamepad(GamepadMode.UHID)
    }
    connection {
        serial("ABC123DEF456")
        selectUsb()
    }
    control {
        stayAwake()
        showTouches()
        disableScreensaver()
        powerOffOnClose()
    }
}
```

### Process Control

ScrcpyKt gives you full control over the scrcpy process:

```kotlin
val result = client.mirror { /* configuration */ }

when (result) {
    is ScrcpyResult.Success -> {
        val process = result.process
        
        // Monitor process in background
        Thread {
            val exitCode = process.waitFor()
            println("Process ended with code: $exitCode")
        }.start()
        
        // Terminate when needed
        println("Press Enter to stop...")
        readLine()
        process.destroyForcibly()
    }
    is ScrcpyResult.Error -> {
        println("Error: ${result.message}")
    }
}
```

## API Reference

### ScrcpyClient

The main client class providing methods to execute scrcpy operations:

- `mirror(configure)` - Start screen mirroring
- `record(outputFile, configure)` - Record screen to file
- `camera(configure)` - Mirror device camera
- `otg(configure)` - Start OTG mode for input-only control
- `command(configure)` - Build command without execution

### Configuration Builders

#### Video Configuration
```kotlin
video {
    codec(VideoCodec.H264)        // Video codec (H264, H265, AV1)
    bitRate(8000000)              // Video bitrate
    maxSize(1920)                 // Maximum resolution
    maxFps(60)                    // Maximum frame rate
    source(VideoSource.DISPLAY)   // Video source
    disableVideo()                // Disable video
}
```

#### Audio Configuration
```kotlin
audio {
    codec(AudioCodec.AAC)         // Audio codec (AAC, OPUS, FLAC)
    bitRate(128000)               // Audio bitrate
    source(AudioSource.OUTPUT)    // Audio source
    disableAudio()                // Disable audio
}
```

#### Display Configuration
```kotlin
display {
    windowTitle("My App")         // Window title
    windowSize(800, 600)          // Window size
    windowPosition(100, 100)      // Window position
    alwaysOnTop()                 // Keep window on top
    fullscreen()                  // Start fullscreen
}
```

#### Input Configuration
```kotlin
input {
    keyboard(KeyboardMode.UHID)   // Keyboard mode
    mouse(MouseMode.UHID)         // Mouse mode
    gamepad(GamepadMode.UHID)     // Gamepad mode
    enableOtg()                   // Enable OTG mode
}
```

### ScrcpyResult

Result of scrcpy operations:

- `ScrcpyResult.Success(process)` - Operation started successfully, contains the Process
- `ScrcpyResult.Error(message, exception)` - Operation failed

## Example Application

See the [`app`](app/) module for a complete example application demonstrating all features.

Run the example:

```bash
./gradlew :app:run
```

## Building

Build the library:

```bash
./gradlew build
```

Run tests:

```bash
./gradlew test
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### Development Guidelines

- Follow the official [scrcpy specification](https://github.com/Genymobile/scrcpy)
- API should mirror scrcpy's CLI command structure
- Maintain compatibility with scrcpy's naming conventions
- Write tests for new functionality

## License

```
Copyright 2024 kaleidot725

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Acknowledgments

- [scrcpy](https://github.com/Genymobile/scrcpy) - The excellent Android screen mirroring tool this library wraps
- Kotlin team for the amazing language and tooling