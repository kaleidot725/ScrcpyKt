package jp.kaleidot725.scrcpykt

import jp.kaleidot725.scrcpykt.builder.ScrcpyCommandBuilder
import jp.kaleidot725.scrcpykt.option.VideoSource
import java.io.IOException

class ScrcpyClient {
    fun execute(
        command: ScrcpyCommand,
        isRecording: Boolean = false,
    ): ScrcpyResult {
        return try {
            // Validate scrcpy executable exists
            val commandList = command.buildCommand()
            if (commandList.isEmpty() || commandList[0] != "scrcpy") {
                return ScrcpyResult.Error("Invalid scrcpy command", IllegalArgumentException("Command must start with 'scrcpy'"))
            }

            val processBuilder = ProcessBuilder(commandList)
            val process = processBuilder.start()
            val scrcpyProcess = ScrcpyProcess(process, isRecording)
            ScrcpyResult.Success(scrcpyProcess)
        } catch (e: IllegalArgumentException) {
            ScrcpyResult.Error("Invalid parameter: ${e.message}", e)
        } catch (e: SecurityException) {
            ScrcpyResult.Error("Security error: insufficient permissions", e)
        } catch (e: IOException) {
            ScrcpyResult.Error("Failed to execute scrcpy command (scrcpy not found or not executable)", e)
        } catch (e: InterruptedException) {
            ScrcpyResult.Error("Scrcpy execution was interrupted", e)
        } catch (e: Exception) {
            ScrcpyResult.Error("Unexpected error during scrcpy execution", e)
        }
    }

    fun command(configure: ScrcpyCommandBuilder.() -> Unit): ScrcpyCommand = ScrcpyCommandBuilder().apply(configure).build()

    fun mirror(configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command = command(configure)
        return execute(command)
    }

    fun record(
        outputFile: String,
        configure: ScrcpyCommandBuilder.() -> Unit = {},
    ): ScrcpyResult {
        val command =
            command {
                recording { outputFile(outputFile) }
                configure()
            }
        return execute(command, isRecording = true)
    }

    fun camera(configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command =
            command {
                video { source(VideoSource.CAMERA) }
                configure()
            }
        return execute(command)
    }

    fun otg(configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command =
            command {
                input { enableOtg() }
                configure()
            }
        return execute(command)
    }

    // Legacy methods removed due to immutable ScrcpyCommand
    // Use the new builder-based methods: mirror(), record(), camera(), otg()

    companion object {
        fun create(): ScrcpyClient = ScrcpyClient()
    }
}

sealed class ScrcpyResult {
    data class Success(
        val process: ScrcpyProcess,
    ) : ScrcpyResult()

    data class Error(
        val message: String,
        val exception: Exception,
    ) : ScrcpyResult()
}
