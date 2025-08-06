import java.net.HttpURLConnection
import java.net.URL


fun main() {
//    println(StringChallenge("Hello", 4)) // Output: Lipps
//    println(StringChallenge("abc", 0))   // Output: abc

    val input = arrayOf("4", "A", "B", "C", "D", "A-B", "B-D", "B-C", "C-D")
    println(GraphChallenge(input))
}

/*
Back-end Challenge In the Kotlin file,
write a program to perform a GET request on the route htttp://coderbyte.com/api/challenges/logs/user-info-csv and then sort the CSV data by the second column.
Finally, print it as comma-separated values.
Example Input: name,email,phone Jane Smith,janesmith@example.com,555-5678 John Doe,johndoe@example.com,555-1234
Example Output: Jane Smith, janesmith@example.com, 555-5678, John Doe, johndoe@example, 555-1234
 */
fun cvs() {
    val url = URL("https://coderbyte.com/api/challenges/logs/user-info-csv")
    val connection = url.openConnection() as HttpURLConnection

//    val response = BufferedReader(InputStreamReader(connection.inputStream)).use {
//        it.readText()
//    }

    val response = "Name,Email,Phone\n" +
            "Harry Potter,harrypotter@hogwarts.edu,555-1234\n" +
            "Hermione Granger,hermionegranger@hogwarts.edu,555-5678\n" +
            "Ron Weasley,ronweasley@hogwarts.edu,555-2468\n" +
            "Luna Lovegood,lunalovegood@hogwarts.edu,555-3691\n" +
            "Draco Malfoy,dracomalfoy@hogwarts.edu,555-8024\n" +
            "Neville Longbottom,nevillelongbottom@hogwarts.edu,555-1357\n" +
            "Ginny Weasley,ginnyweasley@hogwarts.edu,555-2468\n" +
            "Cho Chang,chochang@hogwarts.edu,555-3691\n" +
            "Fred Weasley,fredweasley@hogwarts.edu,555-8024\n" +
            "Lavender Brown,lavenderbrown@hogwarts.edu,555-1357"

    val lines = response.split("\n").filter { it.isNotBlank() }
    val header = lines.first()
    val data = lines.drop(1)

    val sortedData = data.sortedBy { it.split(",")[1] }

//    sortedData.forEach { println(it) }
    val commaSeparated = sortedData.joinToString(", ") {
        it.replace(",", ", ")
    }
    println(commaSeparated)
}

/*
String Challenge
Have the function StringChallenge(str,num) take the str parameter and perform a Caesar Cipher shift on it using the num parameter as the shifting number. A Caesar Cipher works by shifting each letter in the string N places in the alphabet (in this case N will be num). Punctuation, spaces, and capitalization should remain intact. For example if the string is "Caesar Cipher" and num is 2 the output should be "Ecguct Ekrjgt".
Examples
Input: "Hello" & num = 4
Output: Lipps
Input: "abc" & num = 0
Output: abc
 */
fun StringChallenge(str: String, num: Int): String {
    val shift = num % 26
    return str.map { char ->
        when {
            char.isUpperCase() -> 'A' + (char - 'A' + shift) % 26
            char.isLowerCase() -> 'a' + (char - 'a' + shift) % 26
            else -> char
        }
    }.joinToString("")
}

fun GraphChallenge(strArr: Array<String>): String {
    // A--B
    // | /
    // C-D-F

    val numNodes = strArr[0].toInt()
    val nodes = strArr.slice(1..numNodes)
    val edges = strArr.slice(1 + numNodes..strArr.lastIndex)

    val graph = mutableMapOf<String, MutableSet<String>>()
    // Filling nodes
    nodes.forEach {
        graph[it] = mutableSetOf()
    }
    // Filling edges
    edges.forEach {
        val (from, to) = it.split("-")
        graph[from]?.add(to)
        graph[to]?.add(from)
    }
    // A->{B,C}
    // B->{A,C}
    // C->{A,B,D}
    // D->{C,F}
    // F->{D}

    fun shortestPath(start: String, end: String):List<String> {

        return emptyList()
    }

    // BFS to find shortest path
    fun bfs(start: String, end: String): List<String> {
        val queue = ArrayDeque<String>()
        val visited = mutableSetOf<String>()
        val parent = mutableMapOf<String, String>()

        queue.add(start)
        visited.add(start)

        while (queue.isNotEmpty()) {
            println("bfs")
            val current = queue.removeFirst()

            if (current == end) {
                // Reconstruct path
                val path = mutableListOf<String>()
                var node = end
                while (node != start) {
                    path.add(0, node)
                    node = parent[node]!!
                }
                path.add(0, start)
                return path
            }

            graph[current]?.forEach { neighbor ->
                if (neighbor !in visited) {
                    queue.add(neighbor)
                    visited.add(neighbor)
                    parent[neighbor] = current
                }
            }
        }

        return emptyList()
    }

    // Find shortest path from first to last node
    val path = bfs(nodes.first(), nodes.last())

    // Return formatted path
    return if (path.isEmpty()) "no path exists" else path.joinToString("-")
}