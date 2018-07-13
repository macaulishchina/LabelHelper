package top.macaulish.core

import java.io.File
import java.io.FileFilter

class SimpleFileFilter : FileFilter {
    override fun accept(pathname: File?): Boolean {
        return true
    }
}