package glovo

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    val result = list.alternatingSort { it }
    println(result)

    val str = "Hello, World!"
    val result2 = str.reverseWithExceptions { it.isLetter() }
    println(result2)

    val result3 = str.reverseLettersOnly()
    println(result3)
}

// 2025 interview
/**
 * Extension function to sort a list of elements in an alternating (zigzag) pattern:
 * [smallest, largest, second smallest, second largest, ...]
 *
 * @param selector Function to extract the comparable property for sorting.
 */
fun <T, R : Comparable<R>> List<T>.alternatingSort(selector: (T) -> R): List<T> {
    if (size <= 1) return this
    val sorted = this.sortedBy(selector)
    val result = ArrayList<T>(size)
    var left = 0
    var right = sorted.lastIndex
    while (left <= right) {
        if (left == right) {
            result.add(sorted[left])
        } else {
            result.add(sorted[left])
            result.add(sorted[right])
        }
        left++
        right--
    }
    return result
}

// in 2026 interview
/**
 * Reverses the string while keeping "exception" characters in their original positions.
 * 
 * @param isException A predicate that returns true if the character should stay in its place.
 */
fun String.reverseWithExceptions(isException: (Char) -> Boolean): String {
    val chars = this.toCharArray()
    var left = 0
    var right = chars.lastIndex

    while (left < right) {
        when {
            isException(chars[left]) -> left++
            isException(chars[right]) -> right--
            else -> {
                val temp = chars[left]
                chars[left] = chars[right]
                chars[right] = temp
                left++
                right--
            }
        }
    }
    return String(chars)
}

/**
 * Example usage: Reverses only alphabetic characters, keeping numbers and symbols in place.
 */
fun String.reverseLettersOnly(): String = reverseWithExceptions { !it.isLetter() }
