package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand
import jp.kaleidot725.scrcpykt.option.NewDisplay

public class DisplayOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    public fun displayId(id: Int): DisplayOptionsBuilder =
        apply {
            command.displayId = id
        }

    public fun windowTitle(title: String): DisplayOptionsBuilder =
        apply {
            command.windowTitle = title
        }

    public fun windowPosition(
        x: Int,
        y: Int,
    ): DisplayOptionsBuilder = apply {
        command.windowX = x
        command.windowY = y
    }

    public fun windowSize(
        width: Int,
        height: Int,
    ): DisplayOptionsBuilder = apply {
        command.windowWidth = width
        command.windowHeight = height
    }

    public fun fullscreen(): DisplayOptionsBuilder =
        apply {
            command.fullscreen = true
        }

    public fun alwaysOnTop(): DisplayOptionsBuilder =
        apply {
            command.alwaysOnTop = true
        }

    public fun newDisplay(
        width: Int,
        height: Int,
        density: Int? = null,
    ): DisplayOptionsBuilder = apply {
        command.newDisplay = NewDisplay(width, height, density)
    }

    public fun newDisplay(newDisplay: NewDisplay): DisplayOptionsBuilder =
        apply {
            command.newDisplay = newDisplay
        }

    public fun build(): ScrcpyCommand = command
}
