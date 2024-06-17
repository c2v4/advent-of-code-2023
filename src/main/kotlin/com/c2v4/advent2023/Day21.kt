package com.c2v4.advent2023

fun step(input: String, numberOfSteps: Int = 64) = input.split(EOL).let {
    val maxX = it.first().length
    val maxY = it.size
    var start: Point = Point(0, 0)
    val blockades = it.mapIndexed { y, s ->
        s.mapIndexed { x, c ->
            when (c) {
                '#' -> x to y
                'S' -> {
                    start = Point(x, y)
                    x to y
                }

                else -> null
            }
        }.filterNotNull()
    }
    findAccessiblePoints(setOf(start), blockades.flatten().toSet(), maxX, maxY, finalSteps = numberOfSteps).size +1
}

private tailrec fun findAccessiblePoints(
    toExplore: Set<Point>,
    blockades: Set<Point>,
    maxX: Int,
    maxY: Int,
    steps: Int = 0,
    finalSteps: Int = 0,
    visited: Set<Point> = emptySet(),
    accessiblePoints: Set<Point> = emptySet()
): Set<Point> {
    if (toExplore.isEmpty()) {
        return accessiblePoints
    }
    if (steps > finalSteps) {
        return accessiblePoints
    }
    val newPoints = toExplore.flatMap {
        it.neighbours(maxX, maxY)
    }.filter {
        it !in visited && it !in blockades && it !in toExplore
    }.toSet()
    return findAccessiblePoints(
        newPoints,
        blockades,
        maxX,
        maxY,
        steps + 1,
        finalSteps,
        visited + toExplore,
        accessiblePoints + if (steps % 2 == 1) newPoints else emptySet()
    )
}

val Point.x: Int
    get() = first
val Point.y: Int
    get() = second

private fun Point.neighbours(maxX: Int, maxY: Int): List<Point> {
    val result = mutableListOf<Point>()
    if (x > 0) {
        result.add(Point(x - 1, y))
    }
    if (x < maxX) {
        result.add(Point(x + 1, y))
    }
    if (y > 0) {
        result.add(Point(x, y - 1))
    }
    if (y < maxY) {
        result.add(Point(x, y + 1))
    }
    return result
}

fun step2(input: String) = input.split(EOL).let {
    0
}


fun main(args: Array<String>) {
    println(step("day21.txt".asResource()))
    println(step2("day21.txt".asResource()))
}
