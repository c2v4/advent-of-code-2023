package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day14Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            parabolic("O....#....\n" +
                    "O.OO#....#\n" +
                    ".....##...\n" +
                    "OO.#O....O\n" +
                    ".O.....O#.\n" +
                    "O.#..O.#.#\n" +
                    "..O..#O..O\n" +
                    ".......O..\n" +
                    "#....###..\n" +
                    "#OO..#....")
        ).isEqualTo(136)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            parabolic2("O....#....\n" +
                    "O.OO#....#\n" +
                    ".....##...\n" +
                    "OO.#O....O\n" +
                    ".O.....O#.\n" +
                    "O.#..O.#.#\n" +
                    "..O..#O..O\n" +
                    ".......O..\n" +
                    "#....###..\n" +
                    "#OO..#....")
        ).isEqualTo(64)
    }

}
