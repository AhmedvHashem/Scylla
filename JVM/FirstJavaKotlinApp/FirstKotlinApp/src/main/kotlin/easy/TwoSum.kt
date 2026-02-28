package easy

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * The `twoSum` function finds two numbers in an array that add up to a target value and returns their indices.
 *
 * **Step-by-step breakdown:**
 *
 * 1. **Function signature**:
 *    - Takes an `IntArray` called `nums` (the array to search)
 *    - Takes an `Int` called `target` (the sum we're looking for)
 *    - Returns an `IntArray` (the indices of the two numbers)
 *
 * 2. **Outer loop**:
 *    - Iterates through each index `i` in the array using `nums.indices`
 *    - This represents the first number in our potential pair
 *
 * 3. **Inner loop**:
 *    - Iterates from `i + 1` to the end of the array
 *    - This ensures we don't use the same element twice and avoid duplicate pairs
 *    - `j` represents the second number in our potential pair
 *
 * 4. **Check condition**:
 *    - Tests if `nums[i] + nums[j]` equals the `target`
 *    - If true, immediately returns an array containing indices `[i, j]`
 *    - This is the solution to the problem
 *
 * 5. **Fallback return**:
 *    - Returns an empty `IntArray` if no pair is found
 *    - Though according to the problem statement, a solution always exists
 *
 * **Time Complexity:** O(n²) - nested loops check all possible pairs
 * **Space Complexity:** O(1) - only uses constant extra space
 **/
fun twoSum(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices) {
        for (j in i + 1 until nums.size) {
            if (nums[i] + nums[j] == target) return intArrayOf(i, j)
        }
    }
    return intArrayOf()
}

fun twoSumEfficient(nums: IntArray, target: Int): Pair<Int, Int>? {
    val map = HashMap<Int, Int>() // value → index

    for ((index, amount) in nums.withIndex()) {
        val complement = target - amount
        if (map.containsKey(complement)) {
            return Pair(map[complement]!!, index)
        }
        map[amount] = index
    }
    return null
}

fun twoSumSorted(nums: IntArray, target: Int): Pair<Int, Int>? {
    var left = 0
    var right = nums.lastIndex

    while (left < right) {
        val sum = nums[left] + nums[right]
        when {
            sum == target -> return left to right
            sum < target -> left++
            else -> right--
        }
    }
    return null
}