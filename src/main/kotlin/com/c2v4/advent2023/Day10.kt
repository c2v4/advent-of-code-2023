package com.c2v4.advent2023

import kotlin.math.min

fun pipe(input: String) = input.split(EOL).let { lines ->
    val (map, start) = initialConditions(lines)
    findWay(start, map).size / 2
}

private fun initialConditions(lines: List<String>): Pair<List<CharArray>, Pair<Int, Int>> {
    val map = lines.map { it.toCharArray() }
    val start = lines.indices.map { i -> lines[i].indices.map { j -> Pair(i, j) } }
        .flatten()
        .first { lines[it.first][it.second] == 'S' }
    return Pair(map, start)
}

fun findWay(start: Point, map: List<CharArray>): List<Point> {
    val (_, initialDirection) = getNeighbours(start).first { (point, direction) ->
        val pipe = map.getOrElse(point.first) { charArrayOf() }.getOrElse(point.second) { '.' }
        if (pipe == '.') {
            return@first false
        }
        directionMap.getOrElse(pipe) { emptySet() }.contains(direction.opposite())
    }

    return findWay(map, listOf(start), initialDirection)
}

private fun getNeighbours(point: Point): Set<Pair<Point, Direction>> {
    val (i, j) = point
    return setOf(
        (i - 1 to j) to Direction.UP,
        (i to j - 1) to Direction.LEFT,
        (i to j + 1) to Direction.RIGHT,
        (i + 1 to j) to Direction.DOWN
    )
}

private tailrec fun findWay(map: List<CharArray>, visited: List<Point>, direction: Direction): List<Point> {
    if (visited.isEmpty()) return emptyList()
    val next = findNext(visited.last(), direction, map)
    if (visited.contains(next.first)) {
        return visited
    }
    return findWay(map, visited + next.first, next.second)
}

private enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    fun opposite(): Direction = when (this) {
        UP -> DOWN
        DOWN -> UP
        LEFT -> RIGHT
        RIGHT -> LEFT
    }

    fun translate(point: Point): Point = when (this) {
        UP -> point.copy(first = point.first - 1)
        DOWN -> point.copy(first = point.first + 1)
        LEFT -> point.copy(second = point.second - 1)
        RIGHT -> point.copy(second = point.second + 1)
    }
}

private fun findNext(lastLocation: Point, lastDirection: Direction, map: List<CharArray>): Pair<Point, Direction> {
    val (i, j) = lastDirection.translate(lastLocation)
    val pipe = map.getOrElse(i) { charArrayOf() }.getOrElse(j) { '.' }
    val directions = directionMap.getOrElse(pipe) { emptySet() }
    val nextDirection = directions.first { it != lastDirection.opposite() }
    return lastDirection.translate(lastLocation) to nextDirection
}

private val directionMap = mapOf(
    '|' to setOf(Direction.UP, Direction.DOWN),
    '-' to setOf(Direction.LEFT, Direction.RIGHT),
    'L' to setOf(Direction.UP, Direction.RIGHT),
    'J' to setOf(Direction.UP, Direction.LEFT),
    'F' to setOf(Direction.DOWN, Direction.RIGHT),
    '7' to setOf(Direction.DOWN, Direction.LEFT),
    'S' to setOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT),
    '.' to emptySet()
)

fun pipe2(input: String) = input.split(EOL).let { lines ->
    val (map, start) = initialConditions(lines)
    val loop = findWay(start, map)
    val mapWithCorrectStart = map.map { it.map { if (it == 'S') getCorrectStartTile(loop) else it }.toCharArray() }
    val enclosed = mapWithCorrectStart.indices.flatMap { i -> mapWithCorrectStart[i].indices.map { j -> Pair(i, j) } }
        .filter { !loop.contains(it) }
        .filter { isInTheLoop(it, loop, mapWithCorrectStart) }
    enclosed.count()
}

fun getCorrectStartTile(loop: List<Point>): Char {
    val (i, j) = loop[1]
    val (i2, j2) = loop.last()
    return when {
        i == i2 -> '-'
        j == j2 -> '|'
        i < i2 && j < j2 -> '7'
        i < i2 && j > j2 -> 'F'
        i > i2 && j < j2 -> 'J'
        i > i2 && j > j2 -> 'L'
        else -> throw IllegalArgumentException()
    }
}

fun isInTheLoop(point: Point, loop: List<Point>, map: List<CharArray>): Boolean =
    map[point.first].indices.asSequence().filter { it > point.second }
        .map { point.first to it }.filter { loop.contains(it) }.map {
            val (i, j) = it
            val pipe = map.getOrElse(i) { charArrayOf() }.getOrElse(j) { '.' }
            when (pipe) {
                '|' -> 1 to 1
                'L', 'J' -> 1 to 0
                '7', 'F' -> 0 to 1
                '-' -> 0 to 0
                else -> throw IllegalArgumentException()
            }
        }.fold(0 to 0) { acc, pair -> acc.first + pair.first to acc.second + pair.second }.let {
            min(it.first, it.second) % 2 != 0
        }


fun main(args: Array<String>) {
    println(pipe("day10.txt".asResource()))
    println(pipe2("day10.txt".asResource()))
}
