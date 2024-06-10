package com.c2v4.advent2023


private data class Part(val x: Int, val m: Int, val a: Int, val s: Int)

private val getters = mapOf('x' to Part::x, 'm' to Part::m, 'a' to Part::a, 's' to Part::s)
private val conditions = mapOf('<' to { a: Int, b: Int -> a < b }, '>' to { a: Int, b: Int -> a > b })

fun aplenty(input: String) = input.split(PARAGRAPH).let {
    val (rules, parts) = it
    val ruleMap: Map<String, List<Pair<(Part) -> Boolean, String>>> =
        rules.split(EOL).map { it.dropLast(1).split("{") }.associate {
            val ruleDefinitions = it[1].split(",")
            val executableRules = ruleDefinitions.dropLast(1).map { definition ->
                { p: Part ->
                    getters[definition.first()]?.invoke(p)?.let { a ->
                        conditions[definition[1]]?.invoke(a, definition.drop(2).substringBefore(':').toInt())
                            ?: throw IllegalArgumentException()
                    } ?: throw IllegalArgumentException()
                } to definition.substringAfter(':')
            }
            val defaultRule: Pair<(Part) -> Boolean, String> = { _: Part -> true } to ruleDefinitions.last()
            it[0] to (executableRules + defaultRule)
        }
    parts.split(EOL)
        .map { parsePart(it) }
        .filter { part -> processPart(ruleMap, part, "in") }
        .sumOf { it.x + it.m + it.a + it.s }
}

private tailrec fun processPart(
    ruleMap: Map<String, List<Pair<(Part) -> Boolean, String>>>, part: Part, s: String
): Boolean {
    if (s == "A") {
        return true
    }
    if (s == "R") {
        return false
    }
    return processPart(ruleMap, part, ruleMap[s]!!.first { it.first(part) }.second)
}

private fun parsePart(line: String): Part {
    val pattern = "\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}".toRegex()
    val matchResult = pattern.matchEntire(line) ?: throw IllegalArgumentException("Line does not match pattern: $line")
    val (x, m, a, s) = matchResult.groupValues.drop(1).map { it.toInt() }
    return Part(x, m, a, s)
}

private fun findRule(ruleMap: Map<Int, String>, rule: String): String = if (rule.contains("\"")) {
    rule.replace("\"", "")
} else {
    rule.split(" | ").map {
        it.split(" ").map { it.toInt() }.joinToString("") { findRule(ruleMap, ruleMap[it]!!) }
    }.joinToString("|", "(", ")")
}

typealias PartRange = Map<Char, IntRange>

private sealed class Condition(val field: Char, val value: Int) {
    fun splitPartRange(partRange: PartRange): Pair<PartRange?, PartRange?> {
        val range = partRange[field]!!
        val newRanges = splitRange(range)
        newRanges.let { (first, second) ->
            return if (first != null && second != null) {
                partRange + (field to first) to partRange + (field to second)
            } else if (first != null) {
                partRange + (field to first) to null
            } else if (second != null) {
                null to partRange + (field to second)
            } else {
                null to null
            }
        }
    }

    abstract fun splitRange(range: IntRange): Pair<IntRange?, IntRange?>

    class LessThan(field: Char, value: Int) : Condition(field, value) {
        override fun splitRange(range: IntRange): Pair<IntRange?, IntRange?> {
            return (range.first..<value).takeIf { !it.isEmpty() } to ((value)..range.last).takeIf { !it.isEmpty() }
        }
    }

    class GreaterThan(field: Char, value: Int) : Condition(field, value) {
        override fun splitRange(range: IntRange): Pair<IntRange?, IntRange?> {
            return ((value + 1)..range.last).takeIf { !it.isEmpty() } to (range.first..value).takeIf { !it.isEmpty() }
        }
    }

}

private data class Rule(val condition: Condition, val next: String)
private data class Workflow(val rules: List<Rule>)

private val startingRange = 1..4000
fun aplenty2(input: String) =
    input.split(PARAGRAPH)
        .let {
            val (rules, _) = it
            val ruleMap =
                rules.split(EOL).map { it.dropLast(1).split("{") }.associate {
                    val ruleDefinitions = it[1].split(",")
                    val conditions = ruleDefinitions.dropLast(1).map { definition ->
                        val field = definition.first()
                        val value = definition.drop(2).substringBefore(':').toInt()
                        val condition = when (definition[1]) {
                            '<' -> Condition.LessThan(field, value)
                            '>' -> Condition.GreaterThan(field, value)
                            else -> throw IllegalArgumentException()
                        }
                        Rule(condition, definition.drop(2).substringAfter(':'))
                    }
                    it[0] to Workflow(conditions + Rule(Condition.GreaterThan('x', 0), ruleDefinitions.last()))
                }
            findMatching(
                ruleMap,
                mapOf('x' to startingRange, 'a' to startingRange, 'm' to startingRange, 's' to startingRange),
                "in"
            )
        }
        .sumOf {
            it['x']!!.count().toLong() * it['a']!!.count().toLong() * it['m']!!.count()
                .toLong() * it['s']!!.count().toLong()
        }


private fun findMatching(ruleMap: Map<String, Workflow>, partRange: PartRange, s: String): List<PartRange> {
    if (s == "A") {
        return listOf(partRange)
    }
    if (s == "R") {
        return emptyList()
    }
    val workflow = ruleMap[s]!!
    var currentPartRange = partRange
    val toReturn = mutableListOf<PartRange>()
    for (rule in workflow.rules) {
        val (newPartRange, newPartRange2) = rule.condition.splitPartRange(currentPartRange)
        if (newPartRange != null) {
            val matching = findMatching(ruleMap, newPartRange, rule.next)
            if (matching.isNotEmpty()) {
                toReturn.addAll(matching)
            }
        }
        if (newPartRange2 != null) {
            currentPartRange = newPartRange2
        } else {
            break
        }
    }
    return toReturn
}


fun main(args: Array<String>) {
    println(aplenty("day19.txt".asResource()))
    println(aplenty2("day19.txt".asResource()))
}
