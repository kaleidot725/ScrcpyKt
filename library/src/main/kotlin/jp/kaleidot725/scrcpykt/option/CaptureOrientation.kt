package jp.kaleidot725.scrcpykt.option

public enum class CaptureOrientation(
    public val value: String,
) {
    ROTATION_0("0"),
    ROTATION_90("90"),
    ROTATION_180("180"),
    ROTATION_270("270"),
    FLIP_0("flip0"),
    FLIP_90("flip90"),
    FLIP_180("flip180"),
    FLIP_270("flip270"),
}
