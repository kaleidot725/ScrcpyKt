package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.LogLevel

public class ScrcpyCommandBuilder(
    private val binaryPath: String = "scrcpy",
    private val adbPath: String = "adb",
) {
    private val command = ScrcpyCommand(binaryPath = binaryPath, adbPath = adbPath)

    public fun video(configure: VideoOptionsBuilder.() -> Unit): ScrcpyCommandBuilder =
        apply {
            VideoOptionsBuilder(command).configure()
        }

    public fun audio(configure: AudioOptionsBuilder.() -> Unit): ScrcpyCommandBuilder =
        apply {
            AudioOptionsBuilder(command).configure()
        }

    public fun display(configure: DisplayOptionsBuilder.() -> Unit): ScrcpyCommandBuilder =
        apply {
            DisplayOptionsBuilder(command).configure()
        }

    public fun input(configure: InputOptionsBuilder.() -> Unit): ScrcpyCommandBuilder =
        apply {
            InputOptionsBuilder(command).configure()
        }

    public fun camera(configure: CameraOptionsBuilder.() -> Unit): ScrcpyCommandBuilder =
        apply {
            CameraOptionsBuilder(command).configure()
        }

    public fun recording(configure: RecordingOptionsBuilder.() -> Unit): ScrcpyCommandBuilder =
        apply {
            RecordingOptionsBuilder(command).configure()
        }

    public fun connection(configure: ConnectionOptionsBuilder.() -> Unit): ScrcpyCommandBuilder =
        apply {
            ConnectionOptionsBuilder(command).configure()
        }

    public fun control(configure: ControlOptionsBuilder.() -> Unit): ScrcpyCommandBuilder =
        apply {
            ControlOptionsBuilder(command).configure()
        }

    public fun verbosity(level: LogLevel): ScrcpyCommandBuilder =
        apply {
            command.verbosity = level
        }

    public fun renderDriver(driver: String): ScrcpyCommandBuilder =
        apply {
            command.renderDriver = driver
        }

    public fun pushTarget(target: String): ScrcpyCommandBuilder =
        apply {
            command.pushTarget = target
        }

    public fun startApp(packageName: String): ScrcpyCommandBuilder =
        apply {
            command.startApp = packageName
        }

    public fun stdoutFile(filePath: String): ScrcpyCommandBuilder =
        apply {
            command.stdoutFile = filePath
        }

    public fun stderrFile(filePath: String): ScrcpyCommandBuilder =
        apply {
            command.stderrFile = filePath
        }

    public fun outputFiles(
        stdoutPath: String? = null,
        stderrPath: String? = null,
    ): ScrcpyCommandBuilder = apply {
        stdoutPath?.let { command.stdoutFile = it }
        stderrPath?.let { command.stderrFile = it }
    }

    public fun build(): ScrcpyCommand = command
}
