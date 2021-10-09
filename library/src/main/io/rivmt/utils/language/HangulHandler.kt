package io.rivmt.modulator.language

class HangulHandler {

    companion object {
        fun getChoseong(text: Char): Int {
            return (text.toInt() - 44032)/(21*28)
        }

        fun getJungseong(text: Char): Int {
            return ((text.toInt() - 44032)%(21*28))/28
        }

        fun getJongseong(text: Char): Int {
            return ((text.toInt() - 44032)%(21*28))%28
        }

        fun combinateHangul(first: Int, middle: Int, last: Int): Char {
            return (44032+first*588+middle*28+last).toChar()
        }
    }

}