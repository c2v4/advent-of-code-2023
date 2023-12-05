package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day01Test : AnnotationSpec() {
    @Test
    fun test() {
        Assertions.assertThat(
            trebuchet(
                "1abc2\n" +
                        "pqr3stu8vwx\n" +
                        "a1b2c3d4e5f\n" +
                        "treb7uchet"
            )
        ).isEqualTo(142)
    }

    @Test
    fun testPlus() {
        Assertions.assertThat(
            trebuchet2(
                "two1nine\n" +
                        "eighthree\n" +
                        "abcone2threexyz\n" +
                        "xtwone3four\n" +
                        "4nineeightseven2\n" +
                        "zoneight234\n" +
                        "76"
            )
        ).isEqualTo(281)
    }
}
