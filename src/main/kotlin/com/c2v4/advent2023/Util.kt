package com.c2v4.advent2023

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


fun String.asResource() = Thread.currentThread().contextClassLoader.getResource(this).readText()

val EOL = Regex("\\r?\\n")

val PARAGRAPH = Regex("\\r?\\n\\r?\\n")
suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}
fun <T> transpose(input: List<List<T>>): List<List<T>> {
    // Check if the input list is empty or contains empty char arrays
    if (input.isEmpty() || input[0].isEmpty()) return listOf()

    val numRows = input.size
    val numCols = input[0].size

    // Create a list to store the transposed char arrays
    val transposed = MutableList(numCols) { mutableListOf<T>() }

    for (i in 0 until numRows) {
        for (j in 0 until numCols) {
            transposed[j][i] = input[i][j]
        }
    }

    return transposed
}