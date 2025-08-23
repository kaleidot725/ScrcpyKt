package jp.kaleidot725.scrcpykt

import jp.kaleidot725.scrcpykt.option.AudioCodec
import jp.kaleidot725.scrcpykt.option.AudioSource
import jp.kaleidot725.scrcpykt.option.CaptureOrientation
import jp.kaleidot725.scrcpykt.option.RecordFormat
import jp.kaleidot725.scrcpykt.option.VideoCodec
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ScrcpyCommandTest {
    @Test
    fun `buildCommand should generate basic scrcpy command`() {
        val command = ScrcpyCommand()
        val result = command.buildCommand()

        assertEquals(listOf("scrcpy"), result)
    }

    @Test
    fun `buildCommand should use custom binary path`() {
        val command = ScrcpyCommand(binaryPath = "/custom/path/to/scrcpy")
        val result = command.buildCommand()

        assertEquals("/custom/path/to/scrcpy", result[0])
    }

    @Test
    fun `buildCommand should include video options`() {
        val command =
            ScrcpyCommand(
                videoBitRate = 8000000,
                maxFps = 60,
                maxSize = 1920,
                videoCodec = VideoCodec.H265,
            )
        val result = command.buildCommand()

        assertTrue(result.contains("--video-bit-rate"))
        assertTrue(result.contains("8000000"))
        assertTrue(result.contains("--max-fps"))
        assertTrue(result.contains("60"))
        assertTrue(result.contains("--max-size"))
        assertTrue(result.contains("1920"))
        assertTrue(result.contains("--video-codec"))
        assertTrue(result.contains("h265"))
    }

    @Test
    fun `buildCommand should include audio options`() {
        val command =
            ScrcpyCommand(
                audioBitRate = 128000,
                audioCodec = AudioCodec.OPUS,
                audioSource = AudioSource.MIC,
            )
        val result = command.buildCommand()

        assertTrue(result.contains("--audio-bit-rate"))
        assertTrue(result.contains("128000"))
        assertTrue(result.contains("--audio-codec"))
        assertTrue(result.contains("opus"))
        assertTrue(result.contains("--audio-source"))
        assertTrue(result.contains("mic"))
    }

    @Test
    fun `buildCommand should include boolean flags`() {
        val command =
            ScrcpyCommand(
                noVideo = true,
                noAudio = true,
                fullscreen = true,
                alwaysOnTop = true,
                otg = true,
            )
        val result = command.buildCommand()

        assertTrue(result.contains("--no-video"))
        assertTrue(result.contains("--no-audio"))
        assertTrue(result.contains("--fullscreen"))
        assertTrue(result.contains("--always-on-top"))
        assertTrue(result.contains("--otg"))
    }

    @Test
    fun `buildCommand should include recording options`() {
        val command =
            ScrcpyCommand(
                record = "output.mp4",
                recordFormat = RecordFormat.MP4,
                captureOrientation = CaptureOrientation.ROTATION_90,
            )
        val result = command.buildCommand()

        assertTrue(result.contains("--record"))
        assertTrue(result.contains("output.mp4"))
        assertTrue(result.contains("--record-format"))
        assertTrue(result.contains("mp4"))
        assertTrue(result.contains("--capture-orientation"))
        assertTrue(result.contains("90"))
    }

    @Test
    fun `buildCommand should include connection options`() {
        val command =
            ScrcpyCommand(
                serial = "ABC123",
                selectUsb = true,
                tcpip = "192.168.1.100:5555",
            )
        val result = command.buildCommand()

        assertTrue(result.contains("--serial"))
        assertTrue(result.contains("ABC123"))
        assertTrue(result.contains("--select-usb"))
        assertTrue(result.contains("--tcpip"))
        assertTrue(result.contains("192.168.1.100:5555"))
    }
}
