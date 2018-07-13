package top.macaulish

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel
import java.util.regex.Pattern

object FileKits {

    /**
     * 复制文件[source]到[destination]，过程中可能抛出异常
     */
    fun copyFile(source: File, destination: File) {
        var inputChannel: FileChannel? = null
        var outputChannel: FileChannel? = null
        try {
            inputChannel = FileInputStream(source).channel
            outputChannel = FileOutputStream(destination).channel
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size())
        } catch (e: Exception) {
            throw RuntimeException("Failed to copy file from ${source.path} to ${destination.path}$")
        } finally {
            inputChannel?.close()
            outputChannel?.close()
        }
    }

}