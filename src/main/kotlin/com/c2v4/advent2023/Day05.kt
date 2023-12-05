package com.c2v4.advent2023

import kotlin.math.max
import kotlin.math.min

fun fertilizer(input: String) = input.split(PARAGRAPH).let {
    val seeds = it[0].split(":").last().trim().split(Regex("\\s+")).map { it.toLong() }
    val maps = getMaps(it)
    seeds.minOfOrNull { seed ->
        maps.fold(seed) { acc, map ->
            map.filter { (range, start) -> range.contains(acc) }
                .map { (range, start) -> start + acc - range.first }
                .firstOrNull() ?: acc
        }
    }
}

private fun getMaps(it: List<String>) = it.drop(1).map {
    it.split(EOL).drop(1).map { line ->
        val split = line.split(Regex("\\s+"))
        (split[1].toLong() until (split[1].toLong() + split[2].toLong())) to split[0].toLong()
    }
}

fun intersect(given: LongRange, over: LongRange): Pair<LongRange?, List<LongRange>> {
    if (given.first > over.last || given.last < over.first) return null to listOf(given)
    return if (given.first >= over.first) {
        if (over.last >= given.last) {
            given to emptyList()
        } else {
            LongRange(given.first, over.last) to listOf(LongRange(over.last + 1, given.last))
        }
    } else {
        if (over.last >= given.last) {
            LongRange(over.first, given.last) to listOf(LongRange(given.first, over.first - 1))
        } else {
            LongRange(over.first, over.last) to listOf(
                LongRange(given.first, over.first - 1), LongRange(over.last + 1, given.last)
            )
        }
    }
}

fun fertilizer2(input: String) = input.split(PARAGRAPH).let { paragraphs ->
    val seeds = paragraphs[0].split(":")
        .last()
        .trim()
        .split(Regex("\\s+"))
        .map { it.toLong() }
        .windowed(2, 2)
        .map { LongRange(it[0], it[0] + it[1] - 1) }
    val maps = getMaps(paragraphs)
    seeds.flatMap { seed ->
        maps.fold(setOf(seed)) { acc, map ->
            val fold = map.fold(emptySet<LongRange>() to acc) { (processed, toProcess), (range, start) ->
                val (intersect, rest) = toProcess.map { intersect(it, range) }.unzip()
                val newProcessed = processed + intersect.filterNotNull().map {
                    LongRange(
                        it.first + start - range.first, it.last + start - range.first
                    )
                }
                val newToProcess = rest.flatten().toSet()
                newProcessed to newToProcess
            }
            fold.first + fold.second
        }
    }.minOfOrNull { it.first }
}

fun main(args: Array<String>) {
    println(fertilizer("day05.txt".asResource()))
    println(fertilizer2("day05.txt".asResource()))
}
