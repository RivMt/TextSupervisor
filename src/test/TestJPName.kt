fun main(args: Array<String>) {
    val finder = JapaneseNameFinder()
    
    val list = listOf(
        "お=,ま,え",
        "く,み,こ",
        "た,ち,か,わ"
    )
    
    for(item in list) {
        val candidates = finder.makeNameCases(item)
        var index = 0
        repeat(candidates.size) {
            Log.d("Result: " + candidates[index])
            index++
        }
    }
}