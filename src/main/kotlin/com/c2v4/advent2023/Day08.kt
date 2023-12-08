package com.c2v4.advent2023

import java.math.BigInteger

fun haunted(input: String) = input.split(PARAGRAPH).let {
    val (instructions, map) = getInstructionsAndMap(it)
    findLast(instructions, map)
}

private tailrec fun findLast(
    instruction: String,
    map: Map<String, Pair<String, String>>,
    step: Long = 0,
    current: String = "AAA",
    ghost: Boolean = false
): Long {
    if (current == "ZZZ" || (ghost && current.endsWith("Z"))) return step
    val (left, right) = map[current]!!
    val currentInstruction = instruction[(step % instruction.length).toInt()]
    return when (currentInstruction) {
        'R' -> findLast(instruction, map, step + 1, right, ghost)
        'L' -> findLast(instruction, map, step + 1, left, ghost)
        else -> throw IllegalArgumentException()
    }
}

fun haunted2(input: String) = input.split(PARAGRAPH).let { paragraph ->
    val (instructions, map) = getInstructionsAndMap(paragraph)
    map.keys.filter { it.endsWith("A") }.map { findLast(instructions, map, 0, it, true) }
}.map { BigInteger.valueOf(it) }.reduce { a, b -> lcm(a, b) }.toLong()

private fun getInstructionsAndMap(paragraph: List<String>): Pair<String, Map<String, Pair<String, String>>> {
    val instructions = paragraph[0]
    val map = paragraph[1].split(EOL).map { it.split(" = ") }.map {
        val split = it[1].trim().drop(1).dropLast(1).split(", ")
        it[0] to (split[0] to split[1])
    }.toMap()
    return Pair(instructions, map)
}

fun lcm(a: BigInteger, b: BigInteger): BigInteger {
    val gcd = a.gcd(b)
    val absProduct = a * b
    return absProduct / gcd
}

fun main(args: Array<String>) {
    println(haunted("day08.txt".asResource()))
    println(haunted2("day08.txt".asResource()))
}
