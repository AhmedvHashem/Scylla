package easy

import kotlin.time.measureTime

fun main(args: Array<String>) {
    val time = measureTime {
        println(checkPalindrome("aaabaaaa"))
    }
    println(time)
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