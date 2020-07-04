import java.io.FileNotFoundException

fun main(args: Array<String>) {
    println("Text Supervisor System")
    println("강제개행된 텍스트에 사용하지 마십시오")

    //Task
    var taskEnd = false
    var taskCode: Int
    val fileControl = FileControl()
    var fileName: String? = null
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
                println(Constants.TEXT_HORIZONTAL_LINE)
                println("파일명을 입력해주세요")
                fileName = readLine()
                try {
                    inputText = fileName?.let { fileControl.readText(it) }!!
                    println("${fileName}을 불러왔습니다")
                } catch (e: FileNotFoundException) {
                    println("파일이 존재하지 않습니다")
                }
            }
            1 -> {
                inputText = textEditor.removeManualIndents(inputText)
                fileControl.saveText(fileName, inputText)
            }
            2 -> {
                inputText = textEditor.replaceQuotes(inputText)
                fileControl.saveText(fileName, inputText)
            }
            3 -> {
                inputText = textEditor.replaceSpecialCharacters(inputText)
                fileControl.saveText(fileName, inputText)
            }
            4 -> {
                inputText = textEditor.resetSpaces(inputText)
                fileControl.saveText(fileName, inputText)
            }
            5 -> {
                try {
                    println("지원 예정입니다")
                } catch (e: UnhandledException) {
                    println("자동 처리에 실패했습니다")
                } catch (e: FileNotFoundException) {
                    println("파일을 불러올 수 없습니다")
                } finally {
                    println("예상치 못한 문제가 발생했습니다")
                }
            }
            500 -> {
                inputText = textEditor.removeManualIndents(inputText)//들여쓰기
                inputText = textEditor.resetSpaces(inputText)//띄어쓰기
                inputText = textEditor.replaceQuotes(inputText)//따옴표
                inputText = textEditor.replaceSpecialCharacters(inputText)//특수문자
                fileControl.saveText(fileName, inputText)
            }
            Constants.CODE_CONSOLE_INVALID -> println("유효하지 않은 명령입니다. 종료하려면 999를 입력하십시오")
            else -> taskEnd = true
        }

    } while (!taskEnd)

}

private fun showMainOrder() {
    println(Constants.TEXT_HORIZONTAL_LINE)
    println("0: 파일 불러오기\n" +
            "1: 수동 들여쓰기 제거\n" +
            "2: 따옴표 정리\n" +
            "3: 특수문자 기호 정리\n" +
            "4: 띄어쓰기 점검\n" +
            "5: 일본어 고유명사 일관성 검사\n" +
            "500: 추천 설정으로 자동 정리\n" +
            "999: 종료")
    print("작업을 선택해주세요: ")
}