package io.rivmt.modulator.language.name

data class NameObject(
    val input: String, //Primitive equation of name
    val candidates: MutableList<String>, //Converting candidates which created by input
    val change: String
) {
    override fun toString(): String {
        return "($input): $candidates > $change"
    }
}