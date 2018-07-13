package top.macaulish.core

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.logging.LogFactory;
import java.io.File

abstract class AbstractFilesListener : FileAlterationListenerAdaptor() {

    private val log = LogFactory.getLog(AbstractFilesListener::class.java)
    private var ignore = false
    /**
     * 定义复制文件的规则
     */
    abstract fun smartCopyFile(file : File):Boolean


    override fun onDirectoryDelete(directory: File?) {
        super.onDirectoryDelete(directory)
        log.info("The directory ${directory?.path} has been deleted.")
    }

    override fun onFileCreate(file: File?) {
        super.onFileCreate(file)
        log.info("The file ${file?.name} has been created in ${file?.parent}.")
        if(ignore){
            ignore = false
            return
        }
        if(file!=null){ ignore = smartCopyFile(file) }
    }

    override fun onFileChange(file: File?) {
        super.onFileChange(file)
        log.info("The file ${file?.name} has been changed in ${file?.parent}.")
        if(ignore){
            ignore = false
            return
        }
        if(file!=null){ ignore = smartCopyFile(file) }
    }

    override fun onDirectoryCreate(directory: File?) {
        super.onDirectoryCreate(directory)
        log.info("The directory ${directory?.path} has been created.")
    }

    override fun onDirectoryChange(directory: File?) {
        super.onDirectoryChange(directory)
        log.info("The directory ${directory?.path} has been changed.")
    }

    override fun onFileDelete(file: File?) {
        super.onFileDelete(file)
        log.info("The file ${file?.name} has been deleted from ${file?.parent}.")
    }

}