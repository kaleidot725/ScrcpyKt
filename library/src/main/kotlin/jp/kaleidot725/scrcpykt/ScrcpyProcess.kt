package jp.kaleidot725.scrcpykt

import java.io.Closeable
import java.io.InputStream
import java.util.concurrent.TimeUnit

/**
 * Wrapper for scrcpy process with specialized termination handling
 * @param process The underlying Process instance
 * @param isRecording Whether this process is performing recording
 */
public class ScrcpyProcess(
    private val process: Process,
    private val isRecording: Boolean = false,
) : Closeable {
    /**
     * Check if the process is alive
     */
    public val isAlive: Boolean
        get() = process.isAlive

    /**
     * Get the exit value of the process
     */
    public val exitValue: Int
        get() = process.exitValue()

    /**
     * Wait for the process to complete and return the exit code
     */
    public fun waitFor(): Int = process.waitFor()

    /**
     * Get the input stream of the process
     */
    public val inputStream: InputStream?
        get() = process.inputStream

    /**
     * Get the error stream of the process
     */
    public val errorStream: InputStream?
        get() = process.errorStream

    /**
     * Terminate the scrcpy process with appropriate method based on mode
     *
     * For recording processes: Sends SIGTERM to allow graceful recording finalization
     * For non-recording processes: Forces immediate termination
     */
    public fun terminate() {
        if (isRecording) {
            // For recording, send SIGTERM to allow graceful shutdown and file finalization
            process.destroy()

            // Wait a short time for graceful shutdown
            try {
                val terminated = process.waitFor(3, TimeUnit.SECONDS)
                if (!terminated) {
                    // If graceful shutdown failed, force termination
                    process.destroyForcibly()
                }
            } catch (e: InterruptedException) {
                // If interrupted, force termination
                process.destroyForcibly()
                Thread.currentThread().interrupt()
            }
        } else {
            // For non-recording processes, immediately force termination
            process.destroyForcibly()
        }
    }

    /**
     * Force immediate termination regardless of mode
     */
    public fun forceTerminate() {
        process.destroyForcibly()
    }

    /**
     * Send graceful termination signal
     */
    public fun gracefulTerminate() {
        process.destroy()
    }

    /**
     * Implements Closeable interface - automatically terminates the process
     */
    override fun close() {
        terminate()
    }
}
