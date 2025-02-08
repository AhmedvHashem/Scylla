package utils

infix fun String.test(function: () -> Unit) {
    println("---Test of $this---")
    function()
    println()
}

fun <T> MutableList<T>.swap(first: Int, second: Int) {
    val aux = this[first]
    this[first] = this[second]
    this[second] = aux
}