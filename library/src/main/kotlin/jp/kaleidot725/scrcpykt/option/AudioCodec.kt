package jp.kaleidot725.scrcpykt.option

public enum class AudioCodec(
    public val value: String,
) {
    OPUS("opus"),
    AAC("aac"),
    FLAC("flac"),
    RAW("raw"),
}
