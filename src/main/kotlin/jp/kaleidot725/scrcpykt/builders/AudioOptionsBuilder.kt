package jp.kaleidot725.scrcpykt.builders

import jp.kaleidot725.scrcpykt.AudioCodec
import jp.kaleidot725.scrcpykt.AudioSource
import jp.kaleidot725.scrcpykt.ScrcpyCommand

class AudioOptionsBuilder(private val command: ScrcpyCommand) {
    
    fun bitRate(bitRate: Int) = apply {
        command.audioBitRate = bitRate
    }
    
    fun codec(codec: AudioCodec) = apply {
        command.audioCodec = codec
    }
    
    fun source(source: AudioSource) = apply {
        command.audioSource = source
    }
    
    fun encoder(encoder: String) = apply {
        command.audioEncoder = encoder
    }
    
    fun disableAudio() = apply {
        command.noAudio = true
    }
    
    fun build(): ScrcpyCommand = command
}