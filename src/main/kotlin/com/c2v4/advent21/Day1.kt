package com.c2v4.advent21

fun trebuchet(input: String) = input.split(EOL).sumOf { findNumbers(it) }

private fun findNumbers(line: String): Int =
    line.filter { it.isDigit() }.map { it.toString().toInt() }.let { it.first() * 10 + it.last() }
fun trebuchet2(input: String) = input.split(EOL).sumOf { findNumbers2(it) }

private val regex = Regex("""(\d|one|two|three|four|five|six|seven|eight|nine)""")
private fun findNumbers2(line: String): Int =
    line.indices.map {  i-> regex.find(line,i)
        .let {
        when (it?.value) {
            "one" -> 1
            "two" -> 2
            "three" -> 3
            "four" -> 4
            "five" -> 5
            "six" -> 6
            "seven" -> 7
            "eight" -> 8
            "nine" -> 9
            null -> null
            else -> it?.value?.toInt()
        }
    }}.mapNotNull { it }.let { it.first() * 10 + it.last() }


fun main(args: Array<String>) {
    println(trebuchet("day1.txt".asResource()))
    println(trebuchet2("day1.txt".asResource()))
}
