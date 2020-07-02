import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

fun main(args: Array<String>) {
    println("Text Supervisor System")
    println("강제개행된 텍스트에 사용하지 마십시오")

    //Task
    var taskEnd = false
    var taskCode: Int
    val fileControl = FileControl()
    var fileName: String?
    val textEditor = TextEditor()
    var inputText = mutableListOf<String>()
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
                fileName = readLine()
                try {
                    inputText = fileName?.let { fileControl.readText(it) }!!
                    println("${fileName}을 불러왔습니다")
                } catch (e: FileNotFoundException) {
                    println("파일이 존재하지 않습니다")
                }
            }
            2 -> textEditor.replaceLargeQuotes(inputText)
            Constants.CODE_CONSOLE_INVALID -> println("유효하지 않은 명령입니다. 종료하려면 999를 입력하십시오")
            else -> taskEnd = true
        }

    } while (!taskEnd)

}

private fun showMainOrder() {
    println("0: 파일 불러오기\n" +
            "1: 수동 들여쓰기 제거\n" +
            "2: 큰따옴표 정리" +
            "999: 종료")
    println("작업을 선택해주세요: n")
}