package io.rivmt.editor

import io.rivmt.utility.Log
import io.rivmt.utility.Constants
import io.rivmt.editor.QuotesEditor

class TextEditor {

    companion object {

        fun removeManualIndents(list: List<String>): MutableList<String> {
            Log.v("수동 들여쓰기를 제거합니다")
            val l = mutableListOf<String>()
            for(item in list) {
                l.add(item.replace("^[\\s　]+".toRegex(),""))
            }
            return l
        }

    
        fun replaceSpecialCharacters(list: List<String>): MutableList<String> {
            Log.v("특수문자 기호 정리를 시작합니다")
            val l = mutableListOf<String>()
            var t: String
            for(i in list.indices) {
                t = list[i].replace("[-—–‐\\-⁃‑―‒ーㅡ─━]+".toRegex(),"⸺")//Hyphen
                        .replace("cm","㎝")//Units
                        .replace("mm","㎜")
                        .replace("km","㎞")
                        .replace("mg","㎎")
                        .replace("kg","㎏")
                        .replace("kt","㏏")
                        .replace("...","…")//Dots
                        .replace("···", "…")
                        .replace("…·", "…")
                l.add(t)
            }
            return l
        }

    
        fun resetSpaces(list: List<String>): MutableList<String> {
            Log.v("띄어쓰기 점검을 시작합니다")
            val l = mutableListOf<String>()
            var t: String
            for(i in list.indices) {
                t = list[i]
                        .replace(".",". ")
                        .replace("  "," ")
                        .replace(" . ",". ")
                        .replace(". \"",".\"")
                        .replace(". '",".'")
                        .replace(". ’",".’")
                        .replace(". ”",".”")
                        .replace(",",", ")//Comma
                        .replace("  "," ")
                        .replace(" , ",", ")
                        .replace(", \"",",\"")
                        .replace(", '",",'")
                        .replace(", ’",",’")
                        .replace(", ”",",”")
                l.add(t)
            }
            return l
        }
    
    
        fun interactiveQuotesEditor(input: List<String>): MutableList<String> {  
            val lines = mutableListOf<String>()  
            Log.v("대화형 따옴표 수정기")
            for(str in input) {
                //Reset quotes
                var line = str.replace("“", "\"").replace("”", "\"").replace("‘", "'").replace("’", "'")
            
                //Double quotes
                val type = QuotesEditor.judgeDoubleQuotesType(line)
                Log.d(type, "^" + line + "$")
                line = QuotesEditor.editDoubleQuotes(type, line)
            
                //Single quotes
                line = QuotesEditor.editSingleQuotes(line)
            
                lines.add(line)
            }
            return lines
        }

    }
}