package jp.kaleidot725.scrcpykt.builder

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
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ScrcpyCommandBuilderTest {
    @Test
    fun `builder should create command with video options`() {
        val command =
            ScrcpyCommandBuilder()
                .video {
                    bitRate(8000000)
                    maxFps(60)
                    codec(VideoCodec.H265)
                    source(VideoSource.CAMERA)
                }.build()

        assertEquals(8000000, command.videoBitRate)
        assertEquals(60, command.maxFps)
        assertEquals(VideoCodec.H265, command.videoCodec)
        assertEquals(VideoSource.CAMERA, command.videoSource)
    }

    @Test
    fun `builder should create command with audio options`() {
        val command =
            ScrcpyCommandBuilder()
                .audio {
                    bitRate(128000)
                    codec(AudioCodec.OPUS)
                    source(AudioSource.MIC)
                    encoder("custom_encoder")
                }.build()

        assertEquals(128000, command.audioBitRate)
        assertEquals(AudioCodec.OPUS, command.audioCodec)
        assertEquals(AudioSource.MIC, command.audioSource)
        assertEquals("custom_encoder", command.audioEncoder)
    }

    @Test
    fun `builder should create command with display options`() {
        val command =
            ScrcpyCommandBuilder()
                .display {
                    windowTitle("My Scrcpy")
                    windowPosition(100, 200)
                    windowSize(800, 600)
                    fullscreen()
                    alwaysOnTop()
                }.build()

        assertEquals("My Scrcpy", command.windowTitle)
        assertEquals(100, command.windowX)
        assertEquals(200, command.windowY)
        assertEquals(800, command.windowWidth)
        assertEquals(600, command.windowHeight)
        assertTrue(command.fullscreen)
        assertTrue(command.alwaysOnTop)
    }

    @Test
    fun `builder should create command with input options`() {
        val command =
            ScrcpyCommandBuilder()
                .input {
                    keyboard(KeyboardMode.UHID)
                    mouse(MouseMode.SDK)
                    gamepad(GamepadMode.AOA)
                    enableOtg()
                }.build()

        assertEquals(KeyboardMode.UHID, command.keyboard)
        assertEquals(MouseMode.SDK, command.mouse)
        assertEquals(GamepadMode.AOA, command.gamepad)
        assertTrue(command.otg)
    }

    @Test
    fun `builder should create command with camera options`() {
        val command =
            ScrcpyCommandBuilder()
                .camera {
                    size(1920, 1080)
                    facing(CameraFacing.FRONT)
                    id("camera1")
                    fps(30)
                }.build()

        assertEquals("1920x1080", command.cameraSize)
        assertEquals(CameraFacing.FRONT, command.cameraFacing)
        assertEquals("camera1", command.cameraId)
        assertEquals(30, command.cameraFps)
    }

    @Test
    fun `builder should create command with recording options`() {
        val command =
            ScrcpyCommandBuilder()
                .recording {
                    outputFile("output.mp4")
                    format(RecordFormat.MP4)
                    orientation(CaptureOrientation.ROTATION_90)
                    disablePlayback()
                }.build()

        assertEquals("output.mp4", command.record)
        assertEquals(RecordFormat.MP4, command.recordFormat)
        assertEquals(CaptureOrientation.ROTATION_90, command.captureOrientation)
        assertTrue(command.noPlayback)
    }

    @Test
    fun `builder should create command with connection options`() {
        val command =
            ScrcpyCommandBuilder()
                .connection {
                    serial("ABC123")
                    selectUsb()
                    tcpip("192.168.1.100", 5555)
                }.build()

        assertEquals("ABC123", command.serial)
        assertTrue(command.selectUsb)
        assertEquals("192.168.1.100:5555", command.tcpip)
    }

    @Test
    fun `builder should create command with control options`() {
        val command =
            ScrcpyCommandBuilder()
                .control {
                    turnScreenOff()
                    stayAwake()
                    showTouches()
                }.build()

        assertTrue(command.turnScreenOff)
        assertTrue(command.stayAwake)
        assertTrue(command.showTouches)
    }

    @Test
    fun `builder should create complete command with all options`() {
        val command =
            ScrcpyCommandBuilder()
                .video {
                    codec(VideoCodec.H264)
                    maxSize(1920)
                }.audio {
                    codec(AudioCodec.AAC)
                    disableAudio()
                }.recording {
                    outputFile("recording.mp4")
                }.verbosity(LogLevel.DEBUG)
                .startApp("com.example.app")
                .build()

        val cmdList = command.buildCommand()
        assertTrue(cmdList.contains("--video-codec"))
        assertTrue(cmdList.contains("h264"))
        assertTrue(cmdList.contains("--max-size"))
        assertTrue(cmdList.contains("1920"))
        assertTrue(cmdList.contains("--no-audio"))
        assertTrue(cmdList.contains("--record"))
        assertTrue(cmdList.contains("recording.mp4"))
        assertTrue(cmdList.contains("--verbosity"))
        assertTrue(cmdList.contains("debug"))
        assertTrue(cmdList.contains("--start-app"))
        assertTrue(cmdList.contains("com.example.app"))
    }

    @Test
    fun `builder should create command with stdout file output`() {
        val command =
            ScrcpyCommandBuilder()
                .stdoutFile("/tmp/scrcpy_stdout.log")
                .build()

        assertEquals("/tmp/scrcpy_stdout.log", command.stdoutFile)
        assertEquals(null, command.stderrFile)
    }

    @Test
    fun `builder should create command with stderr file output`() {
        val command =
            ScrcpyCommandBuilder()
                .stderrFile("/tmp/scrcpy_stderr.log")
                .build()

        assertEquals(null, command.stdoutFile)
        assertEquals("/tmp/scrcpy_stderr.log", command.stderrFile)
    }

    @Test
    fun `builder should create command with both output files`() {
        val command =
            ScrcpyCommandBuilder()
                .outputFiles(
                    stdoutPath = "/tmp/scrcpy_stdout.log",
                    stderrPath = "/tmp/scrcpy_stderr.log",
                ).build()

        assertEquals("/tmp/scrcpy_stdout.log", command.stdoutFile)
        assertEquals("/tmp/scrcpy_stderr.log", command.stderrFile)
    }

    @Test
    fun `builder should create command with partial output files using outputFiles`() {
        val command1 =
            ScrcpyCommandBuilder()
                .outputFiles(stdoutPath = "/tmp/stdout.log")
                .build()

        val command2 =
            ScrcpyCommandBuilder()
                .outputFiles(stderrPath = "/tmp/stderr.log")
                .build()

        assertEquals("/tmp/stdout.log", command1.stdoutFile)
        assertEquals(null, command1.stderrFile)
        assertEquals(null, command2.stdoutFile)
        assertEquals("/tmp/stderr.log", command2.stderrFile)
    }
}
