package jp.kaleidot725.scrcpykt.option

public enum class LogLevel(
    public val value: String,
) {
    VERBOSE("verbose"),
    DEBUG("debug"),
    INFO("info"),
    WARN("warn"),
    ERROR("error"),
}
