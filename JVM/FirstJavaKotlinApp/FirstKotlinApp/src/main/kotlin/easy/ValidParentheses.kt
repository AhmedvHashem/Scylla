package easy

import kotlin.time.measureTime

fun main(args: Array<String>) {
    val time = measureTime {
        println(isValidParentheses("{([])}"))
        println(isValidParentheses("{}([])"))
        println(isValidParentheses("{(][)}"))
        println(isValidParentheses("}(][){"))
    }
    println(time)
}

/**
 * The `isValidParentheses` function validates if brackets in a string are properly matched and balanced. Here's how it works:
 *
 * ## Step-by-Step Breakdown:
 *
 * 1. **Initialize Data Structures** (lines 15-16)
 *    - Creates an `ArrayDeque<Char>` as a stack to track opening brackets
 *    - Creates a map that pairs each closing bracket with its corresponding opening bracket
 *
 * 2. **Iterate Through Each Character** (line 18)
 *    - Loops through every character in the input string
 *
 * 3. **Check if Character is a Closing Bracket** (line 19)
 *    - Tests if the current character exists in the map's keys (closing brackets)
 *
 * 4. **Handle Closing Brackets** (lines 21-25)
 *    - If the stack is **not empty** AND the **top element matches** the corresponding opening bracket:
 *      - Removes the matched opening bracket from the stack (line 22)
 *    - Otherwise:
 *      - Returns `false` because there's either no opening bracket to match or it's the wrong type (line 24)
 *
 * 5. **Handle Opening Brackets** (line 27)
 *    - If the character is not a closing bracket, it must be an opening bracket
 *    - Adds it to the stack
 *
 * 6. **Final Validation** (line 30)
 *    - After processing all characters, returns `true` if the stack is empty (all brackets were matched)
 *    - Returns `false` if there are unmatched opening brackets remaining
 *
 */
private fun isValidParentheses(s: String): Boolean {
    val stack = ArrayDeque<Char>() // Stack to store opening brackets
    val hash = mapOf(')' to '(', ']' to '[', '}' to '{') // Matching pairs

    for (char in s) {
        // check if char is a Closing Bracket
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