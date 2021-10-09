package io.rivmt.modulator.editor

import io.rivmt.utils.utility.Log
import io.rivmt.utils.utility.Utility
import io.rivmt.utils.language.name.NameObject

class NameEditor {
    companion object {
        fun fixNameConsistency(list: MutableList<NameObject>, text: MutableList<String>): MutableList<String> {
            val result = text
            
            Log.v("인명 일관성 검사")
            for(find in list) {
                for(item in find.candidates) {
                    if (item != find.change) {
                        val count = Utility.findWordInList(item, text)
                        if (count > 0) {
                            Log.v("${find.input}에 대한 후보 ${item}에 대해 ${count}개가 발견되었습니다.")
                            Log.v("${find.change}로 수정하시겠습니까? (Y/n)")
                            val rl = readLine()
                            for((i,line) in text.withIndex()) {
                                if (rl!!.toLowerCase() == "y") {
                                    result[i] = line.replace(item, find.change)
                                }
                            }
                        }
                    }
                }
            }
            Log.v("인명 일관성 검사를 마쳤습니다.")
            
            return result
        }
    }
}