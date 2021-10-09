package io.rivmt.modulator.language

import io.rivmt.utils.utility.Log
import io.rivmt.modulator.language.HangulHandler

class Japanese2Korean {
           
    companion object {
           
           val kanaLongSoundDoubleMap = mapOf(
               "あ" to "아",
               "え" to "에",
               "い" to "이",
               "お" to "오",
               "う" to "우",
               "か" to "아",
               "き" to "이",
               "く" to "우",
               "け" to "에",
               "こ" to "오",
               "さ" to "아",
               "し" to "이",
               "す" to "우",
               "せ" to "에",
               "そ" to "오",
               "た" to "아",
               "ち" to "이",
               "つ" to "우",
               "て" to "에",
               "と" to "오",
               "な" to "아",
               "に" to "이",
               "ぬ" to "우",
               "ね" to "에",
               "の" to "오",
               "は" to "아",
               "ひ" to "이",
               "ふ" to "우",
               "へ" to "에",
               "ほ" to "오",
               "ま" to "아",
               "み" to "이",
               "む" to "우",
               "め" to "에",
               "も" to "오",
               "や" to "아",
               "ゆ" to "우",
               "よ" to "오",
               "ら" to "아",
               "り" to "이",
               "る" to "우",
               "れ" to "에",
               "ろ" to "오",
               "わ" to "아",
               "を" to "오",
               "が" to "아",
               "ぎ" to "이",
               "ぐ" to "우",
               "げ" to "에",
               "ご" to "오",
               "ざ" to "아",
               "じ" to "이",
               "ず" to "우",
               "ぜ" to "에",
               "ぞ" to "오",
               "だ" to "아",
               "ぢ" to "이",
               "づ" to "우",
               "で" to "에",
               "ど" to "오",
               "ば" to "아",
               "び" to "이",
               "ぶ" to "우",
               "べ" to "에",
               "ぼ" to "오",
               "ぱ" to "아",
               "ぴ" to "이",
               "ぷ" to "우",
               "ぺ" to "에",
               "ぽ" to "오"
           )
           
           val kanaKoreanMap: Map<String, List<String>?> = mapOf(
               "あ" to listOf("아"),
               "え" to listOf("에"),
               "い" to listOf("이"),
               "お" to listOf("오"),
               "う" to listOf("우"),
               "か" to listOf("카", "가"),
               "き" to listOf("키", "기"),
               "く" to listOf("쿠", "구"),
               "け" to listOf("케", "게"),
               "こ" to listOf("코", "고"),
               "さ" to listOf("사"),
               "し" to listOf("시"),
               "す" to listOf("스"),
               "せ" to listOf("세"),
               "そ" to listOf("소"),
               "た" to listOf("타", "다"),
               "ち" to listOf("치", "지", "찌"),
               "つ" to listOf("츠", "쓰", "쯔"),
               "て" to listOf("테", "데"),
               "と" to listOf("토", "도"),
               "な" to listOf("나"),
               "に" to listOf("니"),
               "ぬ" to listOf("누"),
               "ね" to listOf("네"),
               "の" to listOf("노"),
               "は" to listOf("하"),
               "ひ" to listOf("히"),
               "ふ" to listOf("후", "푸"),
               "へ" to listOf("헤"),
               "ほ" to listOf("호"),
               "ま" to listOf("마"),
               "み" to listOf("미"),
               "む" to listOf("무"),
               "め" to listOf("메"),
               "も" to listOf("모"),
               "や" to listOf("야"),
               "ゆ" to listOf("유"),
               "よ" to listOf("요"),
               "ら" to listOf("라"),
               "り" to listOf("리"),
               "る" to listOf("루"),
               "れ" to listOf("레"),
               "ろ" to listOf("로"),
               "わ" to listOf("와"),
               "を" to listOf("워"),
               "が" to listOf("가"),
               "ぎ" to listOf("기"),
               "ぐ" to listOf("구"),
               "げ" to listOf("게"),
               "ご" to listOf("고"),
               "ざ" to listOf("자"),
               "じ" to listOf("지"),
               "ず" to listOf("즈"),
               "ぜ" to listOf("제"),
               "ぞ" to listOf("조"),
               "だ" to listOf("다"),
               "ぢ" to listOf("지"),
               "づ" to listOf("즈"),
               "で" to listOf("데"),
               "ど" to listOf("도"),
               "ば" to listOf("바"),
               "び" to listOf("비"),
               "ぶ" to listOf("부"),
               "べ" to listOf("베"),
               "ぼ" to listOf("보"),
               "ぱ" to listOf("파"),
               "ぴ" to listOf("피"),
               "ぷ" to listOf("푸"),
               "ぺ" to listOf("페"),
               "ぽ" to listOf("포"),
               "きゃ" to listOf("캬", "갸"),
               "きゅ" to listOf("큐", "규"),
               "きょ" to listOf("쿄", "교"),
               "ぎゃ" to listOf("갸"),
               "ぎゅ" to listOf("규"),
               "ぎょ" to listOf("교"),
               "しゃ" to listOf("샤"),
               "しゅ" to listOf("슈"),
               "しょ" to listOf("쇼"),
               "じゃ" to listOf("자", "쟈"),
               "じゅ" to listOf("주", "쥬"),
               "じょ" to listOf("조", "죠"),
               "ちゃ" to listOf("차", "챠", "자"),
               "ちゅ" to listOf("추", "츄", "주"),
               "ちょ" to listOf("초", "쵸", "조"),
               "ぢゃ" to listOf("자", "쟈"),
               "ぢゅ" to listOf("주", "쥬"),
               "ぢょ" to listOf("조", "죠"),
               "にゃ" to listOf("냐"),
               "にゅ" to listOf("뉴"),
               "にょ" to listOf("뇨"),
               "ひゃ" to listOf("햐"),
               "ひゅ" to listOf("휴"),
               "ひょ" to listOf("효"),
               "びゃ" to listOf("뱌"),
               "びゅ" to listOf("뷰"),
               "びょ" to listOf("뵤"),
               "ぴゃ" to listOf("퍄"),
               "ぴゅ" to listOf("퓨"),
               "ぴょ" to listOf("표"),
               "みゃ" to listOf("먀"),
               "みゅ" to listOf("뮤"),
               "みょ" to listOf("묘"),
               "りゃ" to listOf("랴"),
               "りゅ" to listOf("류"),
               "りょ" to listOf("료")
           )
        
           fun makeNameCases(input: String): MutableList<String> {
               val result = mutableListOf<String>()
               
               val candidateChars = mutableListOf<MutableList<String>>()
               var candidateSize = 1
               
               var nameChars: List<String>
               if (input.contains(",")) {
                   nameChars = input.split(",")
               } else {
                   nameChars = listOf(input)
               }
               
               //Select candidates characters
               for((i, char) in nameChars.withIndex()) {
                   candidateChars.add(mutableListOf())
                   //Long sound (=: Double, -: Low)
                   val longDoulbe = "=$".toRegex()
                   val longLow = "-$".toRegex()
                   
                   //연속형 장음
                   if (longDoulbe.containsMatchIn(char)) {
                       candidateChars[i] = kanaKoreanMap[char.replace("=", "")]!!.toMutableList()
                       var index = 0
                       repeat(candidateChars[i].size) {
                           val ch = candidateChars[i][index]
                           candidateChars[i].add(ch + kanaLongSoundDoubleMap[char.replace("=", "").takeLast(1)])
                           index++
                       }
                   } else if (longLow.containsMatchIn(char)) {
                       candidateChars[i] = kanaKoreanMap[char.replace("-", "")]!!.toMutableList()
                       var index = 0
                       repeat(candidateChars[i].size) {
                           val ch = candidateChars[i][index]
                           candidateChars[i].add(ch + "우")
                           index++
                       }
                   } else {
                       //받침
                       val finalConsonantTsu = "っ$".toRegex()
                       val finalConsonantN = "ん$".toRegex()
                       var hasFinalConsonant = false
                       var last = '아'
                       if (finalConsonantTsu.containsMatchIn(char)) {
                           hasFinalConsonant = true
                           last = '앗'
                       } else if (finalConsonantN.containsMatchIn(char)) {
                           hasFinalConsonant = true
                           last = '안'
                       }
                       
                       if (hasFinalConsonant) {
                           candidateChars[i] = kanaKoreanMap[char.replace("ん", "").replace("っ", "")]!!.toMutableList()
                           for((j,item) in candidateChars[i].withIndex()) {
                               candidateChars[i][j] = HangulHandler.combinateHangul(
                                   HangulHandler.getChoseong(item.single()),
                                   HangulHandler.getJungseong(item.single()),
                                   HangulHandler.getJongseong(last)
                               ).toString()
                           }
                       } else {
                           candidateChars[i] = kanaKoreanMap[char]!!.toMutableList()
                       }
                   }
                   Log.d("$char: " + candidateChars[i].toString())
                   candidateSize *= candidateChars[i].size
               }
               
               //Candidates size
               repeat(candidateSize) {
                   result.add("")
               }
               
               //Make candidates
               var period = result.size
               for(list in candidateChars) {
                   period /= list.size
                   var resultIndex = 0
                   var periodIndex = 0
                   repeat(result.size) {
                       result[resultIndex] = result[resultIndex] + list[periodIndex]
                       resultIndex++
                       if (resultIndex%period == 0 && resultIndex != 0) {
                           periodIndex++
                           if (periodIndex == list.size) {
                               periodIndex = 0
                           }
                       }
                   }
               }
               
               Log.d("Result: " + result.toString())
               return result
          }
    }
}