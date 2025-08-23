package jp.kaleidot725.scrcpykt

import jp.kaleidot725.scrcpykt.option.AudioCodec
import jp.kaleidot725.scrcpykt.option.AudioSource
import jp.kaleidot725.scrcpykt.option.CameraFacing
import jp.kaleidot725.scrcpykt.option.CaptureOrientation
import jp.kaleidot725.scrcpykt.option.GamepadMode
import jp.kaleidot725.scrcpykt.option.KeyboardMode
import jp.kaleidot725.scrcpykt.option.LogLevel
import jp.kaleidot725.scrcpykt.option.MouseMode
import jp.kaleidot725.scrcpykt.option.RecordFormat
import jp.kaleidot725.scrcpykt.option.VideoCodec
import jp.kaleidot725.scrcpykt.option.VideoSource

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
    println("\nNote: During scrcpy execution, press Enter key to terminate and return to menu.")
    print("\nEnter your choice (1-6, 0 to exit): ")
}

fun testBasicMirroring() {
    val client = ScrcpyClient.create()

    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        println("Press Enter key to terminate scrcpy and return to menu.")
        val result =
            client.mirror {
                display {
                    windowTitle("ScrcpyKt Test")
                    windowSize(800, 600)
                }
                video {
                    maxSize(1920)
                    maxFps(60)
                }
            }
        handleProcessResult(result, "Basic mirroring")
    } else {
        println("Command not executed.")
    }
}

fun testScreenRecording() {
    val client = ScrcpyClient.create()

    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        println("Press Enter key to terminate scrcpy and return to menu.")
        val result =
            client.record("test_recording.mp4") {
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
        handleProcessResult(result, "Screen recording")
    } else {
        println("Command not executed.")
    }
}

fun testCameraMirroring() {
    val client = ScrcpyClient.create()

    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        println("Press Enter key to terminate scrcpy and return to menu.")
        val result =
            client.camera {
                camera {
                    size(1920, 1080)
                    facing(CameraFacing.BACK)
                    fps(30)
                }
                display {
                    windowTitle("Camera Mirror")
                }
            }
        handleProcessResult(result, "Camera mirroring")
    } else {
        println("Command not executed.")
    }
}

fun testComplexConfiguration() {
    val client = ScrcpyClient.create()

    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        println("Press Enter key to terminate scrcpy and return to menu.")
        val result =
            client.mirror {
                video {
                    codec(VideoCodec.H265)
                    bitRate(10000000)
                    maxFps(60)
                    maxSize(2560)
                    encoder("custom_encoder")
                }
                audio {
                    codec(AudioCodec.OPUS)
                    bitRate(256000)
                    source(AudioSource.OUTPUT)
                }
                display {
                    windowTitle("Complex Scrcpy Configuration")
                    windowPosition(100, 100)
                    windowSize(1280, 720)
                    alwaysOnTop()
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
                }
                verbosity(LogLevel.DEBUG)
                startApp("com.example.testapp")
            }
        handleProcessResult(result, "Complex configuration")
    } else {
        println("Command not executed.")
    }
}

fun testOtgMode() {
    val client = ScrcpyClient.create()

    print("Execute this command? (y/n): ")
    val execute = readLine()?.trim()?.lowercase()
    if (execute == "y" || execute == "yes") {
        println("Executing command...")
        println("Press Enter key to terminate scrcpy and return to menu.")
        val result =
            client.otg {
                input {
                    keyboard(KeyboardMode.UHID)
                    mouse(MouseMode.UHID)
                }
                connection {
                    selectUsb()
                }
            }
        handleProcessResult(result, "OTG mode")
    } else {
        println("Command not executed.")
    }
}

fun testCommandBuilding() {
    println("Testing various command combinations...")
    val client = ScrcpyClient.create()

    // Test all video codecs
    println("\nVideo codecs:")
    VideoCodec.entries.forEach { codec ->
        val cmd = client.command { video { codec(codec) } }
        println("  ${codec.name}: ${cmd.buildCommand().joinToString(" ")}")
    }

    // Test all audio sources
    println("\nAudio sources:")
    AudioSource.entries.forEach { source ->
        val cmd = client.command { audio { source(source) } }
        println("  ${source.name}: ${cmd.buildCommand().joinToString(" ")}")
    }

    // Test all keyboard modes
    println("\nKeyboard modes:")
    KeyboardMode.entries.forEach { mode ->
        val cmd = client.command { input { keyboard(mode) } }
        println("  ${mode.name}: ${cmd.buildCommand().joinToString(" ")}")
    }

    // Test boolean flags
    println("\nBoolean flags:")
    val flagsCommand =
        client.command {
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
    CameraFacing.entries.forEach { facing ->
        val cmd =
            client.command {
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

fun handleProcessResult(
    result: ScrcpyResult,
    operation: String,
) {
    when (result) {
        is ScrcpyResult.Error -> {
            println("❌ $operation failed: ${result.message}")
            println("Exception: ${result.exception.message}")
        }
        is ScrcpyResult.Success -> {
            println("$operation process started. Press Enter to terminate...")

            // Monitor for Enter key in a separate thread
            val keyMonitorThread =
                Thread {
                    try {
                        while (result.process.isAlive) {
                            if (System.`in`.available() > 0) {
                                val input = System.`in`.read()
                                if (input == 10 || input == 13) { // Enter key ASCII codes (LF or CR)
                                    println("\nEnter key detected. Terminating scrcpy...")
                                    result.process.terminate()
                                    break
                                }
                            }
                            Thread.sleep(100)
                        }
                    } catch (e: Exception) {
                        // Ignore exceptions in key monitoring
                    }
                }
            keyMonitorThread.isDaemon = true
            keyMonitorThread.start()
            result.process.waitFor()
        }
    }
}
