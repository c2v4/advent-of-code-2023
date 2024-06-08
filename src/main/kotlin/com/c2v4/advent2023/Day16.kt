package com.c2v4.advent2023

private const val RIGHT_MIRROR = '/'
private const val LEFT_MIRROR = '\\'
private const val HORIZONTAL = '-'
private const val VERTICAL = '|'
private const val EMPTY = '.'

fun lava(input: String) = input.split(EOL).let { it.map { it.toCharArray() } }.let { grid ->
    findVisited(grid, setOf((0 to 0) to Direction.RIGHT)).size

}

typealias Beam = Pair<Point, Direction>

tailrec fun findVisited(grid: List<CharArray>, beams: Set<Beam>, visited: Set<Beam> = emptySet()): Set<Point> {
    if (beams.isEmpty()) {
        return visited.map { it.first }.toSet()
    }
    val beam = beams.first()
    val newBeams = beams.drop(1).toSet()
    val (point, direction) = beam
    if (visited.contains(beam) || grid.getOrNull(point.second)?.getOrNull(point.first) == null) {
        return findVisited(grid, newBeams, visited)
    }
    val currentChar = grid[point.second][point.first]
    return when (currentChar) {
        HORIZONTAL -> when (direction) {
            Direction.UP, Direction.DOWN ->
                findVisited(
                    grid,
                    newBeams + (point + Direction.LEFT to Direction.LEFT) + (point + Direction.RIGHT to Direction.RIGHT),
                    visited + beam
                )

            Direction.LEFT, Direction.RIGHT -> findVisited(grid, newBeams + (point + direction to direction), visited + beam)
        }

        VERTICAL -> when (direction) {
            Direction.UP, Direction.DOWN -> findVisited(grid, newBeams + (point + direction to direction), visited + beam)
            Direction.LEFT, Direction.RIGHT ->
                findVisited(
                    grid,
                    newBeams + (point + Direction.UP to Direction.UP) + (point + Direction.DOWN to Direction.DOWN),
                    visited + beam
                )
        }

        RIGHT_MIRROR ->
            when (direction) {
                Direction.UP -> findVisited(grid, newBeams + (point + Direction.RIGHT to Direction.RIGHT), visited + beam)
                Direction.DOWN -> findVisited(grid, newBeams + (point + Direction.LEFT to Direction.LEFT), visited + beam)
                Direction.LEFT -> findVisited(grid, newBeams + (point + Direction.DOWN to Direction.DOWN), visited + beam)
                Direction.RIGHT -> findVisited(grid, newBeams + (point + Direction.UP to Direction.UP), visited + beam)
            }

        LEFT_MIRROR ->
            when (direction) {
                Direction.UP -> findVisited(grid, newBeams + (point + Direction.LEFT to Direction.LEFT), visited + beam)
                Direction.DOWN -> findVisited(grid, newBeams + (point to Direction.RIGHT), visited + beam)
                Direction.LEFT -> findVisited(grid, newBeams + (point + Direction.UP to Direction.UP), visited + beam)
                Direction.RIGHT -> findVisited(grid, newBeams + (point + Direction.DOWN to Direction.DOWN), visited + beam)
            }

        EMPTY -> findVisited(grid, newBeams + (point + direction to direction), visited + beam)
        else -> throw IllegalArgumentException("Unknown character $currentChar")
    }

}

operator fun Point.plus(direction: Direction): Point = this + direction.getDiff()
operator fun Point.plus(pair: Point): Point = Point(first + pair.first, second + pair.second)

private fun Direction.getDiff() = when (this) {
    Direction.UP -> Pair(0, -1)
    Direction.DOWN -> Pair(0, 1)
    Direction.LEFT -> Pair(-1, 0)
    Direction.RIGHT -> Pair(1, 0)
}


fun lava2(input: String) = input.split(EOL).let { 0 }

fun main(args: Array<String>) {
    println(lava("day16.txt".asResource()))
    println(lava2("day16.txt".asResource()))
}
