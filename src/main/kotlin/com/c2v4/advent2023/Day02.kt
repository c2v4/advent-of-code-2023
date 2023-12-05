package com.c2v4.advent2023

import java.util.*
import kotlin.math.max

fun cubeConundrum(input: String) = input.split(EOL).map { toGame(it) }.filter { isPossible(it) }.sumOf { it.gameNumber }


private enum class Cube {
    RED, GREEN, BLUE
}


private data class Game(val gameNumber: Int, val reveled: List<Map<Cube, Int>>)

private fun toGame(it: String) =
    it.drop("Game ".length).split(":").let { (gameNumber, cubes) ->
        Game(gameNumber.toInt(), cubes.split(";").map { toDraw(it) })
    }


private fun toDraw(s: String): Map<Cube, Int> {
    val map = mutableMapOf<Cube, Int>()
    s.split(",").forEach {
        val (number, color) = it.trim().split(" ")
        map[Cube.valueOf(color.uppercase(Locale.getDefault()))] = number.toInt()
    }
    return map
}

private fun isPossible(
    game: Game,
    limit: Map<Cube, Int> = mapOf(Cube.RED to 12, Cube.GREEN to 13, Cube.BLUE to 14)
): Boolean = game.reveled.all { isPossible(it, limit) }

private fun isPossible(
    draw: Map<Cube, Int>,
    limit: Map<Cube, Int> = mapOf(Cube.RED to 12, Cube.GREEN to 13, Cube.BLUE to 14)
): Boolean = draw.all { it.value <= (limit[it.key] ?: 0) }


fun cubeConundrum2(input: String): Int = input.split(EOL).map { toGame(it) }.sumOf { toPower(it) }

private fun toPower(game: Game): Int = game.reveled.foldRight(Cube.entries.associateWith { 0 }) { draw, acc ->
    acc.mapValues { (key, value) ->
        max(value, draw[key] ?: 0)
    }
}.values.fold(1) { acc, i -> acc * i }


fun main(args: Array<String>) {
    println(cubeConundrum("day02.txt".asResource()))
    println(cubeConundrum2("day02.txt".asResource()))
}
