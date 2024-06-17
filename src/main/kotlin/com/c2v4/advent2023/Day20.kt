package com.c2v4.advent2023

import arrow.core.unzip
import com.c2v4.advent2023.Level.*
import com.google.common.graph.GraphBuilder
import com.google.common.graph.MutableGraph
import java.util.stream.IntStream

fun pulse(input: String) = input.split(EOL).let {

    val (modules, _) = it.unzip {
        it.split(" -> ").let { it.first() to it.last() }
    }
    val moduleMap = modules.associate {
        val name = it.drop(1)
        when (it.first()) {
            '%' -> name to Module.FlipFlop(name)
            '&' -> name to Module.Conjunction(name)
            else -> "broadcaster" to Module.Broadcaster()
        }
    }
    val graph = GraphBuilder.directed().build<String>()
    it.forEach {
        val (fromPrototype, to) = it.split(" -> ")
        val from = when (fromPrototype.first()) {
            '%' -> fromPrototype.drop(1)
            '&' -> fromPrototype.drop(1)
            else -> "broadcaster"
        }
        to.split(", ").forEach {
            graph.putEdge(from, it)
        }
    }
    (1..1000).map { process(listOf(Pulse(LOW, "button", "broadcaster")), moduleMap, graph) }.let {
        it.unzip().let { it.first.sum() * it.second.sum() }
    }

}

private tailrec fun process(
    toProcess: List<Pulse>, moduleMap: Map<String, Module>, graph: MutableGraph<String>,
    soFar: Pair<Int, Int> = 0 to 0
): Pair<Int, Int> {
    if (toProcess.isEmpty()) {
        return soFar
    }
    val current = toProcess.first()
    val newSoFar = when (current.level) {
        LOW -> soFar.first + 1 to soFar.second
        HIGH -> soFar.first to soFar.second + 1
    }
    val module = moduleMap[current.to]
    val toAddToProcessing = module?.process(current, graph) ?: emptyList()
    return process(
        toProcess.drop(1) + toAddToProcessing,
        moduleMap,
        graph,
        newSoFar
    )
}

private data class Pulse(val level: Level, val from: String, val to: String) {
    override fun toString(): String {
        return "$from -$level> $to"
    }
}

private enum class Level {
    LOW, HIGH;
}

private sealed class Module(val name: String) {

    abstract fun process(pulse: Pulse, graph: MutableGraph<String>): List<Pulse>

    class Broadcaster() : Module("broadcaster") {
        override fun process(pulse: Pulse, graph: MutableGraph<String>): List<Pulse> {
            return graph.successors(name).map {
                Pulse(pulse.level, name, it)
            }
        }
    }

    class FlipFlop(name: String) : Module(name) {
        private enum class State {
            ON, OFF;

            fun other() = if (this == ON) OFF else ON
            fun mapToPulse() = if (this == ON) HIGH else LOW
        }

        private var state = State.OFF
        override fun process(pulse: Pulse, graph: MutableGraph<String>): List<Pulse> =
            when (pulse.level) {
                LOW -> {
                    state = state.other()
                    val level = state.mapToPulse()
                    graph.successors(name).map {
                        Pulse(level, name, it)
                    }
                }

                HIGH -> {
                    emptyList()
                }
            }

    }

    class Conjunction(name: String) : Module(name) {

        val inputs = mutableMapOf<String, Level>()
        override fun process(pulse: Pulse, graph: MutableGraph<String>): List<Pulse> {
            inputs[pulse.from] = pulse.level
            val outPulse = if (graph.predecessors(name).all {
                    inputs.getOrDefault(it, LOW) == HIGH
                }) LOW else HIGH
            return graph.successors(name).map {
                Pulse(outPulse, name, it)
            }
        }
    }

}

fun pulse2(input: String) = input.split(EOL).let {

    val (modules, _) = it.unzip {
        it.split(" -> ").let { it.first() to it.last() }
    }
    val moduleMap = modules.associate {
        val name = it.drop(1)
        when (it.first()) {
            '%' -> name to Module.FlipFlop(name)
            '&' -> name to Module.Conjunction(name)
            else -> "broadcaster" to Module.Broadcaster()
        }
    }
    val graph = GraphBuilder.directed().build<String>()
    it.forEach {
        val (fromPrototype, to) = it.split(" -> ")
        val from = when (fromPrototype.first()) {
            '%' -> fromPrototype.drop(1)
            '&' -> fromPrototype.drop(1)
            else -> "broadcaster"
        }
        to.split(", ").forEach {
            graph.putEdge(from, it)
        }
    }
    var lcm = setOf("rx")
    while (true) {
        val predecessors = lcm.flatMap { graph.predecessors(it) }
        if (predecessors.all { moduleMap[it] is Module.Conjunction } && lcm.size>1) {
            break
        }
        lcm = predecessors.toSet()
    }
    lcm.map {
        val moduleMap = modules.associate {
            val name = it.drop(1)
            when (it.first()) {
                '%' -> name to Module.FlipFlop(name)
                '&' -> name to Module.Conjunction(name)
                else -> "broadcaster" to Module.Broadcaster()
            }
        }
        findCycle(it, moduleMap, graph)
    }.fold(1L) { acc, l -> acc * (l+1) }

}

private fun findCycle(
    module: String,
    moduleMap: Map<String, Module>,
    graph: MutableGraph<String>
) = generateSequence(1L) { it + 1 }.takeWhile {
    process2(listOf(Pulse(LOW, "button", "broadcaster")), moduleMap, graph, module, LOW).not()
}.last()

private tailrec fun process2(
    toProcess: List<Pulse>, moduleMap: Map<String, Module>, graph: MutableGraph<String>,
    toFind: String, toFindLevel: Level,
    soFar: Pair<Int, Int> = 0 to 0
): Boolean {
    if (toProcess.isEmpty()) {
        return false
    }
    val current = toProcess.first()
    if (current.to == toFind && current.level == toFindLevel) {
        return true
    }
    val newSoFar = when (current.level) {
        LOW -> soFar.first + 1 to soFar.second
        HIGH -> soFar.first to soFar.second + 1
    }
    val module = moduleMap[current.to]
    val toAddToProcessing = module?.process(current, graph) ?: emptyList()
    return process2(
        toProcess.drop(1) + toAddToProcessing,
        moduleMap,
        graph,
        toFind,
        toFindLevel,
        newSoFar
    )
}


fun main(args: Array<String>) {
    println(pulse("day20.txt".asResource()))
    println(pulse2("day20.txt".asResource()))
}
