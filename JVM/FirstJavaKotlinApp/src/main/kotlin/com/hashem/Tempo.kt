package com.hashem

fun main() {
    println(operations(listOf("INSERT Code", "INSERT Signal", "UNDO", "UNDO", "BACKSPACE", "UNDO")))
}

fun operations(operations: List<String>): List<String> {
    val results = mutableListOf<String>()
    for (operation in operations) {
        val split = operation.split(" ")
        val op = split.first()
        val text = split.lastOrNull() ?: ""
        when (op) {
            "INSERT" -> {
                results.add((results.lastOrNull() ?: "") + text)
            }

            "BACKSPACE" -> {
                if (results.isNotEmpty())
                    results.add(results.last().substring(0, results.last().length - 1))
            }

            "UNDO" -> {
                if (results.isNotEmpty())
                    results.add(results[results.size - 2])
            }
        }
    }
    return results
}