package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day13Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            incidence(
                "#.##..##.\n" +
                        "..#.##.#.\n" +
                        "##......#\n" +
                        "##......#\n" +
                        "..#.##.#.\n" +
                        "..##..##.\n" +
                        "#.#.##.#.\n" +
                        "\n" +
                        "#...##..#\n" +
                        "#....#..#\n" +
                        "..##..###\n" +
                        "#####.##.\n" +
                        "#####.##.\n" +
                        "..##..###\n" +
                        "#....#..#"
            )
        ).isEqualTo(405)
    }

    @Test
    fun test2() {
        Assertions.assertThat(
            incidence(
                "#.##..##.\n" +
                        "..#.##.#.\n" +
                        "##......#\n" +
                        "##......#\n" +
                        "..#.##.#.\n" +
                        "..##..##.\n" +
                        "#.#.##.#.\n" +
                        "\n" +
                        "#...##..#\n" +
                        "#....#..#\n" +
                        "..##..###\n" +
                        "#####.##.\n" +
                        "#####.##.\n" +
                        "..##..###\n" +
                        "#....#..#", true
            )
        ).isEqualTo(400)
    }


}
