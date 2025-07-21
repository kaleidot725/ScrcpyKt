package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.AudioCodec
import jp.kaleidot725.scrcpykt.option.AudioSource

class AudioOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    fun bitRate(bitRate: Int) =
        apply {
            command.audioBitRate = bitRate
        }

    fun codec(codec: AudioCodec) =
        apply {
            command.audioCodec = codec
        }

    fun source(source: AudioSource) =
        apply {
            command.audioSource = source
        }

    fun encoder(encoder: String) =
        apply {
            command.audioEncoder = encoder
        }

    fun disableAudio() =
        apply {
            command.noAudio = true
        }

    fun build(): ScrcpyCommand = command
}
