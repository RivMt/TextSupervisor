package io.rivmt.cli

import io.rivmt.utility.Log
import io.rivmt.utility.Constants
import io.rivmt.utility.Utility
import io.rivmt.file.FileControl
import io.rivmt.editor.TextEditor
import io.rivmt.editor.NameEditor
import io.rivmt.language.name.NameObject

fun main(args: Array<String>) {
    Log.v("Text Supervisor System")
    Log.v("강제개행된 텍스트에 사용하지 마십시오")

    //Task
    var taskEnd = false //If taskEnd become true, quit program
    var taskCode: Int //Integer inputted by User
    var fileName: String? = null //Target text file name
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
            0 -> {
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
                
                //Action
                when(taskCode) {
                    //Japanese name consistency check
                    5 -> {
                        Log.v("처리할 인명 목록이 있는 파일명을 입력해주세요")
                        val fn = readLine() //Read name list text file
                        inputText = NameEditor.fixNameConsistency(FileControl.loadNameListFile(fn), inputText)
                    }
                    //Suggestion
                    500 -> {
                        inputText = TextEditor.removeManualIndents(inputText)//들여쓰기
                        inputText = TextEditor.interactiveQuotesEditor(inputText)
                        inputText = TextEditor.replaceSpecialCharacters(inputText)//특수문자
                        inputText = TextEditor.resetSpaces(inputText)//띄어쓰기
                        Utility.analyzeBrackets(inputText)//괄호점검
                        Utility.analyzeNotations(inputText)//각주
                    }
                }
                
                //Save text file
                FileControl.saveText(fileName, inputText)
            }
        }

    } while (!taskEnd)

}

private fun showMainMenu() {
    Log.v(Constants.TEXT_HORIZONTAL_LINE)
    Log.v("0: 파일 불러오기\n" +
            "5: 일본어 고유명사 일관성 검사\n" +
            "500: 추천 설정으로 자동 정리\n" +
            "999: 종료")
    print("작업을 선택해주세요: ")
}