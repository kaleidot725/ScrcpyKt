package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.NewDisplay

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

    fun newDisplay(
        width: Int,
        height: Int,
        density: Int? = null,
    ) = apply {
        command.newDisplay = NewDisplay(width, height, density)
    }

    fun newDisplay(newDisplay: NewDisplay) =
        apply {
            command.newDisplay = newDisplay
        }

    fun build(): ScrcpyCommand = command
}
