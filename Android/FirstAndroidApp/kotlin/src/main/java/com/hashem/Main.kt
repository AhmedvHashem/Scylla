package com.hashem

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// Caching behavior
val i1: Int? = 127
val i2: Int? = 127

val i3: Int? = 128
val i4: Int? = 128

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

suspend fun main() = coroutineScope {
    println("Thread: ${Thread.currentThread().name}")
    println("Hello, World! Kotlin")

    launch {
        delay(1000)
        println("Thread: ${Thread.currentThread().name}")
        println("Kotlin Coroutines World!")
    }
    println("Hello")
}
