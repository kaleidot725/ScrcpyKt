package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.VideoCodec
import jp.kaleidot725.scrcpykt.option.VideoSource

public class VideoOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    public fun bitRate(bitRate: Int): VideoOptionsBuilder =
        apply {
            command.videoBitRate = bitRate
        }

    public fun maxFps(fps: Int): VideoOptionsBuilder =
        apply {
            command.maxFps = fps
        }

    public fun maxSize(size: Int): VideoOptionsBuilder =
        apply {
            command.maxSize = size
        }

    public fun codec(codec: VideoCodec): VideoOptionsBuilder =
        apply {
            command.videoCodec = codec
        }

    public fun source(source: VideoSource): VideoOptionsBuilder =
        apply {
            command.videoSource = source
        }

    public fun encoder(encoder: String): VideoOptionsBuilder =
        apply {
            command.videoEncoder = encoder
        }

    public fun disableVideo(): VideoOptionsBuilder =
        apply {
            command.noVideo = true
        }

    public fun build(): ScrcpyCommand = command
}
