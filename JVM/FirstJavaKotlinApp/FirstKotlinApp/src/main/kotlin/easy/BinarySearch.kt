package easy

fun main() {
    // Test case 1: Target found in the middle
    val list1 = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
    println("Test 1 - Finding 3.0: ${binarySearch(list1, 3.0)}") // Expected: 2

    // Test case 2: Target found at the beginning
    println("Test 2 - Finding 1.0: ${binarySearch(list1, 1.0)}") // Expected: 0

    // Test case 3: Target found at the end
    println("Test 3 - Finding 5.0: ${binarySearch(list1, 5.0)}") // Expected: 4

    // Test case 4: Target not found
    println("Test 4 - Finding 6.0: ${binarySearch(list1, 6.0)}") // Expected: -1

    // Test case 5: Empty list
    val list2 = emptyList<Double>()
    println("Test 5 - Empty list: ${binarySearch(list2, 1.0)}") // Expected: -1

    // Test case 6: Single element found
    val list3 = listOf(5.0)
    println("Test 6 - Single element found: ${binarySearch(list3, 5.0)}") // Expected: 0

    // Test case 7: Single element not found
    println("Test 7 - Single element not found: ${binarySearch(list3, 3.0)}") // Expected: -1

    // Test case 8: Target with decimals
    val list4 = listOf(1.5, 2.5, 3.5, 4.5, 5.5)
    println("Test 8 - Finding 3.5: ${binarySearch(list4, 3.5)}") // Expected: 2
}

fun binarySearch(sortedAmounts: List<Double>, target: Double): Int {
    var left = 0
    var right = sortedAmounts.lastIndex

    while (left <= right) {
        val mid = left + (right - left) / 2
        when {
            sortedAmounts[mid] == target ->
                return mid
            sortedAmounts[mid] < target ->
                left = mid + 1
            else ->
                right = mid - 1
        }
    }
    return -1
}