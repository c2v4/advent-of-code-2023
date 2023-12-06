package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day06Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            waitForIt("Time:      7  15   30\n" +
                    "Distance:  9  40  200")
        ).isEqualTo(288)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            waitForIt2("Time:      7  15   30\n" +
                    "Distance:  9  40  200")
        ).isEqualTo(71503)
    }

}
