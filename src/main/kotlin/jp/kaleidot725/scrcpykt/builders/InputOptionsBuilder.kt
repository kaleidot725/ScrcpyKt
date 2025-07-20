package jp.kaleidot725.scrcpykt.builders

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.GamepadMode
import jp.kaleidot725.scrcpykt.option.KeyboardMode
import jp.kaleidot725.scrcpykt.option.MouseMode

class InputOptionsBuilder(private val command: ScrcpyCommand) {
    
    fun keyboard(mode: KeyboardMode) = apply {
        command.keyboard = mode
    }
    
    fun mouse(mode: MouseMode) = apply {
        command.mouse = mode
    }
    
    fun gamepad(mode: GamepadMode) = apply {
        command.gamepad = mode
    }
    
    fun enableOtg() = apply {
        command.otg = true
    }
    
    fun build(): ScrcpyCommand = command
}