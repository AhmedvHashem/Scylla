package com.hashem

fun main(args: Array<String>) {

//    val result = computeGameState("Bob", "Anna", listOf("Bob", "Anna", "Bob"))
//    println(result)


    data class PersonV2(
        val name: String,
        val data: CharArray,
    ){
        fun asd() {
            data.asSequence()
        }

    }

    val p1 = PersonV2("Amanda", charArrayOf('A', 'm', 'a', 'n', 'd', 'a'))
    val p2 = PersonV2("Amanda", charArrayOf('A', 'm', 'a', 'n', 'd', 'a'))
    println(p1.equals(p2))

}

fun reverse(x: Int): Int {
    var x = x
    var reversed = 0
    while (x != 0) {
        val lastDigit = x % 10
        reversed = reversed * 10 + lastDigit
        x /= 10
    }
    return reversed
}


private const val UNKNOWN_ERROR = 0

class Test {
    companion object {
        private const val UNKNOWN_ERROR = 0
    }
}