package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.GamepadMode
import jp.kaleidot725.scrcpykt.option.KeyboardMode
import jp.kaleidot725.scrcpykt.option.MouseMode

public class InputOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    public fun keyboard(mode: KeyboardMode): InputOptionsBuilder =
        apply {
            command.keyboard = mode
        }

    public fun mouse(mode: MouseMode): InputOptionsBuilder =
        apply {
            command.mouse = mode
        }

    public fun gamepad(mode: GamepadMode): InputOptionsBuilder =
        apply {
            command.gamepad = mode
        }

    public fun enableOtg(): InputOptionsBuilder =
        apply {
            command.otg = true
        }

    public fun build(): ScrcpyCommand = command
}
