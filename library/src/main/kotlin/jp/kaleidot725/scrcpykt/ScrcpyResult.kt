package jp.kaleidot725.scrcpykt

public sealed class ScrcpyResult {
    public data class Success(
        val process: ScrcpyProcess,
    ) : ScrcpyResult()

    public data class Error(
        val message: String,
        val exception: Exception,
    ) : ScrcpyResult()
}
