package io.rivmt.utility

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
    }
}