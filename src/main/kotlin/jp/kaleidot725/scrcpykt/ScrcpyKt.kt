package jp.kaleidot725.scrcpykt

import jp.kaleidot725.scrcpykt.builders.ScrcpyCommandBuilder

object ScrcpyKt {
    
    fun client(): ScrcpyClient = ScrcpyClient()
    
    fun command(configure: ScrcpyCommandBuilder.() -> Unit): ScrcpyCommand {
        return ScrcpyCommandBuilder().apply(configure).build()
    }
    
    fun mirror(configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command = command(configure)
        return client().execute(command)
    }
    
    fun record(outputFile: String, configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command = command {
            recording { outputFile(outputFile) }
            configure()
        }
        return client().execute(command)
    }
    
    fun camera(configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command = command {
            video { source(VideoSource.CAMERA) }
            configure()
        }
        return client().execute(command)
    }
    
    fun otg(configure: ScrcpyCommandBuilder.() -> Unit = {}): ScrcpyResult {
        val command = command {
            input { enableOtg() }
            configure()
        }
        return client().execute(command)
    }
}