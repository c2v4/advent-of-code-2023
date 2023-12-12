package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day11Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            cosmic("...#......\n" +
                    ".......#..\n" +
                    "#.........\n" +
                    "..........\n" +
                    "......#...\n" +
                    ".#........\n" +
                    ".........#\n" +
                    "..........\n" +
                    ".......#..\n" +
                    "#...#.....")
        ).isEqualTo(374)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            cosmic("...#......\n" +
                    ".......#..\n" +
                    "#.........\n" +
                    "..........\n" +
                    "......#...\n" +
                    ".#........\n" +
                    ".........#\n" +
                    "..........\n" +
                    ".......#..\n" +
                    "#...#.....",10)
        ).isEqualTo(1030)
    }
    @Test
    fun test3() {
        Assertions.assertThat(
            cosmic("...#......\n" +
                    ".......#..\n" +
                    "#.........\n" +
                    "..........\n" +
                    "......#...\n" +
                    ".#........\n" +
                    ".........#\n" +
                    "..........\n" +
                    ".......#..\n" +
                    "#...#.....",100)
        ).isEqualTo(8410)
    }

}
