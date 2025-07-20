package jp.kaleidot725.scrcpykt.builders

import jp.kaleidot725.scrcpykt.GamepadMode
import jp.kaleidot725.scrcpykt.KeyboardMode
import jp.kaleidot725.scrcpykt.MouseMode
import jp.kaleidot725.scrcpykt.ScrcpyCommand

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