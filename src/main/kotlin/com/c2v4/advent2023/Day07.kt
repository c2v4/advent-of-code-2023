package com.c2v4.advent2023

import java.util.Comparator

fun camel(input: String) = input.split(EOL).map { toBet(it) }.sortedWith(
    Comparator.comparing<Bet?, Float?> {
        val i = it.first.getRank()[0]
        if (i == 3 && it.first.getRank()[1] == 2) 3.5f
        else if (i == 2 && it.first.getRank()[1] == 2) 2.5f
        else i.toFloat()
    }.thenComparing(listComparator)
).mapIndexed() { index, (hand, bet) ->
    (index + 1) * bet
}.sum()

val listComparator = compareBy<Bet> { it.first[0] }.thenBy { it.first[1] }
    .thenBy { it.first[2] }
    .thenBy { it.first[3] }
    .thenBy { it.first[4] }

fun toBet(string: String, jValue: Int = 11): Bet {
    val split = string.split(Regex("\\s+"))
    return toHand(split[0], jValue) to split[1].toLong()
}
typealias Bet = Pair<Hand, Long>
typealias Hand = List<Int>

fun Hand.getRank() = this.groupBy { it }.map { it.value.size }.sortedDescending()
fun toHand(s: String, jValue: Int = 11) = s.toCharArray().map {
    when {
        it.isDigit() -> it.digitToInt()
        it == 'T' -> 10
        it == 'J' -> jValue
        it == 'Q' -> 12
        it == 'K' -> 13
        it == 'A' -> 14
        else -> throw IllegalArgumentException()
    }
}

fun Hand.getRankWithoutJ() = this.filter { it != 1 }.groupBy { it }.map { it.value.size }.sortedDescending()

fun camel2(input: String) = input.split(EOL).map { toBet(it, 1) }.sortedWith(
    Comparator.comparing<Bet?, Float?> {
        val i = it.first.getRankWithoutJ().getOrElse(0) { 0 } + it.first.count { it == 1 }
        if (i == 3 && it.first.getRankWithoutJ().getOrElse(1) { 0 } == 2) 3.5f
        else if (i == 2 && it.first.getRankWithoutJ().getOrElse(1) { 0 } == 2) 2.5f
        else i.toFloat()
    }.thenComparing(listComparator)
).mapIndexed() { index, (hand, bet) ->
    (index + 1) * bet
}.sum()

fun main(args: Array<String>) {
    println(camel("day07.txt".asResource()))
    println(camel2("day07.txt".asResource()))
}
