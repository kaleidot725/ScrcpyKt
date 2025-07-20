package jp.kaleidot725.scrcpykt

import jp.kaleidot725.scrcpykt.builders.ScrcpyCommandBuilder

fun main() {
    println("ScrcpyKt Test Program")
    println("====================")
    
    while (true) {
        showMenu()
        val input = readLine()?.trim()
        
        when (input) {
            "1" -> {
                println("\n1. Testing basic screen mirroring...")
                testBasicMirroring()
            }
            "2" -> {
                println("\n2. Testing screen recording...")
                testScreenRecording()
            }
            "3" -> {
                println("\n3. Testing camera mirroring...")
                testCameraMirroring()
            }
            "4" -> {
                println("\n4. Testing complex configuration...")
                testComplexConfiguration()
            }
            "5" -> {
                println("\n5. Testing OTG mode...")
                testOtgMode()
            }
            "6" -> {
                println("\n6. Testing command building...")
                testCommandBuilding()
            }
            "0", "exit", "quit" -> {
                println("Exiting ScrcpyKt Test Program. Goodbye!")
                break
            }
            else -> {
                println("Invalid input. Please enter a number from 1-6 or 0 to exit.")
            }
        }
        
        println("\n" + "=".repeat(50))
    }
}

fun showMenu() {
    println("\nSelect a test to run:")
    println("1. Basic screen mirroring")
    println("2. Screen recording")
    println("3. Camera mirroring")
    println("4. Complex configuration")
    println("5. OTG mode")
    println("6. Command building examples")
    println("0. Exit")
    print("\nEnter your choice (1-6, 0 to exit): ")
}

fun testBasicMirroring() {
    val command = ScrcpyKt.command {
        display {
            windowTitle("ScrcpyKt Test")
            windowSize(800, 600)
        }
        video {
            maxSize(1920)
            maxFps(60)
        }
    }
    
    println("Generated command: ${command.buildCommand().joinToString(" ")}")
    
    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        val result = ScrcpyKt.client().execute(command)
        handleResult(result, "Basic mirroring")
    } else {
        println("Command not executed.")
    }
}

fun testScreenRecording() {
    val command = ScrcpyKt.command {
        recording {
            outputFile("test_recording.mp4")
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
    
    println("Generated command: ${command.buildCommand().joinToString(" ")}")
    
    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        val result = ScrcpyKt.client().execute(command)
        handleResult(result, "Screen recording")
    } else {
        println("Command not executed.")
    }
}

fun testCameraMirroring() {
    val command = ScrcpyKt.command {
        video {
            source(VideoSource.CAMERA)
        }
        camera {
            size(1920, 1080)
            facing(CameraFacing.BACK)
            fps(30)
        }
        display {
            windowTitle("Camera Mirror")
        }
    }
    
    println("Generated command: ${command.buildCommand().joinToString(" ")}")
    
    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        val result = ScrcpyKt.client().execute(command)
        handleResult(result, "Camera mirroring")
    } else {
        println("Command not executed.")
    }
}

fun testComplexConfiguration() {
    val command = ScrcpyCommandBuilder()
        .video {
            codec(VideoCodec.H265)
            bitRate(10000000)
            maxFps(60)
            maxSize(2560)
            encoder("custom_encoder")
        }
        .audio {
            codec(AudioCodec.OPUS)
            bitRate(256000)
            source(AudioSource.OUTPUT)
        }
        .display {
            windowTitle("Complex Scrcpy Configuration")
            windowPosition(100, 100)
            windowSize(1280, 720)
            alwaysOnTop()
        }
        .input {
            keyboard(KeyboardMode.UHID)
            mouse(MouseMode.UHID)
            gamepad(GamepadMode.UHID)
        }
        .connection {
            serial("ABC123DEF456")
            selectUsb()
        }
        .control {
            stayAwake()
            showTouches()
            disableScreensaver()
        }
        .verbosity(LogLevel.DEBUG)
        .startApp("com.example.testapp")
        .build()
    
    println("Generated command: ${command.buildCommand().joinToString(" ")}")
    
    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        val result = ScrcpyKt.client().execute(command)
        handleResult(result, "Complex configuration")
    } else {
        println("Command not executed.")
    }
}

fun testOtgMode() {
    val command = ScrcpyKt.command {
        input {
            enableOtg()
            keyboard(KeyboardMode.UHID)
            mouse(MouseMode.UHID)
        }
        connection {
            selectUsb()
        }
    }
    
    println("Generated command: ${command.buildCommand().joinToString(" ")}")
    
    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        val result = ScrcpyKt.client().execute(command)
        handleResult(result, "OTG mode")
    } else {
        println("Command not executed.")
    }
}

fun testCommandBuilding() {
    println("Testing various command combinations...")
    
    // Test all video codecs
    println("\nVideo codecs:")
    VideoCodec.values().forEach { codec ->
        val cmd = ScrcpyKt.command { video { codec(codec) } }
        println("  ${codec.name}: ${cmd.buildCommand().joinToString(" ")}")
    }
    
    // Test all audio sources
    println("\nAudio sources:")
    AudioSource.values().forEach { source ->
        val cmd = ScrcpyKt.command { audio { source(source) } }
        println("  ${source.name}: ${cmd.buildCommand().joinToString(" ")}")
    }
    
    // Test all keyboard modes
    println("\nKeyboard modes:")
    KeyboardMode.values().forEach { mode ->
        val cmd = ScrcpyKt.command { input { keyboard(mode) } }
        println("  ${mode.name}: ${cmd.buildCommand().joinToString(" ")}")
    }
    
    // Test boolean flags
    println("\nBoolean flags:")
    val flagsCommand = ScrcpyKt.command {
        video { disableVideo() }
        audio { disableAudio() }
        display { 
            fullscreen()
            alwaysOnTop() 
        }
        input { enableOtg() }
        recording { disablePlayback() }
        connection { 
            selectUsb()
            selectTcpip() 
        }
        control {
            turnScreenOff()
            stayAwake()
            powerOffOnClose()
            showTouches()
            disableScreensaver()
        }
    }
    println("  All flags: ${flagsCommand.buildCommand().joinToString(" ")}")
    
    // Test camera configuration
    println("\nCamera configuration:")
    CameraFacing.values().forEach { facing ->
        val cmd = ScrcpyKt.command { 
            video { source(VideoSource.CAMERA) }
            camera { 
                facing(facing)
                size("1920x1080")
                fps(30)
            }
        }
        println("  ${facing.name}: ${cmd.buildCommand().joinToString(" ")}")
    }
}

fun handleResult(result: ScrcpyResult, operation: String) {
    when (result) {
        is ScrcpyResult.Success -> {
            println("✅ $operation completed successfully")
            println("Exit code: ${result.exitCode}")
            if (result.output.isNotEmpty()) {
                println("Output: ${result.output}")
            }
            if (result.error.isNotEmpty()) {
                println("Error output: ${result.error}")
            }
        }
        is ScrcpyResult.Error -> {
            println("❌ $operation failed: ${result.message}")
            println("Exception: ${result.exception.message}")
        }
    }
}