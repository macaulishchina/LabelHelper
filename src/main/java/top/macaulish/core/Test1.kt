package top.macaulish.core

import org.junit.Test
import java.util.regex.Pattern

class Test1 {

    var dog:Dog? = null

    @Test
    fun test(){
        println(Pattern.matches("^.*\\d{5,}.xml\$","guangs00664.xml"))
    }

    class Dog(){
        fun eat(){}
    }
}