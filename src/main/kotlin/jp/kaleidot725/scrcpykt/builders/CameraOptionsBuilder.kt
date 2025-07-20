package jp.kaleidot725.scrcpykt.builders

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.CameraFacing

class CameraOptionsBuilder(private val command: ScrcpyCommand) {
    
    fun size(width: Int, height: Int) = apply {
        command.cameraSize = "${width}x${height}"
    }
    
    fun size(resolution: String) = apply {
        command.cameraSize = resolution
    }
    
    fun facing(facing: CameraFacing) = apply {
        command.cameraFacing = facing
    }
    
    fun id(id: String) = apply {
        command.cameraId = id
    }
    
    fun fps(fps: Int) = apply {
        command.cameraFps = fps
    }
    
    fun build(): ScrcpyCommand = command
}