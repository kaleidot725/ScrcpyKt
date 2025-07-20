package jp.kaleidot725.scrcpykt

import jp.kaleidot725.scrcpykt.builder.ScrcpyCommandBuilder
import jp.kaleidot725.scrcpykt.option.VideoSource
import java.io.IOException

class ScrcpyClient {
    
    fun execute(command: ScrcpyCommand): ScrcpyResult {
        return try {
            val processBuilder = ProcessBuilder(command.buildCommand())
            val process = processBuilder.start()
            
            val exitCode = process.waitFor()
            val output = process.inputStream.bufferedReader().readText()
            val error = process.errorStream.bufferedReader().readText()
            
            ScrcpyResult.Success(exitCode, output, error)
        } catch (e: IOException) {
            ScrcpyResult.Error("Failed to execute scrcpy command", e)
        } catch (e: InterruptedException) {
            ScrcpyResult.Error("Scrcpy execution was interrupted", e)
        } catch (e: Exception) {
            ScrcpyResult.Error("Unexpected error during scrcpy execution", e)
        }
    }
    
    fun executeAsync(command: ScrcpyCommand, callback: (ScrcpyResult) -> Unit) {
        Thread {
            callback(execute(command))
        }.start()
    }
    
    fun command(configure: ScrcpyCommandBuilder.() -> Unit): ScrcpyCommand {
        return ScrcpyCommandBuilder().apply(configure).build()
    }
    
    fun mirror(configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command = command(configure)
        return execute(command)
    }
    
    fun record(outputFile: String, configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command = command {
            recording { outputFile(outputFile) }
            configure()
        }
        return execute(command)
    }
    
    fun camera(configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command = command {
            video { source(VideoSource.CAMERA) }
            configure()
        }
        return execute(command)
    }
    
    fun otg(configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command = command {
            input { enableOtg() }
            configure()
        }
        return execute(command)
    }
    
    // Legacy methods for backward compatibility
    fun startMirroring(configure: ScrcpyCommand.() -> Unit = {}): ScrcpyResult {
        val command = ScrcpyCommand().apply(configure)
        return execute(command)
    }
    
    fun recordScreen(outputFile: String, configure: ScrcpyCommand.() -> Unit = {}): ScrcpyResult {
        val command = ScrcpyCommand().apply {
            record = outputFile
            configure()
        }
        return execute(command)
    }
    
    fun mirrorCamera(configure: ScrcpyCommand.() -> Unit = {}): ScrcpyResult {
        val command = ScrcpyCommand().apply {
            videoSource = VideoSource.CAMERA
            configure()
        }
        return execute(command)
    }
    
    fun connectOtg(configure: ScrcpyCommand.() -> Unit = {}): ScrcpyResult {
        val command = ScrcpyCommand().apply {
            otg = true
            configure()
        }
        return execute(command)
    }
    
    companion object {
        fun create(): ScrcpyClient = ScrcpyClient()
    }
}

sealed class ScrcpyResult {
    data class Success(
        val exitCode: Int,
        val output: String,
        val error: String
    ) : ScrcpyResult()
    
    data class Error(
        val message: String,
        val exception: Exception
    ) : ScrcpyResult()
}