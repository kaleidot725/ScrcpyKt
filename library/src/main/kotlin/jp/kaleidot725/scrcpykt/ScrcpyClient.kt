package jp.kaleidot725.scrcpykt

import jp.kaleidot725.scrcpykt.builder.ScrcpyCommandBuilder
import jp.kaleidot725.scrcpykt.option.VideoSource
import java.io.File
import java.io.IOException

class ScrcpyClient(
    private val binaryPath: String = "scrcpy",
) {
    fun execute(
        command: ScrcpyCommand,
        isRecording: Boolean = false,
    ): ScrcpyResult {
        return try {
            // Validate scrcpy executable exists
            val commandList = command.buildCommand()
            if (commandList.isEmpty()) {
                return ScrcpyResult.Error("Invalid scrcpy command", IllegalArgumentException("Command cannot be empty"))
            }

            val processBuilder = ProcessBuilder(commandList)
            processBuilder.setupCommandPath(binaryPath)
            processBuilder.setupOutputRedirection(command)
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

    fun command(configure: ScrcpyCommandBuilder.() -> Unit): ScrcpyCommand = ScrcpyCommandBuilder(binaryPath).apply(configure).build()

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

    private fun ProcessBuilder.setupCommandPath(binaryPath: String?) {
        environment()["PATH"] =
            if (binaryPath != null && binaryPath != "scrcpy") {
                File(binaryPath).parent + File.pathSeparator + System.getenv("PATH")
            } else {
                System.getenv("PATH")
            }
    }

    private fun ProcessBuilder.setupOutputRedirection(command: ScrcpyCommand) {
        command.stdoutFile?.let { stdoutPath ->
            val stdoutFile = File(stdoutPath)
            try {
                stdoutFile.parentFile?.mkdirs()
                redirectOutput(ProcessBuilder.Redirect.to(stdoutFile))
            } catch (e: Exception) {
                throw IOException("Failed to setup stdout redirection to $stdoutPath", e)
            }
        }

        command.stderrFile?.let { stderrPath ->
            val stderrFile = File(stderrPath)
            try {
                stderrFile.parentFile?.mkdirs()
                redirectError(ProcessBuilder.Redirect.to(stderrFile))
            } catch (e: Exception) {
                throw IOException("Failed to setup stderr redirection to $stderrPath", e)
            }
        }
    }

    companion object {
        fun create(): ScrcpyClient = ScrcpyClient()

        fun create(binaryPath: String): ScrcpyClient = ScrcpyClient(binaryPath)
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
