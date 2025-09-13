package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand

public class ControlOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    public fun turnScreenOff(): ControlOptionsBuilder =
        apply {
            command.turnScreenOff = true
        }

    public fun stayAwake(): ControlOptionsBuilder =
        apply {
            command.stayAwake = true
        }

    public fun powerOffOnClose(): ControlOptionsBuilder =
        apply {
            command.powerOffOnClose = true
        }

    public fun showTouches(): ControlOptionsBuilder =
        apply {
            command.showTouches = true
        }

    public fun disableScreensaver(): ControlOptionsBuilder =
        apply {
            command.disableScreensaver = true
        }

    public fun build(): ScrcpyCommand = command
}
