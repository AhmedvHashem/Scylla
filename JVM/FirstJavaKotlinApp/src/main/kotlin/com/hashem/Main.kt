package com.hashem

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.PriorityQueue
import java.util.Stack


fun main(args: Array<String>) {
    println("Hello, World! Kotlin")
}

// Caching behavior
val i1: Int? = 127
val i2: Int? = 127

val i3: Int? = 128
val i4: Int? = 128

fun main3() {
//    println(i1 === i2) // true, due to caching
//    println(i3 === i4) // false, outside cache range
//    println(i1 == i2) // true, due to caching
//    println(i3 == i4) // false, outside cache range
//    println(i1?.equals(i2) ?: (i2 === null)) // true, due to caching
//    println(i3?.equals(i4) ?: (i4 === null)) // false, outside cache range
//

    val mmap = mapOf("a" to 1, "b" to 2)
    mmap.forEach { (key, value) ->
        println("$key -> $value")
    }

    hashMapOf("a" to 1, "b" to 2).forEach { (key, value) ->
        println("$key -> $value")
    }

    linkedMapOf("a" to 1, "b" to 2).forEach { (key, value) ->
        println("$key -> $value")
    }

    arrayOf(1, 2, 3).forEach {
        println(it)
    }

    Stack<Int>().apply {
        push(1)
        push(2)
        push(3)
    }.forEach {
        println(it)
    }

    PriorityQueue<Int>().apply {
        add(1)
        add(2)
        add(3)
    }.forEach {
        println(it)
    }

    IntArray(3) { it + 1 }.forEach {
        println(it)
    }

    Array(3) { it + 1 }.forEach {
        println(it)
    }

    ArrayList<Int>().apply {
        add(1)
        add(2)
        add(3)
    }.forEach {
        println(it)
    }

    listOf(1, 2, 3).forEach {
        println(it)
    }

    arrayListOf(1, 2, 3).toArray().forEach {
        println(it)
    }

    mutableListOf(1, 2, 3).forEach {
        println(it)
    }

    java.util.ArrayList<Int>().apply {
        add(1)
        add(2)
        add(3)
    }.forEach {
        println(it)
    }

    java.util.ArrayDeque<Int>().apply {
        add(1)
        add(2)
        add(3)
    }.forEach {
        println(it)
    }

    ArrayDeque<Int>().apply {
        add(1)
        add(2)
        add(3)
    }.forEach {
        println(it)
    }

    val intarray: IntArray = intArrayOf(1, 2, 3)
    intarray.binarySearch(2).let {
        println(it)
    }


}

//    println(i1 === i2) // true, due to caching
//    println(i3 === i4) // false, outside cache range

fun main1(args: Array<String>) {
    println("Thread: ${Thread.currentThread().name}")
    println("Hello, World! Kotlin")

    runBlocking(Dispatchers.Default) {
        println("Thread: ${Thread.currentThread().name}")
        println("Hello, World! Kotlin")

        val asd = coroutineScope {
            println("Thread: ${Thread.currentThread().name}")
            println("Hello, World! Kotlin")

            val asd = CoroutineScope(Dispatchers.IO).launch {
            }
            5
        }
    }

    val asd = runBlocking {
        val asd = launch {
            println("Thread: ${Thread.currentThread().name}")
            println("Hello, World! Kotlin")

            val asd = withContext(Dispatchers.IO) {
                println("Thread: ${Thread.currentThread().name}")
                println("Hello, World! Kotlin")
                6
            }
        }

        val asd2 = async {
            println("Thread: ${Thread.currentThread().name}")
            println("Hello, World! Kotlin")
            9
        }

        9
    }
}

suspend fun main2() = coroutineScope {
    println("Thread: ${Thread.currentThread().name}")
    println("Hello, World! Kotlin")

    launch {
        delay(1000)
        println("Thread: ${Thread.currentThread().name}")
        println("Kotlin Coroutines World!")
    }
    println("Hello")
}


public class ByteArraya
public constructor(size: Int) {}