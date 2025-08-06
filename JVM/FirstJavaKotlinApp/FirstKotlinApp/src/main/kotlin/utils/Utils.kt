package utils

import kotlin.time.measureTime

fun String.runFunction(run: Boolean, function: () -> Unit) {
    if (!run) return
    println("---Test of $this---")
    val time = measureTime {
        function()
    }
    println("---Time $time---")
}

fun <T> Array<T>.swap(first: Int, second: Int) {
    val aux = this[first]
    this[first] = this[second]
    this[second] = aux
}