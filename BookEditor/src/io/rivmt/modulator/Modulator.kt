package io.rivmt.modulator

import io.rivmt.utils.utility.Log
import io.rivmt.utils.utility.Constants
import io.rivmt.utils.utility.Utility
import io.rivmt.utils.file.FileControl
import io.rivmt.modulator.editor.TextEditor
import io.rivmt.modulator.editor.NameEditor
import io.rivmt.modulator.editor.TagEditor
import io.rivmt.modulator.language.name.NameObject

val actionMap = mapOf<Int, String>(
    Constants.CODE_CONSOLE_FILE_READ to "텍스트 파일 불러오기",
    Constants.CODE_MODULATOR_JAPANESE_NAME_CONSISTENCY to "일본어 고유명사 일관성 검사",
    Constants.CODE_MODULATOR_SUGGESTED_OPTION to "자동 교열",
    Constants.CODE_MODULATOR_APPEND_TAG to "태그가 삽입된 사본 생성",
    Constants.CODE_CONSOLE_END to "종료"
)

fun main(args: Array<String>) {
    Log.v("Text Supervisor System")
    Log.v("강제개행된 텍스트에 사용하지 마십시오")

    //Task
    var taskEnd = false //If taskEnd become true, quit program
    var taskCode: Int //Integer inputted by User
    var fileName: String? = null //Target text file name
    var fileNameExt = "" //Additional Extension
    var inputText: MutableList<String> //List target text lines
    
    
    do {
        //Show main menu
        showMainMenu()
        
        //Read user input
        taskCode = try {
            Integer.parseInt(readLine()) //Read user inputted integer
        } catch (e: Exception) {
            Constants.CODE_CONSOLE_INVALID //When input is not integer, return INVALID
        }
        

        //Check Code
        when(taskCode) {
            //Read txt file
            Constants.CODE_CONSOLE_FILE_READ -> {
                Log.v(Constants.TEXT_HORIZONTAL_LINE)
                Log.v("파일명을 입력해주세요")
                fileName = readLine()
                inputText = FileControl.loadTextFile(fileName)
                FileControl.saveText(fileName, inputText)
            }
            //Invalid input
            Constants.CODE_CONSOLE_INVALID -> Log.v("유효하지 않은 명령입니다. 종료하려면 999를 입력하십시오")
            //Quit
            Constants.CODE_CONSOLE_END -> taskEnd = true
            //Otherwise
            else -> {
                Log.v(Constants.TEXT_HORIZONTAL_LINE)
                
                //Refresh text file
                inputText = FileControl.loadTextFile(fileName)
                
                //Reset additional extension
                fileNameExt = ""
                
                //Action
                when(taskCode) {
                    //Japanese name consistency check
                    Constants.CODE_MODULATOR_JAPANESE_NAME_CONSISTENCY -> {
                        Log.v("처리할 인명 목록이 있는 파일명을 입력해주세요")
                        val fn = readLine() //Read name list text file
                        inputText = NameEditor.fixNameConsistency(FileControl.loadNameListFile(fn), inputText)
                    }
                    //Suggestion
                    Constants.CODE_MODULATOR_SUGGESTED_OPTION -> {
                        inputText = TextEditor.removeManualIndents(inputText)//들여쓰기
                        inputText = TextEditor.interactiveQuotesEditor(inputText)
                        inputText = TextEditor.replaceSpecialCharacters(inputText)//특수문자
                        inputText = TextEditor.resetSpaces(inputText)//띄어쓰기
                        Utility.analyzeBrackets(inputText)//괄호점검
                        Utility.analyzeNotations(inputText)//각주
                    }
                    //Append tag
                    Constants.CODE_MODULATOR_APPEND_TAG -> {
                        Log.v("태그 삽입 완료")
                        inputText = TagEditor.appendTag(inputText)
                        fileNameExt = ".tag"
                    }
                }
                
                //Save text file
                FileControl.saveText(fileName + fileNameExt, inputText)
            }
        }

    } while (!taskEnd)

}

private fun showMainMenu() {
    Log.v(Constants.TEXT_HORIZONTAL_LINE)
    var index = 0
    repeat(actionMap.size) {
        var key = actionMap.keys.toList()[index]
        Log.v("$key: ${actionMap[key]}")
        index++
    }
    print("작업을 선택해주세요: ")
}