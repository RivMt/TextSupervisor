import java.io.FileNotFoundException

class TextEditor {

    fun replaceLargeQuotes(list: List<String>): MutableList<String> {
        println(Constants.TEXT_HORIZONTAL_LINE)
        println("큰따옴표 수정을 개시합니다.")

        //Make list to string
        val strBuilder = StringBuilder()
        var count = 0
        strBuilder.append("\n")
        for(i in list.indices) {
            if (list[i].contains(""".".""".toRegex())) {
                println("${i+1}: ${list[i]}")
                count++
            } else {
                strBuilder.append(list[i] + "\n")
            }
        }

        //Check Unhandled Text
        if (count > 0) {
            println(Constants.MSG_UNHANDLED_TEXTS+"(계: $count)")
            throw UnhandledException()
        }

        //No Error
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
        println("특수문자 기호 정리를 시작합니다")

        val l = mutableListOf<String>()
        var count = 0

        var t: String
        for(i in list.indices) {
            t = list[i].replace("[-—–‐\\-⁃‑―‒ーㅡ─━]+".toRegex(),"⸺")//Hyphen
                    .replace("cm","㎝")
                    .replace("mm","㎜")
                    .replace("km","㎞")
                    .replace("mg","㎎")
                    .replace("kg","㎏")
                    .replace("kt","㏏")

            l.add(t)
        }

        //Show Error
        if (count > 0) {
            println(Constants.MSG_UNHANDLED_TEXTS+"(계: $count)")
        }

        return l
    }

    fun resetSpaces(list: List<String>): MutableList<String> {
        println(Constants.TEXT_HORIZONTAL_LINE)
        println("띄어쓰기 점검을 시작합니다")

        val l = mutableListOf<String>()

        var t: String
        for(i in list.indices) {
            t = list[i]
                    .replace(".",". ")
                    .replace("  "," ")
                    .replace(" . ",". ")

            l.add(t)
        }

        return l
    }

    fun checkJapaneseNameConsistency(list: List<String>): MutableList<String> {
        println(Constants.TEXT_HORIZONTAL_LINE)
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