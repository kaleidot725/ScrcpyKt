package jp.kaleidot725.scrcpykt.builder

import jp.kaleidot725.scrcpykt.ScrcpyCommand

class ConnectionOptionsBuilder(private val command: ScrcpyCommand) {
    
    fun serial(serial: String) = apply {
        command.serial = serial
    }
    
    fun selectUsb() = apply {
        command.selectUsb = true
    }
    
    fun selectTcpip() = apply {
        command.selectTcpip = true
    }
    
    fun tcpip(address: String) = apply {
        command.tcpip = address
    }
    
    fun tcpip(host: String, port: Int) = apply {
        command.tcpip = "$host:$port"
    }
    
    fun build(): ScrcpyCommand = command
}