package io.rivmt.language.name

data class NameObject(
    val input: String,
    val candidates: MutableList<String>,
    val change: String
) {
    override fun toString(): String {
        return "($input): $candidates > $change"
    }
}