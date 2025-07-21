package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand

class DisplayOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    fun displayId(id: Int) =
        apply {
            command.displayId = id
        }

    fun windowTitle(title: String) =
        apply {
            command.windowTitle = title
        }

    fun windowPosition(
        x: Int,
        y: Int,
    ) = apply {
        command.windowX = x
        command.windowY = y
    }

    fun windowSize(
        width: Int,
        height: Int,
    ) = apply {
        command.windowWidth = width
        command.windowHeight = height
    }

    fun fullscreen() =
        apply {
            command.fullscreen = true
        }

    fun alwaysOnTop() =
        apply {
            command.alwaysOnTop = true
        }

    fun newDisplay(resolution: String) =
        apply {
            command.newDisplay = resolution
        }

    fun build(): ScrcpyCommand = command
}
