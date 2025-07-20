package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand

class ControlOptionsBuilder(private val command: ScrcpyCommand) {
    
    fun turnScreenOff() = apply {
        command.turnScreenOff = true
    }
    
    fun stayAwake() = apply {
        command.stayAwake = true
    }
    
    fun powerOffOnClose() = apply {
        command.powerOffOnClose = true
    }
    
    fun showTouches() = apply {
        command.showTouches = true
    }
    
    fun disableScreensaver() = apply {
        command.disableScreensaver = true
    }
    
    fun build(): ScrcpyCommand = command
}