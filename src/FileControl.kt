import java.io.File
import java.io.InputStream

class FileControl {

    fun readText(fn: String): MutableList<String> {
        val inputStream: InputStream = File(fn).inputStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().forEachLine {
            lineList.add(it)
            println(it)
        }

        return lineList
    }

}