package com.hashem


// Caching behavior
val i1: Int? = 127
val i2: Int? = 127

val i3: Int? = 128
val i4: Int? = 128

fun main(args: Array<String>) {
    println("Hello, World!")

    println(i1 === i2) // true, due to caching
    println(i3 === i4) // false, outside cache range

}
