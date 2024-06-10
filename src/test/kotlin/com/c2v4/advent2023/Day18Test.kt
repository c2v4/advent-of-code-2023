package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day18Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            lavaduct("R 6 (#70c710)\n" +
                    "D 5 (#0dc571)\n" +
                    "L 2 (#5713f0)\n" +
                    "D 2 (#d2c081)\n" +
                    "R 2 (#59c680)\n" +
                    "D 2 (#411b91)\n" +
                    "L 5 (#8ceee2)\n" +
                    "U 2 (#caa173)\n" +
                    "L 1 (#1b58a2)\n" +
                    "U 2 (#caa171)\n" +
                    "R 2 (#7807d2)\n" +
                    "U 3 (#a77fa3)\n" +
                    "L 2 (#015232)\n" +
                    "U 2 (#7a21e3)")
        ).isEqualTo(62)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            lavaduct2("R 6 (#70c710)\n" +
                    "D 5 (#0dc571)\n" +
                    "L 2 (#5713f0)\n" +
                    "D 2 (#d2c081)\n" +
                    "R 2 (#59c680)\n" +
                    "D 2 (#411b91)\n" +
                    "L 5 (#8ceee2)\n" +
                    "U 2 (#caa173)\n" +
                    "L 1 (#1b58a2)\n" +
                    "U 2 (#caa171)\n" +
                    "R 2 (#7807d2)\n" +
                    "U 3 (#a77fa3)\n" +
                    "L 2 (#015232)\n" +
                    "U 2 (#7a21e3)")
        ).isEqualTo(952408144115L)
    }

}
