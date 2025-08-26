package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.LogLevel

class ScrcpyCommandBuilder(
    private val binaryPath: String = "scrcpy",
) {
    private val command = ScrcpyCommand(binaryPath = binaryPath)

    fun video(configure: VideoOptionsBuilder.() -> Unit) =
        apply {
            VideoOptionsBuilder(command).configure()
        }

    fun audio(configure: AudioOptionsBuilder.() -> Unit) =
        apply {
            AudioOptionsBuilder(command).configure()
        }

    fun display(configure: DisplayOptionsBuilder.() -> Unit) =
        apply {
            DisplayOptionsBuilder(command).configure()
        }

    fun input(configure: InputOptionsBuilder.() -> Unit) =
        apply {
            InputOptionsBuilder(command).configure()
        }

    fun camera(configure: CameraOptionsBuilder.() -> Unit) =
        apply {
            CameraOptionsBuilder(command).configure()
        }

    fun recording(configure: RecordingOptionsBuilder.() -> Unit) =
        apply {
            RecordingOptionsBuilder(command).configure()
        }

    fun connection(configure: ConnectionOptionsBuilder.() -> Unit) =
        apply {
            ConnectionOptionsBuilder(command).configure()
        }

    fun control(configure: ControlOptionsBuilder.() -> Unit) =
        apply {
            ControlOptionsBuilder(command).configure()
        }

    fun verbosity(level: LogLevel) =
        apply {
            command.verbosity = level
        }

    fun renderDriver(driver: String) =
        apply {
            command.renderDriver = driver
        }

    fun pushTarget(target: String) =
        apply {
            command.pushTarget = target
        }

    fun startApp(packageName: String) =
        apply {
            command.startApp = packageName
        }

    fun stdoutFile(filePath: String) =
        apply {
            command.stdoutFile = filePath
        }

    fun stderrFile(filePath: String) =
        apply {
            command.stderrFile = filePath
        }

    fun outputFiles(
        stdoutPath: String? = null,
        stderrPath: String? = null,
    ) = apply {
        stdoutPath?.let { command.stdoutFile = it }
        stderrPath?.let { command.stderrFile = it }
    }

    fun adbPath(path: String) =
        apply {
            command.adbPath = path
        }

    fun build(): ScrcpyCommand = command
}
