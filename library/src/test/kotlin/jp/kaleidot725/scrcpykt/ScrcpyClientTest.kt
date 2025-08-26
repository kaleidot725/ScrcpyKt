package jp.kaleidot725.scrcpykt

import jp.kaleidot725.scrcpykt.builder.ScrcpyCommandBuilder
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScrcpyClientTest {
    @Test
    fun `ScrcpyClient should use default binary path`() {
        val client = ScrcpyClient.create()
        val command = client.command { }
        val commandList = command.buildCommand()

        assertEquals("scrcpy", commandList[0])
    }

    @Test
    fun `ScrcpyClient should use custom binary path`() {
        val customPath = "/custom/path/to/scrcpy"
        val client = ScrcpyClient.create(customPath)
        val command = client.command { }
        val commandList = command.buildCommand()

        assertEquals(customPath, commandList[0])
    }

    @Test
    fun `ScrcpyClient constructor should accept binary path`() {
        val customPath = "/another/path/to/scrcpy"
        val client = ScrcpyClient(customPath)
        val command = client.command { }
        val commandList = command.buildCommand()

        assertEquals(customPath, commandList[0])
    }

    @Test
    fun `ScrcpyCommandBuilder should use custom binary path`() {
        val customPath = "/builder/path/to/scrcpy"
        val builder = ScrcpyCommandBuilder(customPath)
        val command = builder.build()
        val commandList = command.buildCommand()

        assertEquals(customPath, commandList[0])
    }
}
