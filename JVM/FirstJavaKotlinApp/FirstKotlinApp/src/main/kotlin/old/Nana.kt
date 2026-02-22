package old

import utils.runFunction
import utils.swap
import kotlin.text.iterator

fun main() {
    "mid".runFunction(false) {
        val arr = arrayOf(59, 66, 84, 13, 47, 58, 65, 14, 33)
//        arr.sortedArray()
        println(arr.size)
        println((arr.size) / 2)
        println(arr[arr.size / 2])
        println()
        println(arr.lastIndex)
        println(arr.lastIndex / 2)
        println(arr[arr.lastIndex / 2])
        println()
        println()
        println("8/2")
        println(8 / 2)
        println()
        println("9/2")
        println(9 / 2)

        bubbleSort(arr)

        val s = "aqwueitqweiuyqgwehgqkwe"
        val range = 0..6
        println(s.slice(range))
        println(s.substring(range))
    }

    "contains-duplicate".runFunction(false) {
        fun containsDuplicate(nums: IntArray): Boolean {
            for (i in nums.indices) {
//        println("------- $i")
                for (j in i + 1 until nums.size) {
//            println(j)
                    if (nums[i] == nums[j])
                        return true
                }
            }
            return false
        }

        println(containsDuplicate(intArrayOf(8, 1, 3, 1)))
    }

    "anagram".runFunction(false) {
        fun isAnagram(s: String, t: String): Boolean {
            if (s.length != t.length) {
                return false
            }
//            return s.toCharArray().sortedArray().contentEquals(t.toCharArray().sortedArray())

            val countS = mutableMapOf<Char, Int>()
            val countT = mutableMapOf<Char, Int>()

            for (i in s.indices) {
                countS[s[i]] = countS.getOrDefault(s[i], 0) + 1
                countT[t[i]] = countT.getOrDefault(t[i], 0) + 1
            }

            return countS == countT
        }

        println(isAnagram("anagram", "nagaram"))
        println(isAnagram("rat", "car"))
    }

    "valid-parentheses".runFunction(true) {
        fun isValid(s: String): Boolean {
            val stack = ArrayDeque<Char>() // Stack to store opening brackets
            val hash = mapOf(')' to '(', ']' to '[', '}' to '{') // Matching pairs

            for (char in s) {
                if (char in hash.keys) {
                    // Check if the stack is non-empty and matches the top element
                    if (stack.isNotEmpty() && stack.last() == hash[char]) {
                        stack.removeLast()
                    } else {
                        return false // Invalid if no match
                    }
                } else {
                    stack.add(char) // Push opening brackets
                }
            }
            return stack.isEmpty() // Valid if the stack is empty
        }

        println(isValid("()[]{}"))
        println(isValid("()[{}"))
        println(isValid("(}"))
        println(isValid("}"))
    }
}


fun bubbleSort(arr: Array<Int>) {

    println(arr.contentToString())
    println()
    // 1
    if (arr.size < 2) return
    // 2
    var swapped: Boolean
    for (end in arr.lastIndex downTo 1) {
        swapped = false
        // 3
        for (current in 0 until end) {
            println("$current to $end")

            if (arr[current] > arr[current + 1]) {
                // 4
                arr.swap(current, current + 1)
                swapped = true
            }
        }
        // 5
        println(arr.contentToString())
        // 6
        if (!swapped) return
    }
}