package easy

import kotlin.time.measureTime

fun main(args: Array<String>) {
    val time = measureTime {
        println(isValidParentheses("{([])}"))
        println(isValidParentheses("{}([])"))
        println(isValidParentheses("{(][)}"))
    }
    println(time)
}

private fun isValidParentheses(s: String): Boolean {
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