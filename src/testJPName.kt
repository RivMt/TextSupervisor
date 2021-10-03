fun main(args: Array<String>) {
    val finder = JapaneseNameFinder()
    val candidates = finder.makeNameCases("お=,ま,え")
    var index = 0
    repeat(candidates.size) {
        println(candidates[index])
        index++
    }
    
    val candidates2 = finder.makeNameCases("く,み,こ")
    var index2 = 0
    repeat(candidates2.size) {
        println(candidates[index2])
        index2++
    }
}