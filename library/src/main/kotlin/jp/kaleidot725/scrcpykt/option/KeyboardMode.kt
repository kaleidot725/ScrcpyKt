package jp.kaleidot725.scrcpykt.option

public enum class KeyboardMode(
    public val value: String,
) {
    DISABLED("disabled"),
    SDK("sdk"),
    UHID("uhid"),
    AOA("aoa"),
}
