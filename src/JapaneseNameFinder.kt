class JapaneseNameFinder {

    fun makeList(list: List<String>): MutableList<String> {
        return list.toMutableList()
    }

    private fun makeWord(text: String): MutableList<String> {
        val longSoundTypeDouble = Utility.countChar(text, "=")
        val longSoundTypeDown = Utility.countChar(text, "-")

        val listNumber = 1+longSoundTypeDouble+longSoundTypeDown


        return mutableListOf()
    }

    /*
    private fun findFirstCases(input: String): MutableList<String> {
        val choseong = HangulController().getChoseong(input.toCharArray()[0])
    }
    */


}