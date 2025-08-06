package com.hashem.firstandroidapp

import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date
import java.util.PriorityQueue
import java.util.TimeZone

class KotlinTest {
    @Test
    fun test() {
        println("Hello from KotlinTest")

        Assert.assertTrue(true)

        val stringOne = "Hello"
        val stringTwo = "Hello"

        println(stringOne === stringTwo)
        println(stringOne == stringTwo)
        println(stringOne.equals(stringTwo))

        stringOne.let {

        }

        stringTwo.run {

        }

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

        println(heap.poll() ?: "")


    }

    @Test
    fun test2() {
        val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        val target = 0

        val result = search(nums, target)
        Assert.assertEquals(4, result)
    }

    @Test
    fun test3() {
        val zoneId = ZoneId.of("Europe/London")

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        format.timeZone = TimeZone.getTimeZone("UTC")

        //from server date to building date
        val utcDate: Date = format.parse("2025-06-29T23:00:00.0000000Z")
        val zonedDateTime = utcDate.toInstant().atZone(zoneId)
        val localDateTime = zonedDateTime.toLocalDate()

        //from building date to server date
        val selectedDateM = localDateTime.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
        val selectedDate = Instant.ofEpochMilli(selectedDateM).atZone(ZoneOffset.UTC)
        val selectedLocalDate = selectedDate.toLocalDate()

        val selectedDateOld = Date.from(selectedLocalDate.atStartOfDay(zoneId).toInstant())
        val dateFormatAsString = format.format(selectedDateOld)

        println(dateFormatAsString)
        println()


//        val dateString = "2025-06-29T23:00:00.0000000Z"
//        val utcDateTime = ZonedDateTime.parse(dateString)
//        val zonedDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Europe/London")).toLocalDateTime()
//        println(zonedDateTime)
//        println(zonedDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy")))
//        println()
    }


    private fun search(nums: IntArray, target: Int): Int {
        println(nums.size)

        val n = nums.size
        val mid = n / 2
        val left = nums[mid - 1]
        val right = nums[mid + 1]

        if (nums[mid] == target) {
            return mid
        }

        return if (target < nums[mid])
            search(nums.sliceArray(0 until mid), target)
        else
            search(nums.sliceArray(mid until n), target)
    }
}