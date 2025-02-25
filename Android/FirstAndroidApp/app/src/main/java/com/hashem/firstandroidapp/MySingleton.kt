package com.hashem.firstandroidapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** Created by Hashem. */
open class Test constructor(val hi: String) {

    fun test() {
        hi.uppercase()
    }
}

class TestSub constructor() : Test("") {
    fun test2() {
        hi.uppercase()
    }
}

class MySingleton private constructor(callback: () -> Unit) {
    companion object {
        private var ourInstance: MySingleton? = null

        fun getInstance(callback: () -> Unit): MySingleton? {
            if (ourInstance == null) {
                ourInstance = MySingleton(callback)
            }
            return ourInstance
        }
    }

    init {
        CoroutineScope(Main).launch {
            delay(5000)
            callback()
        }
    }

    @JvmInline value class EmployeeId(private val id: String)

    @JvmInline value class CustomerId(private val id: String)

    fun doSomething() {
        var v = arrayOf(1, 2, 3, 4, 5)
        for (i in v) {
            println(i)
        }

        val v2 = arrayListOf(1, 2, 3, 4, 5)
        for (i in v) {
            println(i)
        }

        val v3 = listOf(1, 2, 3, 4, 5)

        val upperCaseString: (String) -> String = { text -> text.uppercase() }
        val pairArray = arrayOf("apple" to 120, "banana" to 150, "cherry" to 90, "apple" to 140)

        ArrayDeque<Int>()
                .apply {
                    add(1)
                    add(2)
                    add(3)
                }
                .forEach { println(it) }
    }
}
