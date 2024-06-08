package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day16Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            lava(".|...\\....\n" +
                    "|.-.\\.....\n" +
                    ".....|-...\n" +
                    "........|.\n" +
                    "..........\n" +
                    ".........\\\n" +
                    "..../.\\\\..\n" +
                    ".-.-/..|..\n" +
                    ".|....-|.\\\n" +
                    "..//.|....")
        ).isEqualTo(46)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            lava2("")
        ).isEqualTo(0)
    }

}
