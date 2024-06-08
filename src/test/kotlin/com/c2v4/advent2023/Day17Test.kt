package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day17Test : AnnotationSpec() {

    @Test
    @Ignore
    fun test() {
        Assertions.assertThat(
            clumsy("2413432311323\n" +
                    "3215453535623\n" +
                    "3255245654254\n" +
                    "3446585845452\n" +
                    "4546657867536\n" +
                    "1438598798454\n" +
                    "4457876987766\n" +
                    "3637877979653\n" +
                    "4654967986887\n" +
                    "4564679986453\n" +
                    "1224686865563\n" +
                    "2546548887735\n" +
                    "4322674655533")
        ).isEqualTo(102)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            clumsy2("2413432311323\n" +
                    "3215453535623\n" +
                    "3255245654254\n" +
                    "3446585845452\n" +
                    "4546657867536\n" +
                    "1438598798454\n" +
                    "4457876987766\n" +
                    "3637877979653\n" +
                    "4654967986887\n" +
                    "4564679986453\n" +
                    "1224686865563\n" +
                    "2546548887735\n" +
                    "4322674655533")
        ).isEqualTo(94)
    }

    @Test
    fun test3() {
        Assertions.assertThat(
            clumsy2("111111111111\n" +
                    "999999999991\n" +
                    "999999999991\n" +
                    "999999999991\n" +
                    "999999999991")
        ).isEqualTo(71)
    }
}
