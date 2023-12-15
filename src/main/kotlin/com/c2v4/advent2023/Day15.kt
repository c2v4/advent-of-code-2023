package com.c2v4.advent2023

fun lens(input: String) = input.split(",").sumOf { hash(it).toLong() }

fun hash(it: String) = it.fold(0) { acc, c ->
    ((acc + c.code) * 17) and 0xFF
}

private val regex = """([a-zA-Z]+)([=-])(\d*)""".toRegex()
fun lens2(input: String) = input.split(",").let {
    it.fold(Array(256) { LinkedHashSet<String>() } to hashMapOf<String, Int>()) { (boxes, dictionary), s ->
        regex.find(s)!!.let {
            val (label, operation, value) = it.destructured
            val hash = hash(label)
            val box = boxes[hash]
            when (operation) {
                "-" -> box.remove(label)
                "=" -> {
                    if (!box.contains(label)) {
                        box.add(label)
                    }
                    dictionary[label] = value.toInt()
                }
                else -> throw IllegalArgumentException()
            }
            boxes to dictionary
        }
    }.let { (boxes, dictionary) ->
        boxes.mapIndexed() { index, set ->
            set.mapIndexed() { i, s ->
                dictionary[s]!! * (index + 1) * (i + 1)
            }.sum()
        }.sum()
    }
}

fun main(args: Array<String>) {
    println(lens("day15.txt".asResource()))
    println(lens2("day15.txt".asResource()))
}
