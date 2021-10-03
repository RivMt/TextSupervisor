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
            val chars = text.toCharArray()
            var count = 0
            for(char in chars) {
                if (char == '"') {
                    count++
                }
            }
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
                    text = regDoubleQuotesFirst.replace(text, "“")
                    text = text.replace("\"", "”\n")
                }
                TYPE_DOUBLE_QUOTES_MIDDLE_LAST -> {
                    text = regDoubleQuotesLast.replace(text, "”")
                    text = text.replace("\"", "\n“")
                }
                TYPE_DOUBLE_QUOTES_BOTH_MIDDLE -> {
                    text = text.replace("\"", "\n“")
                    text = text.replace("\"", "”\n")
                }
                TYPE_DOUBLE_QUOTES_FIRST_ONLY -> {
                    Log.v(str)
                    Log.v("1) 맨 앞 큰따옴표 제거")
                    Log.v("2) 맨 뒤에 큰따옴표 삽입")
                    Log.v("3) 무시")
                    var complete = false
                    while(!complete) {
                        print("선택: ")
                        val order = readLine()
                        when(order) {
                            "1" -> {
                                text = text.replace("\"", "")
                                complete = true
                                Log.v("")
                            }
                            "2" -> {
                                text = text.replace("\"", "“")
                                text = text + "”"
                                complete = true
                                Log.v("")
                            }
                            "3" -> {
                                complete = true
                                Log.w("위 문장은 수동 처리가 필요합니다.")
                                Log.v("")
                            }
                        }
                    }
                }
                TYPE_DOUBLE_QUOTES_LAST_ONLY -> {
                    Log.v(str)
                    Log.v("1) 맨 뒤 큰따옴표 제거")
                    Log.v("2) 맨 앞에 큰따옴표 삽입")
                    Log.v("3) 무시")
                    var complete = false
                    while(!complete) {
                        print("선택: ")
                        val order = readLine()
                        when(order) {
                            "1" -> {
                                text = text.replace("\"", "")
                                complete = true
                                Log.v("")
                            }
                            "2" -> {
                                text = text.replace("\"", "”")
                                text = "“"+text
                                complete = true
                                Log.v("")
                            }
                            "3" -> {
                                complete = true
                                Log.w("위 문장은 수동 처리가 필요합니다.")
                                Log.v("")
                            }
                        }
                    }
                }
                TYPE_DOUBLE_QUOTES_MIDDLE_ONLY, TYPE_DOUBLE_QUOTES_MULTIPLE -> {
                    Log.v(str)
                    Log.w("위 문장은 수동 처리가 필요합니다.")
                }
            }        
            return text
        }
        
        fun editSingleQuotes(str: String): String {
            var text = str
            //Count single quotes' number
            val chars = text.toCharArray()
            var count = 0
            for(char in chars) {
                if (char == '\'') {
                    count++
                }
            }
            //
            when(count) {
                1 -> {
                    Log.v(str)
                    Log.v("1) 작은따옴표 제거")
                    Log.v("2) 어깻점으로 변경")
                    Log.v("3) 무시")
                    var complete = false
                    while(!complete) {
                        print("선택: ")
                        val order = readLine()
                        when(order) {
                            "1" -> {
                                text = text.replace("'", "")
                                complete = true
                                Log.v("")
                            }
                            "2" -> {
                                text = text.replace("'", "ʼ")
                                complete = true
                                Log.v("")
                            }
                            "3" -> {
                                complete = true
                                Log.w("위 문장은 수동 처리가 필요합니다.")
                                Log.v("")
                            }
                        }
                    }
                }
                2 -> {
                    text = text.replaceFirst("'", "‘")
                    text = text.replace("'", "’")
                }
                else -> { //more than 3
                    if (count%2 == 1) { //odd
                        Log.w("위 문장은 수동 처리가 필요합니다.")
                        Log.v("")
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