package easy

/**
 * Merge overlapping time intervals.
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 */
fun mergeIntervals(intervals: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    if (intervals.isEmpty()) return emptyList()

    val sorted = intervals.sortedBy { it.first }
    val result = mutableListOf(sorted[0])

    for (i in 1 until sorted.size) {
        val last = result.last()
        if (sorted[i].first <= last.second) {
            // Overlapping - merge
            result[result.lastIndex] = last.first to maxOf(last.second, sorted[i].second)
        } else {
            result.add(sorted[i])
        }
    }
    return result
}