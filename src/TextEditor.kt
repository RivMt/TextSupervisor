class TextEditor {

    fun replaceLargeQuotes(list: List<String>): MutableList<String> {
        println(Constants.TEXT_HORIZONTAL_LINE)
        println("큰따옴표 수정을 개시합니다.")

        //TODO: 문장 중간에 있는 경우 체크할 것

        val strBuilder = StringBuilder()
        strBuilder.append("\n")
        for(item in list) {
            strBuilder.append(item+"\n")
        }
        return strBuilder.toString()
                .replace("\n\"","\n“")
                .replace("\"\n","”\n")
                .split("\n").toMutableList()

    }

    fun removeManualIndents(list: List<String>): MutableList<String> {
        println(Constants.TEXT_HORIZONTAL_LINE)
        println("수동 들여쓰기를 제거합니다")

        val l = mutableListOf<String>()

        for(item in list) {
            l.add(item.replace("^[\\s　]+".toRegex(),""))
        }

        return l
    }

    fun replaceHorizontalLines(list: List<String>): MutableList<String> {
        println(Constants.TEXT_HORIZONTAL_LINE)
        println("하이픈 기호 정리를 시작합니다")

        val l = mutableListOf<String>()

        for(item in list) {
            l.add(item.replace("[-—–‐\\-⁃‑―‒]+".toRegex(),"⸺"))
        }

        return l
    }

}