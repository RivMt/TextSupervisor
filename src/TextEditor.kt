class TextEditor {

    fun replaceLargeQuotes(list: List<String>) {
        println("큰따옴표 수정을 개시합니다.")

        //
        var count = 0;//자동 처리 불가능한 문장 수
        val l = mutableListOf<String>()
        var data: EditResult
        for (i in list.indices) {
            data = replaceLargeQuote(list[i])
            l[i] = data.text
            if (data.handle) {
                println("${i+1}: ${data.text}")
                count++
            }
        }

        //
        if (count > 0) {
            println("▲ 자동 처리가 불가능한 문장 (계: ${count})")
        }

    }

    private fun replaceLargeQuote(text: String): EditResult {
        val t = text.replace("\"\n","”").replace("\n\"","“")
        val b = t.contains("\"")

        return EditResult(t, b)
    }

}