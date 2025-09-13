package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.AudioCodec
import jp.kaleidot725.scrcpykt.option.AudioSource

public class AudioOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    public fun bitRate(bitRate: Int): AudioOptionsBuilder =
        apply {
            command.audioBitRate = bitRate
        }

    public fun buffer(bufferMs: Int): AudioOptionsBuilder =
        apply {
            command.audioBuffer = bufferMs
        }

    public fun codec(codec: AudioCodec): AudioOptionsBuilder =
        apply {
            command.audioCodec = codec
        }

    public fun source(source: AudioSource): AudioOptionsBuilder =
        apply {
            command.audioSource = source
        }

    public fun encoder(encoder: String): AudioOptionsBuilder =
        apply {
            command.audioEncoder = encoder
        }

    public fun disableAudio(): AudioOptionsBuilder =
        apply {
            command.noAudio = true
        }

    public fun build(): ScrcpyCommand = command
}
