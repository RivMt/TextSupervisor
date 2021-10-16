package io.rivmt.modulator.editor

import io.rivmt.utils.utility.Log

class TagEditor {
    companion object {
        fun appendTag(text: MutableList<String>): MutableList<String> {
            val result = mutableListOf<String>()
            for (item in text) {
                //Get tag name
                val tag = extractTagName(item)
                
                //
                if (tag == "") { //No tag
                    if (item == "*") {
                        result.add("<cd>*</cd>")
                    } else { //Append default tag
                        result.add("<p>" + item + "</p>")
                    }
                } else { //Close tag
                    result.add(item + "</$tag>")
                }
            }
            return result
        }
        
        fun extractTagName(line: String): String {
            val find = "<[a-z][a-z0-9]+>".toRegex()
            var tag = ""
            if (find.containsMatchIn(line)) {
                val tail = ">.+".toRegex()
                tag = tail.replace(line, "").replace("<", "")
                Log.d("Tag found: $tag")
            }
            return tag
        }
    }
}