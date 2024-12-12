package com.hashem

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