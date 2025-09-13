package jp.kaleidot725.scrcpykt.option

public data class NewDisplay(
    public val width: Int,
    public val height: Int,
    public val density: Int? = null,
) {
    public override fun toString(): String = if (density != null) "${width}x$height/$density" else "${width}x$height"
}
