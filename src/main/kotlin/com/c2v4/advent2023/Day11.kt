package com.c2v4.advent2023

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


fun cosmic(input: String, inflation: Long = 1L) = input.split(EOL).let { list ->
    val yIndices = list.indices.filter { i -> !list[i].contains('#') }
    val xIndices = list[0].indices.filter { i -> list.all { it[i] != '#' } }
    list.map { it.toCharArray() } to (yIndices to xIndices)
}.let { (map, indices) ->
    map.indices.flatMap { i -> map[i].indices.map { j -> Pair(i, j) } }
        .filter { map[it.first][it.second] == '#' }
        .let { points ->
            points.indices.map { i ->
                points[i] to points.subList(i + 1, points.size)
            }.sumOf { (point, others) ->
                others.sumOf {
                    manhattanDistance(point, it, indices, if (inflation == 1L) 1 else inflation - 1)
                }
            }
        }
}

fun manhattanDistance(
    point: Pair<Int, Int>,
    it: Pair<Int, Int>,
    indices: Pair<List<Int>, List<Int>>,
    inflation: Long
): Long {
    val (y1, x1) = point
    val (y2, x2) = it

    return abs(x1 - x2) + abs(y1 - y2) + indices.first.count {
        (min(y1, y2)..max(
            y1,
            y2
        )).contains(it)
    } * inflation + indices.second.count { (min(x1, x2)..max(x1, x2)).contains(it) } * inflation
}


fun cosmic2(input: String) = cosmic(input, 100L)

fun main(args: Array<String>) {
    println(cosmic("day11.txt".asResource()))
    println(cosmic("day11.txt".asResource(), 1_000_000L))
}
