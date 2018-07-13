package top.macaulish.core

import org.apache.commons.logging.LogFactory
import top.macaulish.FileKits
import java.io.File
import java.util.regex.Pattern
import java.text.DecimalFormat



class SimpleLabelFilesListener :AbstractFilesListener() {

    private val log = LogFactory.getLog(SimpleLabelFilesListener::class.java)

    private val regex : String = DEFAULT_FILE_PATTERN

    override fun smartCopyFile(file: File):Boolean{
        if(Pattern.matches(regex,file.name)){
            log.info("matches with file ${file.name}")
            val length = file.name.length
            val num = file.name.substring(length-9,length-4).toIntOrNull() ?: return false
            val df = DecimalFormat("00000")
            val newFileName = file.name.replace("${df.format(num)}.xml","${df.format(num+1)}.xml")
            val newFile = File(file.parent,newFileName)
            if(newFile.exists()) return false
            try {
                FileKits.copyFile(file, newFile)
                log.info("Succeeded to copy file file ${newFile.name} from ${file.name}.")
                return true
            }catch (e:Exception){
                log.error("Failed to copy file ${file.name} in ${file.parent}.")
            }
        }
        return false
    }

    companion object {
        private val DEFAULT_FILE_PATTERN = "^.*\\d{5,}.xml\$" //匹配以至少五个数字加".jpg"结尾的正则表达式
    }
}