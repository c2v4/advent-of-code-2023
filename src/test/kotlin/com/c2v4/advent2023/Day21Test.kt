package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day21Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            step("...........\n" +
                    ".....###.#.\n" +
                    ".###.##..#.\n" +
                    "..#.#...#..\n" +
                    "....#.#....\n" +
                    ".##..S####.\n" +
                    ".##..#...#.\n" +
                    ".......##..\n" +
                    ".##.#.####.\n" +
                    ".##..##.##.\n" +
                    "...........",6)
        ).isEqualTo(16)
    }



    @Test
    fun test2() {
        Assertions.assertThat(
            step2("...........\n" +
                    ".....###.#.\n" +
                    ".###.##..#.\n" +
                    "..#.#...#..\n" +
                    "....#.#....\n" +
                    ".##..S####.\n" +
                    ".##..#...#.\n" +
                    ".......##..\n" +
                    ".##.#.####.\n" +
                    ".##..##.##.\n" +
                    "...........")
        ).isEqualTo(167409079868000L)
    }

}
