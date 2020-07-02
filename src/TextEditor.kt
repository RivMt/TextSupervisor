class TextEditor {

    fun replaceLargeQuotes(list: List<String>): MutableList<String> {
        println(Constants.TEXT_HORIZONTAL_LINE)
        println("큰따옴표 수정을 개시합니다.")

        //
        var count = 0;//자동 처리 불가능한 문장 수
        val l = mutableListOf<String>()
        var data: EditResult
        for (i in list.indices) {
            data = replaceLargeQuote(list[i])
            l.add(data.text)
            if (data.handle) {
                println("${i+1}: ${data.text}")
                count++
            }
        }

        //
        if (count > 0) {
            println("▲ 자동 처리가 불가능한 문장 (계: ${count})")
        }

        return l

    }

    private fun replaceLargeQuote(text: String): EditResult {
        text.replaceFirst(""""""","“")
        text.replace("""".?$""".toRegex(),"”")

        val b = text.contains("\"")

        //TODO: 오류 존재

        return EditResult(text, b)
    }

    fun removeManualIndents(list: List<String>): MutableList<String> {
        println(Constants.TEXT_HORIZONTAL_LINE)
        println("수동 들여쓰기를 제거합니다")

        val l = mutableListOf<String>()

        for(item in list) {
            val t = removeManualIndent(item)
            l.add(t)
            println("$item to $t")
        }

        return l
    }

    private fun removeManualIndent(text: String): String {
        return text.replace("^[\\s　]+".toRegex(),"")
    }

}