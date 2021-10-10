package io.rivmt.modulator.editor

class TagEditor {
    companion object {
        fun appendTag(text: MutableList<String>): MutableList<String> {
            val result = mutableListOf<String>()
            for (item in text) {
                result.add("<p>" + item + "</p>")
            }
            return result
        } 
    }
}