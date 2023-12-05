package com.c2v4.advent2023

fun gearRatio(input: String) = input.split(EOL).map { it.toCharArray() }.let { map ->
    map.indices.flatMap { i -> map[i].indices.map { j -> Pair(i, j) } }
        .filter { !map[it.first][it.second].isDigit() && map[it.first][it.second] != '.' }
        .fold(emptyMap<Point, Int>()) { results, (i, j) ->
            val numbersAround = findNumbersAround(map, i, j)
            results.plus(numbersAround)

        }.values.sum()
}

private fun findNumbersAround(map: List<CharArray>, i: Int, j: Int): Map<Point, Int> =
    getNeighbours(i to j).filter { map[it.first][it.second].isDigit() }.associate { findNumber(it, map, emptyList()) }


private tailrec fun findNumber(start: Point, map: List<CharArray>, visited: List<Point>): Pair<Point, Int> {
    if (visited.isEmpty() && map[start.first].getOrNull(start.second - 1)?.isDigit() == true) {
        return findNumber(start.copy(second = start.second - 1), map, visited)
    }
    if (map[start.first].getOrNull(start.second + 1)?.isDigit() != true) {
        return start to (visited + start).fold(0) { acc, point ->
            acc * 10 + map[point.first][point.second].toString().toInt()
        }
    }
    return findNumber(start.copy(second = start.second + 1), map, visited + start)
}
private typealias Point = Pair<Int, Int>

private fun getNeighbours(point: Point): Set<Point> {
    val (i, j) = point
    return setOf(
        i - 1 to j - 1, i - 1 to j, i - 1 to j + 1, i to j - 1, i to j + 1, i + 1 to j - 1, i + 1 to j, i + 1 to j + 1
    )
}

fun gearRatio2(input: String) = input.split(EOL).map { it.toCharArray() }.let { map ->
    map.indices.flatMap { i -> map[i].indices.map { j -> Pair(i, j) } }
        .filter { map[it.first][it.second] == '*' }
        .fold(0) { results, (i, j) ->
            val numbersAround = findNumbersAround(map, i, j)
            if (numbersAround.size == 2) {
                results + numbersAround.values.reduce { acc, i -> acc * i }
            } else {
                results
            }
        }
}


fun main(args: Array<String>) {
    println(gearRatio("day03.txt".asResource()))
    println(gearRatio2("day03.txt".asResource()))
}
