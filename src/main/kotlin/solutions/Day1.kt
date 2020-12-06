package solutions

import utils.InputReader

// https://adventofcode.com/2020/day/1

fun main() {
    val input = InputReader.getInputLines(1).map {
        it.toInt()
    }
    Day1().also {
        println(it.part1(input, 2020))
        println(it.part2(input, 2020))
    }
}

class Day1 {
    fun part1(data: List<Int>, sum: Int): Int {
        for (i in data.indices) {
            val value = data[i]
            val possibleAnswer = sum - value
            if (data.contains(possibleAnswer)) {
                return possibleAnswer * value
            }
        }
        return 0
    }

    fun part2(data: List<Int>, sum: Int): Int {
        for (i in data.indices) {
            val value = data[i]
            val remains = sum - value
            val filteredData = data.filter {
                it <= remains
            }
            val answer = part1(filteredData, remains)
            if (answer != 0) return answer * value
        }
        return 0
    }
}

