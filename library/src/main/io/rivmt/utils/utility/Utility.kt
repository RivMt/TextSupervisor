package io.rivmt.utils.utility

import java.lang.StringBuilder

class Utility {
    companion object {
        fun countChar(text: String, find: Char): Int {
            val chars = text.toCharArray()
            var count = 0
            for(char in chars) {
                if (char == find) {
                    count++
                }
            }
            return count
        }
        
        fun analyzeNotations(list: List<String>) {
            Log.v("각주 분석 정보")
            for(i in list.indices) {
                if (list[i].contains("각주|역자|역주|식자|주석|참고".toRegex())
                    || list[i].contains("*")
                    || list[i].contains("\\[[0-9]+\\]".toRegex())) {
                    Log.v("${i+1}: ${list[i].substring(0,Math.min(Constants.TEXT_MAX_CHARACTERS_PER_LINE, list[i].length))}")
                }
            }
        }
        
        fun analyzeBrackets(list: List<String>) {
            Log.v("괄호 분석 정보")
            var numberSmallBrackets = 0 //()
            var numberMiddleBrackets = 0 //{}
            var numberLargeBrackets = 0 //[]
            var numberCurvedBrackets = 0 //〔〕
            var numberRectBrackets = 0 //「 」
            var numberDoubleRectBrackets = 0 //『 』
            var numberArrowBrackets = 0 //〈〉
            var numberDoubleArrowBrackets = 0 //《》
            var numberGLEQBrackets = 0 //<>
            for(item in list) {
                numberSmallBrackets += item.length-item.replace("(","").length+item.length-item.replace(")","").length
                numberMiddleBrackets += item.length-item.replace("{","").length+item.length-item.replace("}","").length
                numberLargeBrackets += item.length-item.replace("[","").length+item.length-item.replace("]","").length
                numberCurvedBrackets += item.length-item.replace("〔","").length+item.length-item.replace("〕","").length
                numberRectBrackets += item.length-item.replace("「","").length+item.length-item.replace("」","").length
                numberDoubleRectBrackets += item.length-item.replace("『","").length+item.length-item.replace("』","").length
                numberArrowBrackets += item.length-item.replace("〈","").length+item.length-item.replace("〉","").length
                numberDoubleArrowBrackets += item.length-item.replace("《","").length+item.length-item.replace("》","").length
                numberGLEQBrackets += item.length-item.replace("<","").length+item.length-item.replace(">","").length
            }

            Log.v("(소괄호) : $numberSmallBrackets\n" +
                    "{중괄호} : $numberMiddleBrackets\n" +
                    "[대괄호] : $numberLargeBrackets\n" +
                    "〔괄호〕: $numberCurvedBrackets\n" +
                    "「홑낫표」: $numberRectBrackets\n" +
                    "『겹낫표』: $numberDoubleRectBrackets\n" +
                    "〈홑화살괄호〉: $numberArrowBrackets\n" +
                    "《겹화살괄호》: $numberDoubleArrowBrackets\n" +
                    "<부등호>: $numberGLEQBrackets")

            if (numberMiddleBrackets+numberLargeBrackets+numberGLEQBrackets > 0) {
                Log.i("인쇄체에 적합하지 않은 괄호가 ${numberMiddleBrackets+numberLargeBrackets+numberGLEQBrackets}개 발견되었습니다.\n" +
                        "적합한 형태로 교체해주세요.")
            }
        }
        
        fun findWordInList(item: String, text: MutableList<String>): Int {
            var count = 0
            for(line in text) {
                if (line.contains(item)) {
                    Log.v(line)
                    count++
                }
            }
            return count
        }
        
        fun getFileNameWithoutExtension(fileName: String): String {
            val list = fileName.split('.')
            
            if (list.size <= 1) {
                return fileName
            } else {
                val builder = StringBuilder()
                var index = 0
                repeat(list.size - 1) {
                    builder.append(list[index])
                    index++
                }
                return builder.toString()
            }
        }
        
        fun getFileExtension(fileName: String): String {
            val list = fileName.split('.')
            
            if (list.size <= 1) {
                return ""
            } else {
                return list[list.size - 1]
            }
        }
    }
}