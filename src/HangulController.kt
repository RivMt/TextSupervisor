class HangulController {

    fun getChoseong(text: Char): Int {
        return (text.toInt() - 44032)/(21*28)
    }

    fun getJungseong(text: Char): Int {
        return ((text.toInt() - 44032)%(21*28))/28
    }

    fun getJongseong(text: Char): Int {
        return ((text.toInt() - 44032)%(21*28))%28
    }

    fun getHangul(first: Int, middle: Int, last: Int): Char {
        return (44032+first*588+middle*28+last).toChar()
    }

    fun findChoseongCases(input: Int): MutableList<Int> {
        val l = mutableListOf<Int>()
        when(input) {
            Constants.HANGUL_CHOSEONG_KIYEOK -> {
                l.add(Constants.HANGUL_CHOSEONG_GIYEOK)
                l.add(Constants.HANGUL_CHOSEONG_KIYEOK)
                l.add(Constants.HANGUL_CHOSEONG_GIYEOK_DOUBLE)
            }
            Constants.HANGUL_CHOSEONG_PIEUB -> {

            }

        }

        return l
    }

}