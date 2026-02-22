package easy

import utils.runFunction
import kotlin.time.measureTime

fun main(args: Array<String>) {
    val time = measureTime {
        println(checkPalindrome("aaabaaaa"))
    }
    println(time)

    "anagram".runFunction(true) {
        fun isAnagram(s: String, t: String): Boolean {
            if (s.length != t.length) {
                return false
            }
            return s.toCharArray().sortedArray().contentEquals(t.toCharArray().sortedArray())
//
//            val countS = mutableMapOf<Char, Int>()
//            val countT = mutableMapOf<Char, Int>()
//
//            for (i in s.indices) {
//                countS[s[i]] = countS.getOrDefault(s[i], 0) + 1
//                countT[t[i]] = countT.getOrDefault(t[i], 0) + 1
//            }
//
//            return countS == countT
        }

        println(isAnagram("anagram", "nagaram"))
        println(isAnagram("rat", "car"))
    }
}

fun checkPalindrome(inputString: String): Boolean {
    if (inputString.length == 1) return true
    for (i in 0 until inputString.length / 2) {
        if (inputString[i] != inputString[inputString.length - 1 - i]) {
            return false
        }
    }
    return true
}