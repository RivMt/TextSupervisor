class NameEditor {
    companion object {
        fun fixNameConsistency(list: MutableList<NameObject>, text: MutableList<String>): MutableList<String> {
            val result = mutableListOf<String>()
            
            Log.v("인명 일관성 검사")
            for(find in list) {
                for(item in find.candidates) {
                    if (item != find.change) {
                        var count = 0
                        for(line in text) {
                            if (line.contains(item)) {
                                count++
                            }
                        }
                    
                        Log.d("${find.input} > $item: $count")
                        if (count > 0) {
                            Log.v("${find.input}에 대한 후보 ${item}에 대해 ${count}개가 발견되었습니다.")
                            Log.v("${find.change}로 수정하시겠습니까? (Y/n)")
                            val rl = readLine()
                            if (rl!!.toLowerCase() == "y") {
                                for(line in text) {
                                    result.add(line.replace(item, find.change))
                                    Log.d(line)
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