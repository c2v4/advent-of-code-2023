package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day03Test : AnnotationSpec() {
    @Test
    fun test() {
        Assertions.assertThat(
            gearRatio(
                "467..114..\n" +
                        "...*......\n" +
                        "..35..633.\n" +
                        "......#...\n" +
                        "617*......\n" +
                        ".....+.58.\n" +
                        "..592.....\n" +
                        "......755.\n" +
                        "...\$.*....\n" +
                        ".664.598.."
            )
        ).isEqualTo(4361)
    }

    @Test
    fun test2() {
        Assertions.assertThat(
            gearRatio2(
                "467..114..\n" +
                        "...*......\n" +
                        "..35..633.\n" +
                        "......#...\n" +
                        "617*......\n" +
                        ".....+.58.\n" +
                        "..592.....\n" +
                        "......755.\n" +
                        "...\$.*....\n" +
                        ".664.598.."
            )
        ).isEqualTo(467835)
    }

}
