fun boundingRectangle(points: List<List<Int>>): List<Int> {
    // Initialize variables to store min and max values for x and y coordinates
    var minX = Int.MAX_VALUE
    var minY = Int.MAX_VALUE
    var maxX = Int.MIN_VALUE
    var maxY = Int.MIN_VALUE

    // Iterate through each point to find min and max values
    for (point in points) {
        minX = minOf(minX, point.first())
        minY = minOf(minY, point.last())
        maxX = maxOf(maxX, point.first())
        maxY = maxOf(maxY, point.last())
    }

    // Calculate width and height of the rectangle
    val width = maxX - minX
    val height = maxY - minY

    // Return the bounding rectangle as a list of 4 integers
    return listOf(minX, minY, width, height)
}


fun isDuoDigit(number: Int): String {
    val digits = number.toString().filter { it.isDigit() }
    val uniqueDigits = digits.toSet()
    return if (uniqueDigits.size > 2) "n" else "y"
}


fun computeGameState(nameP1: String, nameP2: String, wins: List<String>): String {
    var scoreP1 = 0
    var scoreP2 = 0
    val scoreMap = mapOf(0 to "0", 1 to "15", 2 to "30", 3 to "40")

    for (win in wins) {
        if (win == nameP1) {
            scoreP1++
        } else if (win == nameP2) {
            scoreP2++
        }
    }

    if (scoreP1 >= 4 && scoreP1 >= scoreP2 + 2) {
        return "$nameP1 WINS"
    } else if (scoreP2 >= 4 && scoreP2 >= scoreP1 + 2) {
        return "$nameP2 WINS"
    } else if (scoreP1 >= 3 && scoreP2 >= 3) {
        if (scoreP1 == scoreP2) {
            return "DEUCE"
        } else if (scoreP1 == scoreP2 + 1) {
            return "$nameP1 ADVANTAGE"
        } else if (scoreP2 == scoreP1 + 1) {
            return "$nameP2 ADVANTAGE"
        }
    }

    val p1Score = scoreMap[scoreP1] ?: "0"
    val p2Score = scoreMap[scoreP2] ?: "0"

    return if (p1Score == p2Score) {
        "${p1Score}a"
    } else {
        "$nameP1 $p1Score - $nameP2 $p2Score"
    }
}


fun compromisedServer(direction: String, x: Int, y: Int, width: Int, height: Int): Array<Int> {
    // Get the next coordinates to probe.
    val nextCoordinates = getNextCoordinates(direction, x, y)

    // If the next coordinates are within the data center and not the compromised server,
    // return them.
    return if (nextCoordinates[0] in 0 until width && nextCoordinates[1] in 0 until height && (nextCoordinates[0] != x || nextCoordinates[1] != y)) {
        nextCoordinates.toTypedArray()
    } else {
        // Otherwise, return the current coordinates.
        intArrayOf(x, y).toTypedArray()
    }
}

private fun getNextCoordinates(direction: String, x: Int, y: Int): IntArray {
    val nextCoordinatesMap = mapOf(
        "Up" to intArrayOf(x, y - 1),
        "UR" to intArrayOf(x + 1, y - 1),
        "R" to intArrayOf(x + 1, y),
        "DR" to intArrayOf(x + 1, y + 1),
        "D" to intArrayOf(x, y + 1),
        "DL" to intArrayOf(x - 1, y + 1),
        "Left" to intArrayOf(x - 1, y),
        "UL" to intArrayOf(x - 1, y - 1)
    )

    return nextCoordinatesMap[direction]!!
}
