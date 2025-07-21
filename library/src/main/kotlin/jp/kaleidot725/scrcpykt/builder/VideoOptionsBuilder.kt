package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.VideoCodec
import jp.kaleidot725.scrcpykt.option.VideoSource

class VideoOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    fun bitRate(bitRate: Int) =
        apply {
            command.videoBitRate = bitRate
        }

    fun maxFps(fps: Int) =
        apply {
            command.maxFps = fps
        }

    fun maxSize(size: Int) =
        apply {
            command.maxSize = size
        }

    fun codec(codec: VideoCodec) =
        apply {
            command.videoCodec = codec
        }

    fun source(source: VideoSource) =
        apply {
            command.videoSource = source
        }

    fun encoder(encoder: String) =
        apply {
            command.videoEncoder = encoder
        }

    fun disableVideo() =
        apply {
            command.noVideo = true
        }

    fun build(): ScrcpyCommand = command
}
