package com.c2v4.advent2023

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class Day10Test : AnnotationSpec() {

    @Test
    fun test() {
        Assertions.assertThat(
            pipe(
                ".....\n" +
                        ".S-7.\n" +
                        ".|.|.\n" +
                        ".L-J.\n" +
                        "....."
            )
        ).isEqualTo(4)
    }

    @Test
    fun test1() {
        Assertions.assertThat(
            pipe(
                "..F7.\n" +
                        ".FJ|.\n" +
                        "SJ.L7\n" +
                        "|F--J\n" +
                        "LJ...\n"
            )
        ).isEqualTo(8)
    }


    @Test
    fun test22() {
        Assertions.assertThat(
            pipe2(
                "...........\n" +
                        ".S-------7.\n" +
                        ".|F-----7|.\n" +
                        ".||.....||.\n" +
                        ".||.....||.\n" +
                        ".|L-7.F-J|.\n" +
                        ".|..|.|..|.\n" +
                        ".L--J.L--J.\n" +
                        "..........."
            )
        ).isEqualTo(4)
    }

    @Test
    fun test23() {
        Assertions.assertThat(
            pipe2(
                "..........\n" +
                        ".S------7.\n" +
                        ".|F----7|.\n" +
                        ".||OOOO||.\n" +
                        ".||OOOO||.\n" +
                        ".|L-7F-J|.\n" +
                        ".|II||II|.\n" +
                        ".L--JL--J.\n" +
                        ".........."
            )
        ).isEqualTo(4)
    }

    @Test
    fun test24() {
        Assertions.assertThat(
            pipe2(
                ".F----7F7F7F7F-7....\n" +
                        ".|F--7||||||||FJ....\n" +
                        ".||.FJ||||||||L7....\n" +
                        "FJL7L7LJLJ||LJ.L-7..\n" +
                        "L--J.L7...LJS7F-7L7.\n" +
                        "....F-J..F7FJ|L7L7L7\n" +
                        "....L7.F7||L7|.L7L7|\n" +
                        ".....|FJLJ|FJ|F7|.LJ\n" +
                        "....FJL-7.||.||||...\n" +
                        "....L---J.LJ.LJLJ..."
            )
        ).isEqualTo(8)
    }
    @Test
    fun test25() {
        Assertions.assertThat(
            pipe2(
                "FF7FSF7F7F7F7F7F---7\n" +
                        "L|LJ||||||||||||F--J\n" +
                        "FL-7LJLJ||||||LJL-77\n" +
                        "F--JF--7||LJLJ7F7FJ-\n" +
                        "L---JF-JLJ.||-FJLJJ7\n" +
                        "|F|F-JF---7F7-L7L|7|\n" +
                        "|FFJF7L7F-JF7|JL---7\n" +
                        "7-L-JL7||F7|L7F-7F7|\n" +
                        "L.L7LFJ|||||FJL7||LJ\n" +
                        "L7JLJL-JLJLJL--JLJ.L"
            )
        ).isEqualTo(10)
    }

}
