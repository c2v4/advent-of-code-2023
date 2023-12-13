package com.c2v4.advent2023

import kotlinx.coroutines.runBlocking

fun folds(input: String, folds: Int = 1) = input.split(EOL).let {
    runBlocking {
        it.pmap { line ->
            val split = line.split(" ")
            val string = (0 until folds).joinToString("?") { split[0] }
            val groups = split[1].split(",").map { it.toInt() }.repeatList(folds)
            springArrangements(string, groups)
        }.sum()
    }
}

fun <T> List<T>.repeatList(n: Int): List<T> {
    return List(n) { this }.flatten()
}
fun springArrangements(row: String, brokenGroupLengths: List<Int>): Long {
    val cache = Array(row.length) {
        LongArray(brokenGroupLengths.size + 1) { -1L }
    }
    fun brokenGroupPossible(from: Int, to: Int): Boolean = when {
        to > row.length -> {
            false
        }

        to == row.length -> {
            (from until to).all { row[it] != '.' }
        }

        else -> {
            (from until to).all { row[it] != '.' } && row[to] != '#'
        }
    }

    fun compute(i: Int, j: Int): Long {
        if (i == row.length) {
            return if (j == brokenGroupLengths.size) 1 else 0
        }

        if (cache[i][j] != -1L) {
            return cache[i][j]
        }

        fun computeWorking(): Long =
            compute(i + 1, j)

        fun computeBroken(): Long {
            if (j == brokenGroupLengths.size) {
                return 0
            }
            val endGroupIdx = i + brokenGroupLengths[j]
            if (!brokenGroupPossible(i, endGroupIdx)) {
                return 0
            }
            if (endGroupIdx == row.length) {
                return if (j == brokenGroupLengths.size - 1) 1 else 0
            }
            return compute(endGroupIdx + 1, j + 1)
        }

        return when (val c = row[i]) {
            '.' -> computeWorking()
            '#' -> computeBroken()
            '?' -> computeWorking() + computeBroken()
            else -> throw RuntimeException("Illegal character in spring record: $c")
        }
            .also { cache[i][j] = it }
    }

    return compute(0, 0)
}

fun main(args: Array<String>) {
    println(folds("day12.txt".asResource(), 1))
    println(folds("day12.txt".asResource(), 5))
}
