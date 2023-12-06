package com.c2v4.advent2023

fun waitForIt(input: String) = input.split(EOL).let {
    val times = it[0].split(Regex("\\s+")).drop(1).map { it.toLong() }
    val distances = it[1].split(Regex("\\s+")).drop(1).map { it.toLong() }
    times.zip(distances).map { (time, distance) ->
        waysToBeat(time, distance)
    }.reduce(Long::times)
}

private fun waysToBeat(time: Long, distance: Long): Long {
    var waysToBeat = ((time + 1) % 2)
    var middle = time / 2 + 1
    while ((middle * (time - middle)) > distance) {
        waysToBeat += 2
        middle++
    }
    return waysToBeat
}

fun waitForIt2(input: String) = input.split(EOL).let {
    val time = it[0].split(":")[1].replace(" ", "").toLong()
    val distance = it[1].split(":")[1].replace(" ", "").toLong()
    waysToBeat(time, distance)
}

fun main(args: Array<String>) {
    println(waitForIt("day06.txt".asResource()))
    println(waitForIt2("day06.txt".asResource()))
}
