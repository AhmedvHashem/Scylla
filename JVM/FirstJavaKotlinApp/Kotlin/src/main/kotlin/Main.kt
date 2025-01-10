import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.PriorityQueue
import java.util.Stack

infix fun String.example(function: () -> Unit) {
    println("---Example of $this---")
    function()
    println()
}

suspend fun main(args: Array<String>) {
    println("Hello, World! Kotlin")

        coroutineScope {
            val flow = flowOf(1, 2, 3, 4, 5)
            flow.collect {
                println(it)
            }

            flow.collect {
                println(it)
            }

            val stateFlow = MutableSharedFlow<Int>(
                replay = 1,
                extraBufferCapacity = 1
            )
            stateFlow.emit(1)
            stateFlow.collect {
                println(it)
            }

    }

    return

    "int caching" example {
        // Caching behavior
        val i1: Int? = 127
        val i2: Int? = 127

        val i3: Int? = 128
        val i4: Int? = 128

        println(i1 === i2) // true, due to caching
        println(i3 === i4) // false, outside cache range
        println(i1 === i2) // true, due to caching
        println(i3 === i4) // false, outside cache range
        println(i1 == i2) // true, due to caching
        println(i3 == i4) // false, outside cache range
        println(i1?.equals(i2)) // true, due to caching
        println(i3?.equals(i4)) // false, outside cache range
    }

    "kotlin collection" example {

        hashMapOf("a" to 1, "b" to 2).forEach { (key, value) ->
            println("$key -> $value")
        }

        linkedMapOf("a" to 1, "b" to 2).forEach { (key, value) ->
            println("$key -> $value")
        }

        mapOf("a" to 1, "b" to 2).forEach { (key, value) ->
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

    "insertion sort" example {
        val x = intArrayOf(5, 2, 9, 1, 3)
        var key: Int

        for (i in 1 until x.size) {
            key = x[i]

            var j = i - 1

            for (j in i - 1 downTo 0) {
                if (key < x[j]) {
                    x[j + 1] = x[j]
                } else {
                    break
                }
            }
//            while  (j >= 0 && key < x[j]) {
//                x[j + 1] = x[j]
//                j--
//            }
        }
        println("Insertion sort: ${x.joinToString()}")
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