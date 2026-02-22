package old

import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val x = doubleArrayOf(1.0, 2.0, 3.0)
    val n = x.size
    val ave = x.sum() / n
    println("Average: $ave")

    val a = x.sumOf { (it - ave).pow(2) }
    println("Sum: $a")

    val b = a / n
    val sd = sqrt(b)
    println("Standard Deviation: $sd")
}