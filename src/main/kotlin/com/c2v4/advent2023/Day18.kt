package com.c2v4.advent2023


fun lavaduct(input: String) = input.split(EOL).map { it.split(" ") }.map {
    when (it[0]) {
        "U" -> Direction.UP to it[1].toLong()
        "D" -> Direction.DOWN to it[1].toLong()
        "L" -> Direction.LEFT to it[1].toLong()
        "R" -> Direction.RIGHT to it[1].toLong()
        else -> throw IllegalArgumentException()
    }
}.let { mapToVertices(it) }.let { shoelaceArea(it) }


fun mapToVertices(directionSteps: List<Pair<Direction, Long>>): List<LongPoint> {
    val vertices = mutableListOf<LongPoint>()
    var currentPoint = LongPoint(0, 0)

    for ((direction, steps) in directionSteps) {
        when (direction) {
            Direction.UP -> currentPoint = LongPoint(currentPoint.first, currentPoint.second + steps)
            Direction.DOWN -> currentPoint = LongPoint(currentPoint.first, currentPoint.second - steps)
            Direction.LEFT -> currentPoint = LongPoint(currentPoint.first - steps, currentPoint.second)
            Direction.RIGHT -> currentPoint = LongPoint(currentPoint.first + steps, currentPoint.second)
        }
        vertices.add(currentPoint)
    }

    return vertices
}

typealias LongPoint = Pair<Long, Long>

fun shoelaceArea(points: List<LongPoint>): Long {
    var area = 0L
    for (i in points.indices) {
        val j = (i + 1) % points.size
        area += points[i].first * points[j].second
        area -= points[j].first * points[i].second
    }
    area /= 2
    return kotlin.math.abs(area)
}

fun lavaduct2(input: String) = input.split(EOL).map { it.split(" ") }.map {
    val toParse = it[2].drop(1).dropLast(1)
    when (toParse.last()) {
        '3' -> Direction.UP to toParse.dropLast(1).drop(1).toLong(16)
        '1' -> Direction.DOWN to toParse.dropLast(1).drop(1).toLong(16)
        '2' -> Direction.LEFT to toParse.dropLast(1).drop(1).toLong(16)
        '0' -> Direction.RIGHT to toParse.dropLast(1).drop(1).toLong(16)
        else -> throw IllegalArgumentException()
    }
}.let { mapToVertices(it) }.let { shoelaceArea(it + it[0]) }


fun main(args: Array<String>) {
    println(lavaduct("day18.txt".asResource()))
    println(lavaduct2("day18.txt".asResource()))
}
