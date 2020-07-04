class JapaneseNameFinder {

    fun makeList(list: List<String>): MutableList<String> {
        return list.toMutableList()
    }

    private fun makeWord(text: String): MutableList<String> {
        val longSoundTypeDouble = Utility.countChar(text, "=")
        val longSoundTypeDown = Utility.countChar(text, "-")
        return mutableListOf()
    }



}