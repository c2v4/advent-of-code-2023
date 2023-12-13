package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day12Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            folds(
                "???.### 1,1,3\n" +
                        ".??..??...?##. 1,1,3\n" +
                        "?#?#?#?#?#?#?#? 1,3,1,6\n" +
                        "????.#...#... 4,1,1\n" +
                        "????.######..#####. 1,6,5\n" +
                        "?###???????? 3,2,1", 5
            )
        ).isEqualTo(21)
    }



}
