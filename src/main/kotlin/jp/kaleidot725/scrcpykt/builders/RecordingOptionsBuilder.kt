package jp.kaleidot725.scrcpykt.builders

import jp.kaleidot725.scrcpykt.CaptureOrientation
import jp.kaleidot725.scrcpykt.RecordFormat
import jp.kaleidot725.scrcpykt.ScrcpyCommand

class RecordingOptionsBuilder(private val command: ScrcpyCommand) {
    
    fun outputFile(filename: String) = apply {
        command.record = filename
    }
    
    fun format(format: RecordFormat) = apply {
        command.recordFormat = format
    }
    
    fun orientation(orientation: CaptureOrientation) = apply {
        command.captureOrientation = orientation
    }
    
    fun disablePlayback() = apply {
        command.noPlayback = true
    }
    
    fun v4l2Sink(device: String) = apply {
        command.v4l2Sink = device
    }
    
    fun build(): ScrcpyCommand = command
}