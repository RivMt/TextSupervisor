import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.InputStream
import java.lang.Exception

class FileControl {
    
    fun readName(fn: String): MutableList<NameObject> {
        val inputStream: InputStream = File(fn).inputStream()
        val nameList = mutableListOf<NameObject>()

        inputStream.bufferedReader().forEachLine {
            val obj = NameObject(
                it.split("&")[0],
                JapaneseNameKoreanCandidatesGenerator.makeNameCases(it.split("&")[0]),
                it.split("&")[1]
            )
            nameList.add(obj)
            Log.d(obj.toString())
        }

        inputStream.close()

        return nameList
    }

    fun readText(fn: String): MutableList<String> {
        val inputStream: InputStream = File(fn).inputStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().forEachLine {
            lineList.add(it)
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
                if (item != "") {
                    writer.append("$item\n")
                }
            }

            writer.close()

        } catch (e: Exception) {
            println("파일 ${fn}을(를) 저장하는데 실패했습니다\n$e")
            return
        }

    }

}