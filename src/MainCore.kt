import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

fun main(args: Array<String>) {
    println("Text Supervisor System")
    println("강제개행된 텍스트에 사용하지 마십시오")

    //Task
    var taskEnd = false
    var taskCode = Constants.CODE_CONSOLE_END
    var inputText: List<String>
    do {
        //Main
        showMainOrder()
        taskCode = try {
            Integer.parseInt(readLine())
        } catch (e: Exception) {
            Constants.CODE_CONSOLE_INVALID
        }

        //Check Code
        when(taskCode) {
            0 -> {
                println("파일명을 입력해주세요")
                val fn = readLine()
                try {
                    inputText = fn?.let { readText(it) }!!
                    println("${fn}을 불러왔습니다")
                } catch (e: FileNotFoundException) {
                    println("파일이 존재하지 않습니다")
                }
            }
            Constants.CODE_CONSOLE_INVALID -> println("유효하지 않은 명령입니다. 종료하려면 999를 입력하십시오")
            else -> taskEnd = true
        }

    } while (!taskEnd)

}

private fun readText(fn: String): List<String> {
    val inputStream: InputStream = File(fn).inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    return lineList
}

private fun showMainOrder() {
    println("0: 파일 불러오기\n1: 수동 들여쓰기 제거\n999: 종료")
    println("작업을 선택해주세요: n")
}