import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.InputStream
import java.lang.Exception

class FileControl {

    fun readText(fn: String): MutableList<String> {
        val inputStream: InputStream = File(fn).inputStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().forEachLine {
            lineList.add(it)
            println(it)
        }

        inputStream.close()

        return lineList
    }

    fun saveText(fn: String?, text: MutableList<String>) {
        try {
            val file = File("$fn")

            file.createNewFile()

            val writer = BufferedWriter(FileWriter(file))

            for(item in text) {
                writer.append("$item\n")
            }

            writer.close()

        } catch (e: Exception) {
            println("파일 ${fn}을(를) 저장하는데 실패했습니다\n$e")
            return
        }

    }

}