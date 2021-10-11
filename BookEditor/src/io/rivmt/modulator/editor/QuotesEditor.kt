package io.rivmt.modulator.editor

import io.rivmt.utils.utility.Utility
import io.rivmt.utils.utility.Log

class QuotesEditor {
    companion object {
        const val TYPE_DOUBLE_QUOTES_NONE = 0
        const val TYPE_DOUBLE_QUOTES_BOTH_SIDE = 1
        const val TYPE_DOUBLE_QUOTES_FIRST_MIDDLE = 2
        const val TYPE_DOUBLE_QUOTES_MIDDLE_LAST = 3
        const val TYPE_DOUBLE_QUOTES_BOTH_MIDDLE = 4
        const val TYPE_DOUBLE_QUOTES_FIRST_ONLY = 5
        const val TYPE_DOUBLE_QUOTES_LAST_ONLY = 6
        const val TYPE_DOUBLE_QUOTES_MIDDLE_ONLY = 7
        const val TYPE_DOUBLE_QUOTES_MULTIPLE = 8
        
        private val regDoubleQuotesFirst = "^\"".toRegex()
        private val regDoubleQuotesLast = "\"$".toRegex()
        
        fun judgeDoubleQuotesType(text: String): Int {
            //Count double quotes' number
            val count = Utility.countChar(text, '"')
            
            //Judge type
            when(count) {
                0 -> return TYPE_DOUBLE_QUOTES_NONE //Not exist
                1 -> { //1 exists
                    if (regDoubleQuotesFirst.containsMatchIn(text)) {
                        return TYPE_DOUBLE_QUOTES_FIRST_ONLY
                    } else if (regDoubleQuotesLast.containsMatchIn(text)) {
                        return TYPE_DOUBLE_QUOTES_LAST_ONLY
                    } else {
                        return TYPE_DOUBLE_QUOTES_MIDDLE_ONLY
                    }
                }
                2 -> {
                    if (regDoubleQuotesFirst.containsMatchIn(text) && regDoubleQuotesLast.containsMatchIn(text)) {
                        return TYPE_DOUBLE_QUOTES_BOTH_SIDE
                    } else if (regDoubleQuotesFirst.containsMatchIn(text)) {
                        return TYPE_DOUBLE_QUOTES_FIRST_MIDDLE
                    } else if (regDoubleQuotesLast.containsMatchIn(text)) {
                        return TYPE_DOUBLE_QUOTES_MIDDLE_LAST
                    } else {
                        return TYPE_DOUBLE_QUOTES_BOTH_MIDDLE
                    }
                }
                else -> return TYPE_DOUBLE_QUOTES_MULTIPLE
            }
        }
        
        fun editDoubleQuotes(type: Int, str: String): String {
            var text = str
            when(type) {
                TYPE_DOUBLE_QUOTES_BOTH_SIDE -> {
                    text = regDoubleQuotesFirst.replace(text, "“")
                    text = regDoubleQuotesLast.replace(text, "”")
                }
                TYPE_DOUBLE_QUOTES_FIRST_MIDDLE -> {
                    Log.m(str)
                    Log.m("1) 큰따옴표 뒤로 줄바꿈")
                    Log.m("2) 작은따옴표로 변경")
                    Log.m("3) 무시")
                    var complete = false
                    while(!complete) {
                        Log.h("선택: ")
                        val order = readLine()
                        when(order) {
                            "1" -> {
                                text = regDoubleQuotesFirst.replace(text, "“")
                                text = text.replace("\"", "”\n")
                                complete = true
                            }
                            "2" -> {
                                text = text.replaceFirst("\"", "‘")
                                text = text.replace("\"", "’")
                                complete = true
                            }
                            "3" -> {
                                complete = true
                                Log.m("위 문장은 수동 처리가 필요합니다.")
                            }
                        }
                        Log.m("")
                    }
                }
                TYPE_DOUBLE_QUOTES_MIDDLE_LAST -> {
                    text = regDoubleQuotesLast.replace(text, "”")
                    text = text.replace("\"", "\n“")
                }
                TYPE_DOUBLE_QUOTES_BOTH_MIDDLE -> {
                    Log.m(str)
                    Log.m("1) 큰따옴표 앞뒤로 줄바꿈")
                    Log.m("2) 작은따옴표로 변경")
                    Log.m("3) 무시")
                    var complete = false
                    while(!complete) {
                        Log.h("선택: ")
                        val order = readLine()
                        when(order) {
                            "1" -> {
                                text = text.replaceFirst("\"", "\n“")
                                text = text.replace("\"", "”\n")
                                complete = true
                            }
                            "2" -> {
                                text = text.replaceFirst("\"", "‘")
                                text = text.replace("\"", "’")
                                complete = true
                            }
                            "3" -> {
                                complete = true
                                Log.m("위 문장은 수동 처리가 필요합니다.")
                            }
                        }
                        Log.m("")
                    }
                }
                TYPE_DOUBLE_QUOTES_FIRST_ONLY -> {
                    Log.m(str)
                    Log.m("1) 맨 앞 큰따옴표 제거")
                    Log.m("2) 맨 뒤에 큰따옴표 삽입")
                    Log.m("3) 무시")
                    var complete = false
                    while(!complete) {
                        Log.h("선택: ")
                        val order = readLine()
                        when(order) {
                            "1" -> {
                                text = text.replace("\"", "")
                                complete = true
                            }
                            "2" -> {
                                text = text.replace("\"", "“")
                                text = text + "”"
                                complete = true
                            }
                            "3" -> {
                                complete = true
                                Log.m("위 문장은 수동 처리가 필요합니다.")
                            }
                        }
                        Log.m("")
                    }
                }
                TYPE_DOUBLE_QUOTES_LAST_ONLY -> {
                    Log.m(str)
                    Log.m("1) 맨 뒤 큰따옴표 제거")
                    Log.m("2) 맨 앞에 큰따옴표 삽입")
                    Log.m("3) 무시")
                    var complete = false
                    while(!complete) {
                        Log.h("선택: ")
                        val order = readLine()
                        when(order) {
                            "1" -> {
                                text = text.replace("\"", "")
                                complete = true
                            }
                            "2" -> {
                                text = text.replace("\"", "”")
                                text = "“"+text
                                complete = true
                            }
                            "3" -> {
                                complete = true
                                Log.m("위 문장은 수동 처리가 필요합니다.")
                            }
                        }
                        Log.m("")
                    }
                }
                TYPE_DOUBLE_QUOTES_MIDDLE_ONLY, TYPE_DOUBLE_QUOTES_MULTIPLE -> {
                    Log.m(str)
                    Log.m("위 문장은 수동 처리가 필요합니다.")
                }
            }        
            return text
        }
        
        fun editSingleQuotes(str: String): String {
            var text = str
            //Count single quotes' number
            val count = Utility.countChar(text, '\'')
            
            //Edit
            when(count) {
                1 -> {
                    Log.m(str)
                    Log.m("1) 작은따옴표 제거")
                    Log.m("2) 어깻점으로 변경")
                    Log.m("3) 무시")
                    var complete = false
                    while(!complete) {
                        Log.h("선택: ")
                        val order = readLine()
                        when(order) {
                            "1" -> {
                                text = text.replace("'", "")
                                complete = true
                            }
                            "2" -> {
                                text = text.replace("'", "ʼ")
                                complete = true
                            }
                            "3" -> {
                                complete = true
                                Log.w("위 문장은 수동 처리가 필요합니다.")
                            }
                        }
                        Log.m("")
                    }
                }
                2 -> {
                    text = text.replaceFirst("'", "‘")
                    text = text.replace("'", "’")
                }
                else -> { //more than 3
                    if (count%2 == 1) { //odd
                        Log.m("위 문장은 수동 처리가 필요합니다.")
                        Log.m("")
                    } else { //even
                        repeat(count/2) {
                            text = text.replaceFirst("'", "‘")
                            text = text.replaceFirst("'", "’")
                        }
                    }
                }
            }
            
            return text
        }
    }

}