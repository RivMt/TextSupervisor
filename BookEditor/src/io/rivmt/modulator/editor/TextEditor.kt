package io.rivmt.modulator.editor

import io.rivmt.utils.utility.Log
import io.rivmt.utils.utility.Constants
import io.rivmt.modulator.editor.QuotesEditor

class TextEditor {

    companion object {

        fun removeManualIndents(list: List<String>): MutableList<String> {
            Log.split()
            Log.m("수동 들여쓰기 제거 중...")
            val l = mutableListOf<String>()
            for(item in list) {
                l.add(item.replace("^[\\s　]+".toRegex(),""))
            }
            Log.m("완료")
            return l
        }

    
        fun replaceSpecialCharacters(list: List<String>): MutableList<String> {
            Log.split()
            Log.m("특수문자 정리 중...")
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
            Log.m("완료")
            return l
        }

    
        fun resetSpaces(list: List<String>): MutableList<String> {
            Log.split()
            Log.m("띄어쓰기 수정 중...")
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
            Log.m("완료")
            return l
        }
    
    
        fun interactiveQuotesEditor(input: List<String>): MutableList<String> {  
            Log.split()
            val lines = mutableListOf<String>()  
            Log.m("대화형 따옴표 개시...")
            for(str in input) {
                //Reset quotes
                var line = str.replace("“", "\"").replace("”", "\"").replace("‘", "'").replace("’", "'")
            
                //Double quotes
                val type = QuotesEditor.judgeDoubleQuotesType(line)
                Log.v(type, "^" + line + "$")
                line = QuotesEditor.editDoubleQuotes(type, line)
            
                //Single quotes
                line = QuotesEditor.editSingleQuotes(line)
            
                lines.add(line)
            }
            Log.m("완료")
            return lines
        }

    }
}