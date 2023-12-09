package com.c2v4.advent2023

fun mirage(input: String) = input.split(EOL).sumOf { line ->
    val seq = line.split(Regex("""\s+""")).map { it.toLong() }
    findNextElementInSequence(listOf(seq), ::reduceRight)
}

private fun findNextElementInSequence(seqs: List<List<Long>>, reducer: (List<List<Long>>) -> Long): Long =
    if (seqs.last().all { it == 0L }) {
        reducer(seqs)
    } else {
        findNextElementInSequence(seqs.plusElement(seqs.last().windowed(2, 1, false).map { it[1] - it[0] }), reducer)
    }


private fun reduceRight(seqs: List<List<Long>>) = seqs.foldRight(0L) { list, acc -> acc + list.last() }

private fun reduceLeft(seqs: List<List<Long>>) = seqs.foldRight(0L) { list, acc -> list.first() - acc }


fun mirage2(input: String) = input.split(EOL).sumOf { line ->
    val seq = line.split(Regex("""\s+""")).map { it.toLong() }
    findNextElementInSequence(listOf(seq), ::reduceLeft)
}

fun main(args: Array<String>) {
    println(mirage("day09.txt".asResource()))
    println(mirage2("day09.txt".asResource()))
}
