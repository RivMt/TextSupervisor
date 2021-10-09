package io.rivmt.xcalibre.editor

class TagEditor {
    companion object {
        fun appendTagToText(text: MutableList<String>): MutableList<String> {
            val result = mutableListOf<String>()
            for(item in text) {
                result.add("<p>" + item + "</p>")
            }
        }
    }
}