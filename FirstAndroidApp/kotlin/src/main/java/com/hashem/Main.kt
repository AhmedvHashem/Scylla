package com.hashem

fun main(args: Array<String>) {

//    val result = computeGameState("Bob", "Anna", listOf("Bob", "Anna", "Bob"))
//    println(result)

    val points = listOf(listOf(1, 2), listOf(3, 4), listOf(0, 0), listOf(5, 5))
    val result = boundingRectangle(points)
    println(result)  // Output: [0, 0, 5, 5]
}
