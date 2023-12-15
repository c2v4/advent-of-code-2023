package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day15Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            lens("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7")
        ).isEqualTo(1320)
    }


    @Test
    fun test2() {
        Assertions.assertThat(
            lens2("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7")
        ).isEqualTo(145)
    }

}
