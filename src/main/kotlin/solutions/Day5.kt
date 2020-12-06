package solutions

import utils.InputReader

// https://adventofcode.com/2020/day/5

fun main() {
    val input = InputReader.getInputLines(5)
    Day5(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day5(data: List<String>) {

    data class BoardingPass(
        val row: Int,
        val column: Int
    ) {
        fun seatId(): Int = row * 8 + column

        companion object {
            fun fromString(input: String): BoardingPass {
                val bp = input.replace("[FL]".toRegex(), "0")
                    .replace("[BR]".toRegex(), "1")
                return BoardingPass(
                    bp.take(7).toInt(2),
                    bp.drop(7).toInt(2),
                )
            }
        }
    }

    private val seats = data.map {
        BoardingPass.fromString(it)
    }

    fun part1(): Int = seats.maxOf { it.seatId() }

    fun part2(): Int {
        seats.map { it.seatId() }.sorted().zipWithNext { a, b ->
            if (a < b - 1) return a + 1
        }
        return 0
    }
}