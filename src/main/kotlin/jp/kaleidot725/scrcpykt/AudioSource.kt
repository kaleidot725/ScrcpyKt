package jp.kaleidot725.scrcpykt

enum class AudioSource(val value: String) {
    OUTPUT("output"),
    MIC("mic"),
    PLAYBACK("playback"),
    VOICE_CALL("voice-call"),
    CAMCORDER("camcorder"),
    VOICE_RECOGNITION("voice-recognition"),
    VOICE_COMMUNICATION("voice-communication"),
    UNPROCESSED("unprocessed")
}