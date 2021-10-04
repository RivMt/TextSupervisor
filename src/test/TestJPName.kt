fun main(args: Array<String>) {
    
    val list = listOf(
        "お=,ま,え",
        "く,み,こ",
        "た,ち,か,わ",
        "か,のん",
        "いっ,し,き"
    )
    
    for(item in list) {
        JapaneseNameKoreanCandidatesGenerator.makeNameCases(item)
    }
}