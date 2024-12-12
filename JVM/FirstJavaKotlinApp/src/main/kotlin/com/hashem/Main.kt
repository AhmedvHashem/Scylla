package com.hashem

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main(args: Array<String>) {
    println("Hello, World! Kotlin")

    //    val time = measureTime {
    //        // println(checkPalindrome("aaabaaaa"))
    //        // println(adjacentElementsLargestProduct(mutableListOf(-23, 4, -3, 8, -12)))
    //        // println(firstDuplicate2(mutableListOf(2, 1, 3, 5, 3, 2)))
    //        // println(firstDuplicate2(mutableListOf(2, 4, 3, 5, 1)))
    //        // println(firstDuplicate2(mutableListOf(1)))
    //        // println(firstDuplicate2(mutableListOf(1, 1, 2, 2, 1)))
    //        // println(firstDuplicate2(mutableListOf(1, 1, 2, 2, 1)))
    //        // println(firstNotRepeatingCharacter("abacabad"))
    //        // println(firstNotRepeatingCharacter("abacabaabacaba"))
    //        // println(firstNotRepeatingCharacter("bcccccccb"))
    //        // println(
    //        //         rotateImageMatrix(
    //        //                 mutableListOf(
    //        //                         mutableListOf(1, 2, 3),
    //        //                         mutableListOf(4, 5, 6),
    //        //                         mutableListOf(7, 8, 9)
    //        //                 )
    //        //         )
    //        // )
    //        // println(removeKFromList())
    //        // println(shapeArea(4))
    //    }
    //    println(time)
}

fun centuryOfYear(year: Int): Int {
    // 1 to 100 => 1
    // 101 to 200 => 2
    // 201 to 300 => 3
    // .....
    // 1001 to 1100 => 11
    // 1101 to 1200 => 12
    // 1201 to 1300 => 13
    // 1301 to 1400 => 14
    // 1401 to 1500 => 15
    // 1501 to 1600 => 16
    // 1601 to 1700 => 17
    // 1701 to 1800 => 18
    // 1801 to 1900 => 19
    // 1901 to 2000 => 20
    // 2001 to 2100 => 21
    var century = 0
    century =
            if (year % 100 == 0) {
                year / 100
            } else {
                (year / 100) + 1
            }

    return century
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

fun adjacentElementsLargestProduct(inputArray: MutableList<Int>): Int {
    var max = Int.MIN_VALUE
    for (i in 0 until inputArray.size - 1) {
        val nu = inputArray[i] * inputArray[i + 1]
        if (max < nu) max = nu
    }
    return max
}

fun firstDuplicate(a: MutableList<Int>): Int {
    var smallestIndex = -1
    for (i in 0 until a.size) {
        for (j in i + 1 until a.size) {
            if (a[i] == a[j] && (smallestIndex == -1 || j < smallestIndex)) smallestIndex = j
        }
    }
    return if (smallestIndex == -1) return -1 else a[smallestIndex]
}

fun firstDuplicate2(a: MutableList<Int>): Int {
    val set = mutableSetOf<Int>()
    for (i in 0 until a.size) {
        if (set.contains(a[i])) {
            return a[i]
        } else {
            set.add(a[i])
        }
    }
    return -1
}

fun firstNotRepeatingCharacter(s: String): Char {
    val a = s.toCharArray()
    val dic = mutableMapOf<Char, Int>()
    for (i in a) {
        dic[i] = dic[i]?.plus(1) ?: 0
    }
    return dic.entries.firstOrNull { it.value == 0 }?.key ?: '_'
}

/*
a = [[1, 2, 3],
     [4, 5, 6],
     [7, 8, 9]]

 solution(a) =
    [[7, 4, 1],
     [8, 5, 2],
     [9, 6, 3]]
 */
fun rotateImageMatrix(a: MutableList<MutableList<Int>>): List<List<Int>> {
    return List(a.size) { rowIndex ->
        val row = (a.size - 1 downTo 0).map { a[it][rowIndex] }
        row
    }
}

data class ListNode<T>(var value: T) {
    var next: ListNode<T>? = null
}

fun removeKFromList(node: ListNode<Int>?, k: Int): ListNode<Int>? {
    var head = node
    while (head != null && head.value == k) {
        head = head.next
    }
    var temp = head
    var prev: ListNode<Int>? = null
    while (temp != null) {
        while (temp != null && temp.value != k) {
            prev = temp
            temp = temp.next
        }
        if (temp == null) return head
        prev!!.next = temp.next
        temp = prev.next
    }
    return head
}

fun asd() {
    val ma: Array<Array<Array<Array<Int>>>> =
            arrayOf(
                    arrayOf(
                            arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)),
                            arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)),
                            arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
                    ),
                    arrayOf(
                            arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)),
                            arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)),
                            arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
                    ),
                    arrayOf(
                            arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)),
                            arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)),
                            arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
                    )
            )
}

fun shapeArea(n: Int): Int {
    var cubes = 1
    for (i in 1..n) {
        if (i == 1) cubes = 1
        else {
            cubes += n * 2
        }
    }

    return cubes
}
