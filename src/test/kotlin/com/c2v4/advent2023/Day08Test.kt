package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day08Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            haunted("RL\n" +
                    "\n" +
                    "AAA = (BBB, CCC)\n" +
                    "BBB = (DDD, EEE)\n" +
                    "CCC = (ZZZ, GGG)\n" +
                    "DDD = (DDD, DDD)\n" +
                    "EEE = (EEE, EEE)\n" +
                    "GGG = (GGG, GGG)\n" +
                    "ZZZ = (ZZZ, ZZZ)")
        ).isEqualTo(2)
    }
    @Test
    fun test1() {
        Assertions.assertThat(
            haunted("LLR\n" +
                    "\n" +
                    "AAA = (BBB, BBB)\n" +
                    "BBB = (AAA, ZZZ)\n" +
                    "ZZZ = (ZZZ, ZZZ)")
        ).isEqualTo(6)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            haunted2("LR\n" +
                    "\n" +
                    "11A = (11B, XXX)\n" +
                    "11B = (XXX, 11Z)\n" +
                    "11Z = (11B, XXX)\n" +
                    "22A = (22B, XXX)\n" +
                    "22B = (22C, 22C)\n" +
                    "22C = (22Z, 22Z)\n" +
                    "22Z = (22B, 22B)\n" +
                    "XXX = (XXX, XXX)")
        ).isEqualTo(6)
    }

}
