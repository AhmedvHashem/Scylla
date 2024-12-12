package com.hashem

fun adjacentElementsLargestProduct(inputArray: MutableList<Int>): Int {
    var max = Int.MIN_VALUE
    for (i in 0 until inputArray.size - 1) {
        val nu = inputArray[i] * inputArray[i + 1]
        if (max < nu) max = nu
    }
    return max
}