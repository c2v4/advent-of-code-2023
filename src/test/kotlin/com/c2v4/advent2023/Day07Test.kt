package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day07Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            camel("32T3K 765\n" +
                    "T55J5 684\n" +
                    "KK677 28\n" +
                    "KTJJT 220\n" +
                    "QQQJA 483")
        ).isEqualTo(6440)
    }
    @Test
    fun test1() {
        Assertions.assertThat(
            camel("AAAAA 2\n" +
                    "22222 3\n" +
                    "AAAAK 5\n" +
                    "22223 7\n" +
                    "AAAKK 11\n" +
                    "22233 13\n" +
                    "AAAKQ 17\n" +
                    "22234 19\n" +
                    "AAKKQ 23\n" +
                    "22334 29\n" +
                    "AAKQJ 31\n" +
                    "22345 37\n" +
                    "AKQJT 41\n" +
                    "23456 43")
        ).isEqualTo(1343)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            camel2("32T3K 765\n" +
                    "T55J5 684\n" +
                    "KK677 28\n" +
                    "KTJJT 220\n" +
                    "QQQJA 483")
        ).isEqualTo(5905)
    }

}
