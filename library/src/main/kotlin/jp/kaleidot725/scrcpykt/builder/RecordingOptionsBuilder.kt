package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.CaptureOrientation
import jp.kaleidot725.scrcpykt.option.RecordFormat

public class RecordingOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    public fun outputFile(filename: String): RecordingOptionsBuilder =
        apply {
            command.record = filename
        }

    public fun format(format: RecordFormat): RecordingOptionsBuilder =
        apply {
            command.recordFormat = format
        }

    public fun orientation(orientation: CaptureOrientation): RecordingOptionsBuilder =
        apply {
            command.captureOrientation = orientation
        }

    public fun disablePlayback(): RecordingOptionsBuilder =
        apply {
            command.noPlayback = true
        }

    public fun v4l2Sink(device: String): RecordingOptionsBuilder =
        apply {
            command.v4l2Sink = device
        }

    public fun build(): ScrcpyCommand = command
}
