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
                inputText = loadFile(fileName)
                fileControl.saveText(fileName, inputText)
            }
            Constants.CODE_CONSOLE_INVALID -> println("유효하지 않은 명령입니다. 종료하려면 999를 입력하십시오")
            Constants.CODE_CONSOLE_END -> taskEnd = true
            else -> {
                println(Constants.TEXT_HORIZONTAL_LINE)
                inputText = loadFile(fileName)
                when(taskCode) {
                    //1 -> inputText = textEditor.removeManualIndents(inputText)
                    //2 -> inputText = textEditor.replaceQuotes(inputText)
                    //3 -> inputText = textEditor.replaceSpecialCharacters(inputText)
                    //4 -> inputText = textEditor.resetSpaces(inputText)
                    5 -> {
                        try {
                            println("파일명을 입력해주세요")
                            val fn = readLine()
                            inputText = NameEditor.fixNameConsistency(loadName(fn), inputText)
                        } catch (e: Exception) {
                            println("자동 처리에 실패했습니다")
                        } finally {
                            println("예상치 못한 문제가 발생했습니다")
                        }
                    }
                    //6 -> inputText = textEditor.interactiveQuotesEditor(inputText)
                    //102 -> inputText = textEditor.refactorQuotes(inputText)
                    //200 -> getNotations(inputText)
                    //201 -> getBrackets(inputText)
                    500 -> {
                        inputText = textEditor.removeManualIndents(inputText)//들여쓰기
                        inputText = textEditor.interactiveQuotesEditor(inputText)
                        inputText = textEditor.replaceSpecialCharacters(inputText)//특수문자
                        inputText = textEditor.resetSpaces(inputText)//띄어쓰기
                        getBrackets(inputText)//괄호점검
                        getNotations(inputText)//각주
                    }
                }
                fileControl.saveText(fileName, inputText)
            }
        }

    } while (!taskEnd)

}

private fun showMainOrder() {
    println(Constants.TEXT_HORIZONTAL_LINE)
    println("0: 파일 불러오기\n" +
            //"1: 수동 들여쓰기 제거\n" +
            //"2: 따옴표 정리\n" +
            //"3: 특수문자 기호 정리\n" +
            //"4: 띄어쓰기 점검\n" +
            "5: 일본어 고유명사 일관성 검사\n" +
            //"6: 대화형 따옴표 수정기\n"+
            //"102: 따옴표 오류 복구\n" +
            //"200: 각주 점검\n" +
            //"201: 괄호 점검\n" +
            "500: 추천 설정으로 자동 정리\n" +
            "999: 종료")
    print("작업을 선택해주세요: ")
}

private fun loadFile(fileName: String?): MutableList<String> {
    val fileControl = FileControl()
    return try {
        println("${fileName}을 불러왔습니다")
        fileName?.let { fileControl.readText(it) }!!
    } catch (e: FileNotFoundException) {
        println("파일이 존재하지 않습니다")
        mutableListOf()
    }
}

private fun loadName(fileName: String?): MutableList<NameObject> {
    val fileControl = FileControl()
    return try {
        println("${fileName}을 불러왔습니다")
        fileName?.let { fileControl.readName(it) }!!
    } catch (e: FileNotFoundException) {
        println("파일이 존재하지 않습니다")
        mutableListOf()
    }
}

private fun getNotations(list: List<String>) {
    println("각주 분석 정보")
    for(i in list.indices) {
        if (list[i].contains("각주|역자|역주|식자|주석|참고".toRegex())
                || list[i].contains("*")
                || list[i].contains("\\[[0-9]+\\]".toRegex())) {
            println("${i+1}: ${list[i].substring(0,Math.min(Constants.TEXT_MAX_CHARACTERS_PER_LINE, list[i].length))}")
        }
    }
}

private fun getBrackets(list: List<String>) {
    println("괄호 분석 정보")
    var numberSmallBrackets = 0 //()
    var numberMiddleBrackets = 0 //{}
    var numberLargeBrackets = 0 //[]
    var numberCurvedBrackets = 0 //〔〕
    var numberRectBrackets = 0 //「 」
    var numberDoubleRectBrackets = 0 //『 』
    var numberArrowBrackets = 0 //〈〉
    var numberDoubleArrowBrackets = 0 //《》
    var numberGLEQBrackets = 0 //<>
    for(item in list) {
        numberSmallBrackets += item.length-item.replace("(","").length+item.length-item.replace(")","").length
        numberMiddleBrackets += item.length-item.replace("{","").length+item.length-item.replace("}","").length
        numberLargeBrackets += item.length-item.replace("[","").length+item.length-item.replace("]","").length
        numberCurvedBrackets += item.length-item.replace("〔","").length+item.length-item.replace("〕","").length
        numberRectBrackets += item.length-item.replace("「","").length+item.length-item.replace("」","").length
        numberDoubleRectBrackets += item.length-item.replace("『","").length+item.length-item.replace("』","").length
        numberArrowBrackets += item.length-item.replace("〈","").length+item.length-item.replace("〉","").length
        numberDoubleArrowBrackets += item.length-item.replace("《","").length+item.length-item.replace("》","").length
        numberGLEQBrackets += item.length-item.replace("<","").length+item.length-item.replace(">","").length
    }

    println("(소괄호) : $numberSmallBrackets\n" +
            "{중괄호} : $numberMiddleBrackets\n" +
            "[대괄호] : $numberLargeBrackets\n" +
            "〔괄호〕: $numberCurvedBrackets\n" +
            "「홑낫표」: $numberRectBrackets\n" +
            "『겹낫표』: $numberDoubleRectBrackets\n" +
            "〈홑화살괄호〉: $numberArrowBrackets\n" +
            "《겹화살괄호》: $numberDoubleArrowBrackets\n" +
            "<부등호>: $numberGLEQBrackets")

    if (numberMiddleBrackets+numberLargeBrackets+numberGLEQBrackets > 0) {
        Log.i("인쇄체에 적합하지 않은 괄호가 ${numberMiddleBrackets+numberLargeBrackets+numberGLEQBrackets}개 발견되었습니다.\n" +
                "적합한 형태로 교체해주세요.")
    }
}