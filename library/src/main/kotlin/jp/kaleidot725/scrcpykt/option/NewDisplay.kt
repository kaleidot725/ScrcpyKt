package jp.kaleidot725.scrcpykt.option

data class NewDisplay(
    val width: Int,
    val height: Int,
    val density: Int? = null,
) {
    override fun toString(): String = if (density != null) "${width}x$height/$density" else "${width}x$height"
}
