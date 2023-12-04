package com.c2v4.advent21

import kotlin.math.pow

fun scratchcards(input: String) = input.split(EOL).sumOf { line ->
    2.toDouble().pow((winnings(line)-1).toDouble()).toInt()
}

private fun winnings(line: String): Int {
    val split = line.split(":")[1].split("|").map { it.trim().split(Regex("\\s+")).map { it.toInt() } }
    return split[0].intersect(split[1].toSet()).size
}

fun scratchcards2(input: String) = input.split(EOL).map { line ->
    winnings(line)
}.fold(0 to emptyList<Int>()) { acc, i ->
    acc.first + 1 + acc.second.size to (acc.second.map { it - 1 } + List(acc.second.size+1) { i }).filter { it > 0 }
}.first

fun main(args: Array<String>) {
    println(scratchcards("day4.txt".asResource()))
    println(scratchcards2("day4.txt".asResource()))
}
