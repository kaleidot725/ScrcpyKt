package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.CameraFacing

public class CameraOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    public fun size(
        width: Int,
        height: Int,
    ): CameraOptionsBuilder = apply {
        command.cameraSize = "${width}x$height"
    }

    public fun size(resolution: String): CameraOptionsBuilder =
        apply {
            command.cameraSize = resolution
        }

    public fun facing(facing: CameraFacing): CameraOptionsBuilder =
        apply {
            command.cameraFacing = facing
        }

    public fun id(id: String): CameraOptionsBuilder =
        apply {
            command.cameraId = id
        }

    public fun fps(fps: Int): CameraOptionsBuilder =
        apply {
            command.cameraFps = fps
        }

    public fun build(): ScrcpyCommand = command
}
