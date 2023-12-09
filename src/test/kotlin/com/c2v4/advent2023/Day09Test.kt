package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day09Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            mirage("0 3 6 9 12 15\n" +
                    "1 3 6 10 15 21\n" +
                    "10 13 16 21 30 45")
        ).isEqualTo(114)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            mirage2("0 3 6 9 12 15\n" +
                    "1 3 6 10 15 21\n" +
                    "10 13 16 21 30 45")
        ).isEqualTo(2)
    }

}
