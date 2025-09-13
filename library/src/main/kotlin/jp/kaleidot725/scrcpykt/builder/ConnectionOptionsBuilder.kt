package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand

public class ConnectionOptionsBuilder(
    private val command: ScrcpyCommand,
) {
    public fun serial(serial: String): ConnectionOptionsBuilder =
        apply {
            command.serial = serial
        }

    public fun selectUsb(): ConnectionOptionsBuilder =
        apply {
            command.selectUsb = true
        }

    public fun selectTcpip(): ConnectionOptionsBuilder =
        apply {
            command.selectTcpip = true
        }

    public fun tcpip(address: String): ConnectionOptionsBuilder =
        apply {
            command.tcpip = address
        }

    public fun tcpip(
        host: String,
        port: Int,
    ): ConnectionOptionsBuilder = apply {
        command.tcpip = "$host:$port"
    }

    public fun build(): ScrcpyCommand = command
}
