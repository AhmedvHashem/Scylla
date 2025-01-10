package com.hashem.firstandroidapp

import org.junit.Assert
import org.junit.Test
import java.util.PriorityQueue

class KotlinTest {
    @Test
    fun test() {
        println("Hello from KotlinTest")

        Assert.assertTrue(true)

        val array = arrayOf(1, 2, 3, 2, 5)
        array.size
        array.reverse()
        array.sortedArray()
        array.union(arrayOf(4, 5, 6).toSet())
        array.intersect(arrayOf(4, 5, 6).toSet())
        array.contains(1)
        array.indexOf(1)
        array.last()
        array.first()
        array[1]
        array.sliceArray(0..1)
        array.lastIndexOf(2)

        val arrayList = arrayListOf(1, 2, 3)
        arrayList.sorted()

        val set = hashSetOf(1, 2, 3)
        set.contains(1)
        set.intersect(setOf(4, 5, 6))
        set.union(setOf(4, 5, 6))

        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        map.getValue("a")
        map.withDefault { 0 }
        map.flatMap { listOf(it.key, it.value) }
        map.filter { it.value > 1 }

        val queue = ArrayDeque<Int>()
        queue.add(1)
        queue.add(2)
        queue.removeLast()

        val deque = ArrayDeque<Int>()
        deque.add(1)
        deque.add(2)
        deque.removeFirst()

        val heap = PriorityQueue<Int>(compareByDescending { it })
        heap.add(5)
        heap.add(2)
        heap.add(3)

        println(heap.poll())
    }
}