package com.c2v4.advent2023

fun parabolic(input: String) = input.split(EOL).map { it.toCharArray() }.let { grid ->
    val newGrid = moveRocks(grid, Direction.UP)
    val sumOf = newGrid.withIndex().sumOf { (i, row) ->
        row.count { it == 'O' } * (grid.size - i)
    }
    return@let sumOf
}
fun parabolic2(input: String) = input.split(EOL).map { it.toCharArray() }.let { initialGrid ->
    var grid = initialGrid
    val seen = mutableMapOf<List<String>, Int>()
    var cycle = 0
    val total = 1_000_000_000
    var length = 0

    while (cycle < total) {
        val cur = grid.map { it.concatToString() }
        if (cur in seen) {
            length = cycle - seen[cur]!!
            break
        }
        seen[cur] = cycle

        grid = moveRocks(grid, Direction.UP)
        grid = moveRocks(grid, Direction.LEFT)
        grid = moveRocks(grid, Direction.DOWN)
        grid = moveRocks(grid, Direction.RIGHT)
        cycle++
    }

    if (length > 0) {
        val remainingCycles = (total - cycle) % length
        for (i in 0 until remainingCycles) {
            grid = moveRocks(grid, Direction.UP)
            grid = moveRocks(grid, Direction.LEFT)
            grid = moveRocks(grid, Direction.DOWN)
            grid = moveRocks(grid, Direction.RIGHT)
        }
    }

    return@let grid.withIndex().sumOf { (i, row) ->
        row.count { it == 'O' } * (grid.size - i)
    }
}
private fun moveRocks(grid: List<CharArray>, direction: Direction): List<CharArray> {
    val n = grid.size
    val m = grid[0].size
    val new = Array(n) { CharArray(m) { '.' } }

    when (direction) {
        Direction.UP -> {
            for (j in 0 until m) {
                var index = 0
                for (i in 0 until n) {
                    if (grid[i][j] == 'O') {
                        new[index++][j] = 'O'
                    } else if (grid[i][j] == '#') {
                        new[i][j] = '#'
                        index = i + 1
                    }
                }
            }
        }
        Direction.LEFT -> {
            for (i in 0 until n) {
                var index = 0
                for (j in 0 until m) {
                    if (grid[i][j] == 'O') {
                        new[i][index++] = 'O'
                    } else if (grid[i][j] == '#') {
                        new[i][j] = '#'
                        index = j + 1
                    }
                }
            }
        }
        Direction.DOWN -> {
            for (j in 0 until m) {
                var index = n - 1
                for (i in n - 1 downTo 0) {
                    if (grid[i][j] == 'O') {
                        new[index--][j] = 'O'
                    } else if (grid[i][j] == '#') {
                        new[i][j] = '#'
                        index = i - 1
                    }
                }
            }
        }
        Direction.RIGHT -> {
            for (i in 0 until n) {
                var index = m - 1
                for (j in m - 1 downTo 0) {
                    if (grid[i][j] == 'O') {
                        new[i][index--] = 'O'
                    } else if (grid[i][j] == '#') {
                        new[i][j] = '#'
                        index = j - 1
                    }
                }
            }
        }
    }
    return new.toList()
}

fun main(args: Array<String>) {
    println(parabolic("day14.txt".asResource()))
    println(parabolic2("day14.txt".asResource()))
}
