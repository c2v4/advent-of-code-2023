package com.c2v4.advent2023

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


fun String.asResource() = Thread.currentThread().contextClassLoader.getResource(this).readText()

val EOL = Regex("\\r?\\n")

val PARAGRAPH = Regex("\\r?\\n\\r?\\n")
suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}