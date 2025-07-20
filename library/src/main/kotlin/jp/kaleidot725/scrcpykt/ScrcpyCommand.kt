package jp.kaleidot725.scrcpykt

import jp.kaleidot725.scrcpykt.option.*

data class ScrcpyCommand(
    // Video options
    var videoBitRate: Int? = null,
    var maxFps: Int? = null,
    var maxSize: Int? = null,
    var videoCodec: VideoCodec? = null,
    var videoSource: VideoSource? = null,
    var videoEncoder: String? = null,
    var noVideo: Boolean = false,
    
    // Audio options
    var audioBitRate: Int? = null,
    var audioCodec: AudioCodec? = null,
    var audioSource: AudioSource? = null,
    var audioEncoder: String? = null,
    var noAudio: Boolean = false,
    
    // Display options
    var displayId: Int? = null,
    var windowTitle: String? = null,
    var windowX: Int? = null,
    var windowY: Int? = null,
    var windowWidth: Int? = null,
    var windowHeight: Int? = null,
    var fullscreen: Boolean = false,
    var alwaysOnTop: Boolean = false,
    var newDisplay: String? = null,
    
    // Input options
    var keyboard: KeyboardMode? = null,
    var mouse: MouseMode? = null,
    var gamepad: GamepadMode? = null,
    var otg: Boolean = false,
    
    // Camera options
    var cameraSize: String? = null,
    var cameraFacing: CameraFacing? = null,
    var cameraId: String? = null,
    var cameraFps: Int? = null,
    
    // Recording options
    var record: String? = null,
    var recordFormat: RecordFormat? = null,
    var captureOrientation: CaptureOrientation? = null,
    var noPlayback: Boolean = false,
    var v4l2Sink: String? = null,
    
    // Connection options
    var serial: String? = null,
    var selectUsb: Boolean = false,
    var selectTcpip: Boolean = false,
    var tcpip: String? = null,
    
    // Control options
    var turnScreenOff: Boolean = false,
    var stayAwake: Boolean = false,
    var powerOffOnClose: Boolean = false,
    var showTouches: Boolean = false,
    var disableScreensaver: Boolean = false,
    
    // Other options
    var verbosity: LogLevel? = null,
    var renderDriver: String? = null,
    var pushTarget: String? = null,
    var startApp: String? = null
) {
    fun buildCommand(): List<String> {
        val command = mutableListOf("scrcpy")
        
        videoBitRate?.let { command.addAll(listOf("--video-bit-rate", it.toString())) }
        maxFps?.let { command.addAll(listOf("--max-fps", it.toString())) }
        maxSize?.let { command.addAll(listOf("--max-size", it.toString())) }
        videoCodec?.let { command.addAll(listOf("--video-codec", it.value)) }
        videoSource?.let { command.addAll(listOf("--video-source", it.value)) }
        videoEncoder?.let { command.addAll(listOf("--video-encoder", it)) }
        if (noVideo) command.add("--no-video")
        
        audioBitRate?.let { command.addAll(listOf("--audio-bit-rate", it.toString())) }
        audioCodec?.let { command.addAll(listOf("--audio-codec", it.value)) }
        audioSource?.let { command.addAll(listOf("--audio-source", it.value)) }
        audioEncoder?.let { command.addAll(listOf("--audio-encoder", it)) }
        if (noAudio) command.add("--no-audio")
        
        displayId?.let { command.addAll(listOf("--display-id", it.toString())) }
        windowTitle?.let { command.addAll(listOf("--window-title", it)) }
        windowX?.let { command.addAll(listOf("--window-x", it.toString())) }
        windowY?.let { command.addAll(listOf("--window-y", it.toString())) }
        windowWidth?.let { command.addAll(listOf("--window-width", it.toString())) }
        windowHeight?.let { command.addAll(listOf("--window-height", it.toString())) }
        if (fullscreen) command.add("--fullscreen")
        if (alwaysOnTop) command.add("--always-on-top")
        newDisplay?.let { command.addAll(listOf("--new-display", it)) }
        
        keyboard?.let { command.addAll(listOf("--keyboard", it.value)) }
        mouse?.let { command.addAll(listOf("--mouse", it.value)) }
        gamepad?.let { command.addAll(listOf("--gamepad", it.value)) }
        if (otg) command.add("--otg")
        
        cameraSize?.let { command.addAll(listOf("--camera-size", it)) }
        cameraFacing?.let { command.addAll(listOf("--camera-facing", it.value)) }
        cameraId?.let { command.addAll(listOf("--camera-id", it)) }
        cameraFps?.let { command.addAll(listOf("--camera-fps", it.toString())) }
        
        record?.let { command.addAll(listOf("--record", it)) }
        recordFormat?.let { command.addAll(listOf("--record-format", it.value)) }
        captureOrientation?.let { command.addAll(listOf("--capture-orientation", it.value)) }
        if (noPlayback) command.add("--no-playback")
        v4l2Sink?.let { command.addAll(listOf("--v4l2-sink", it)) }
        
        serial?.let { command.addAll(listOf("--serial", it)) }
        if (selectUsb) command.add("--select-usb")
        if (selectTcpip) command.add("--select-tcpip")
        tcpip?.let { command.addAll(listOf("--tcpip", it)) }
        
        if (turnScreenOff) command.add("--turn-screen-off")
        if (stayAwake) command.add("--stay-awake")
        if (powerOffOnClose) command.add("--power-off-on-close")
        if (showTouches) command.add("--show-touches")
        if (disableScreensaver) command.add("--disable-screensaver")
        
        verbosity?.let { command.addAll(listOf("--verbosity", it.value)) }
        renderDriver?.let { command.addAll(listOf("--render-driver", it)) }
        pushTarget?.let { command.addAll(listOf("--push-target", it)) }
        startApp?.let { command.addAll(listOf("--start-app", it)) }
        
        return command
    }
}