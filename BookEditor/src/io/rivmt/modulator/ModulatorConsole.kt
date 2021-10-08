package io.rivmt.modulator

import io.rivmt.utils.Console
import io.rivmt.utils.utility.Log
import io.rivmt.utils.utility.Constants
import io.rivmt.utils.utility.Utility
import io.rivmt.utils.file.FileControl
import io.rivmt.modulator.editor.TextEditor
import io.rivmt.modulator.editor.NameEditor
import io.rivmt.modulator.language.name.NameObject

class ModulatorConsole: Console() {
    
    var fileName: String? = null //Target text file name
    var inputText: MutableList<String> = mutableListOf()//List target text lines
    var taskEnd = false //If taskEnd become true, quit program
    
    fun main() {
        Log.v("텍본 교열 장치")
        Log.v("텍본의 문장 부호, 문단 서식 등을 수정합니다.\n강제개행된 텍스트에 사용하지 마십시오.")
    
        start(taskEnd)
    }    

    override fun showMainMenu() {
        Log.v(Constants.TEXT_HORIZONTAL_LINE)
        Log.v("0: 파일 불러오기\n" +
                "5: 일본어 고유명사 일관성 검사\n" +
                "500: 추천 설정으로 자동 정리\n" +
                "999: 종료")
        print("작업을 선택해주세요: ")
    }
    
    override fun readFile() {
        Log.v(Constants.TEXT_HORIZONTAL_LINE)
        Log.v("파일명을 입력해주세요")
        fileName = readLine()
        inputText = FileControl.loadTextFile(fileName)
        FileControl.saveText(fileName, inputText)
    }
    
    
    ///TODO: Fix indent
    override fun handleTask(taskCode: Int) {
          //Check Code
            when(taskCode) {
                //Read txt file
                Constants.CODE_CONSOLE_READ_FILE -> readFile()
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
    }
        
}