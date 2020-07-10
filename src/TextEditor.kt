class TextEditor {

    fun replaceQuotes(list: List<String>): MutableList<String> {
        
        println("따옴표 수정을 개시합니다.\n조금 기다려주세요...")

        //Make list to string
        val strBuilder = StringBuilder()
        strBuilder.append("\n")
        var checkSingle = false
        var checkDouble = false
        var changeSingle = ""
        var changeDouble = ""
        var t: String
        var count = 0
        for(i in list.indices) {
            t = list[i]
            do {
                if (t.contains("'")) {
                    changeSingle = if (!checkSingle) {
                        "‘"//Start
                    } else {
                        "’"
                    }

                    t = t.replaceFirst("'", changeSingle)
                    checkSingle = !checkSingle
                }

                if (t.contains("\"")) {
                    changeDouble = if (!checkDouble) {
                        "“"//Start
                    } else {
                        "”"
                    }

                    t = t.replaceFirst("\"", changeDouble)
                    checkDouble = !checkDouble
                }
            } while(t.contains("'") || t.contains("\""))

            //Check Doubted Sentence
            if ( (t.contains("^”".toRegex()) || t.contains("“$".toRegex())
                    || t.contains("^’".toRegex()) || t.contains("‘$".toRegex())
                            || t.contains("\\s’".toRegex()) || t.contains("‘\\s".toRegex())) && count < 10) {
                Log.w(i+1, t.substring(0,Math.min(Constants.TEXT_MAX_CHARACTERS_PER_LINE, t.length)))
                count++
            }

            strBuilder.append(t+"\n")
        }

        //Check Error
        if (checkDouble || checkSingle || count > 0) {
            println("닫히지 않은 따옴표가 있는 것 같습니다.\n수동으로 수정 후 복구를 실행해주세요")
        }

        return strBuilder.toString()
                .split("\n").toMutableList()
    }

    fun refactorQuotes(list: List<String>): MutableList<String> {
        
        println("따옴표 오류 복구를 개시합니다.\n조금 기다려주세요...")

        val l = mutableListOf<String>()
        var t: String
        for(item in list) {
            t = item.replace("[‘’]".toRegex(),"'").replace("[“”]".toRegex(),"\"")
            l.add(t)
        }

        return replaceQuotes(l)
    }

    fun removeManualIndents(list: List<String>): MutableList<String> {
        
        println("수동 들여쓰기를 제거합니다")

        val l = mutableListOf<String>()

        for(item in list) {
            l.add(item.replace("^[\\s　]+".toRegex(),""))
        }

        return l
    }

    fun replaceSpecialCharacters(list: List<String>): MutableList<String> {
        
        println("특수문자 기호 정리를 시작합니다")

        val l = mutableListOf<String>()
        var count = 0

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

            l.add(t)
        }

        //Show Error
        if (count > 0) {
            println(Constants.MSG_UNHANDLED_TEXTS+"(계: $count)")
        }

        return l
    }

    fun resetSpaces(list: List<String>): MutableList<String> {
        
        println("띄어쓰기 점검을 시작합니다")

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

    fun checkJapaneseNameConsistency(list: List<String>): MutableList<String> {
        
        println("일관성 검사를 시작합니다")

        val l = mutableListOf<String>()
        val fileControl = FileControl()

        //Receive list
        println("검사할 목록의 파일 이름을 입력해주세요")
        val fileName = readLine()
        var inputList = fileName?.let { fileControl.readText(it) }!!
        println("${fileName}을 불러왔습니다")

        return l
    }

}