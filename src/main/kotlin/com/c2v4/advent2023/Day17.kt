package com.c2v4.advent2023

import java.util.*


fun clumsy(input: String): Int {
    val grid = input.split(EOL).map { it.map { it.toString().toInt() } }
    return dijkstra(grid)
}

fun clumsy2(input: String): Int {
    val grid = input.split(EOL).map { it.map { it.toString().toInt() } }
    return dijkstra2(grid)
}

fun dijkstra(grid: List<List<Int>>): Int {
    val start = Pair(0, 0)
    val end = Pair(grid.size - 1, grid[0].size - 1)
    val queue = PriorityQueue<Triple<Int, List<Pair<Int, Int>>, Pair<Direction, Int>>>(compareBy { it.first })
    queue.add(Triple(0, listOf(start), Direction.DOWN to 0))
    val costs = grid.map { it.map { Array(4) { Array(4){Int.MAX_VALUE} } }.toMutableList() }.toMutableList()
    while (queue.isNotEmpty()) {
        val (distance, path, beam) = queue.poll()
        val current = path.last()
        val (lastDirection, steps) = beam
        if (distance >= costs[current.first][current.second][lastDirection.ordinal][steps]) {
            continue
        }
        costs[current.first][current.second][lastDirection.ordinal][steps] = distance
        if (current == end) {
            return distance
        }
        val directions = Direction.entries.minus(lastDirection.opposite())
        for (direction in directions) {
            val neighbour = direction.translate(current)
            val (nx, ny) = neighbour
            val newSteps = if (lastDirection == direction) steps + 1 else 1
            if (newSteps == 4) {
                continue
            }
            if (nx in grid.indices && ny in grid[0].indices) {
                val newDistance = distance + grid[nx][ny]
                if (!path.contains(neighbour)) {
                    queue.add(Triple(newDistance, path + neighbour, direction to newSteps))
                }
            }
        }
    }
    return -1

}

fun dijkstra2(grid: List<List<Int>>): Int {
    val start = Pair(0, 0)
    val end = Pair(grid.size - 1, grid[0].size - 1)
    val queue = PriorityQueue<Triple<Int, List<Pair<Int, Int>>, Pair<Direction, Int>>>(compareBy { it.first })
    queue.add(Triple(0, listOf(start), Direction.DOWN to 5))
    queue.add(Triple(0, listOf(start), Direction.RIGHT to 5))
    val costs = grid.map { it.map { Array(4) { Array(11){Int.MAX_VALUE} } }.toMutableList() }.toMutableList()
    while (queue.isNotEmpty()) {
        val (distance, path, beam) = queue.poll()
        val current = path.last()
        val (lastDirection, steps) = beam
        if (distance >= costs[current.first][current.second][lastDirection.ordinal][steps]) {
            continue
        }
        if (current == end) {
            if (steps < 4) {
                continue
            }
            return distance
        }
        costs[current.first][current.second][lastDirection.ordinal][steps] = distance
        val directions = Direction.entries.minus(lastDirection.opposite())
        for (direction in directions) {
            val neighbour = direction.translate(current)
            val (nx, ny) = neighbour
            if (direction != lastDirection && steps < 4) {
               continue
            }
            val newSteps = if (lastDirection == direction) steps + 1 else 1
            if (direction == lastDirection && newSteps > 9) {
                continue
            }
            if (nx in grid.indices && ny in grid[0].indices) {
                val newDistance = distance + grid[nx][ny]
                if (!path.contains(neighbour)) {
                    queue.add(Triple(newDistance, path + neighbour, direction to newSteps))
                }
            }
        }
    }
    return -1

}


fun main(args: Array<String>) {

    println(clumsy("day17.txt".asResource()))
    println(clumsy2("day17.txt".asResource()))
}