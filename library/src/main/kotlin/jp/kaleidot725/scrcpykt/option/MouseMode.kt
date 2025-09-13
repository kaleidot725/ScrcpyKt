package jp.kaleidot725.scrcpykt.option

public enum class MouseMode(
    public val value: String,
) {
    DISABLED("disabled"),
    SDK("sdk"),
    UHID("uhid"),
    AOA("aoa"),
}
