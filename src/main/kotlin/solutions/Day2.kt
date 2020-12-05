package solutions

import utils.InputReader
import java.lang.IllegalArgumentException

// https://adventofcode.com/2020/day/2

fun main() {
    val input = InputReader.getInputLines(2)
    Day2(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day2(inputFromFile: List<String>) {
    private val passwordPolicyRegex = Regex("(\\d+)-(\\d+) ([a-z]:) ([a-z]+)")
    private val data = inputFromFile.map {
        parseLineInput(it)
    }

    data class Policy(
        val min: Int,
        val max: Int,
        val char: Char
    )

    private fun parseLineInput(line: String): Pair<String, Policy> {
        val matches = passwordPolicyRegex.find(line) ?: throw IllegalArgumentException("invalid line at $line")
        val (min, max, char, password) = matches.destructured

        return Pair(password, Policy(min.toInt(), max.toInt(), char.first()))
    }

    fun part1(): Int =
        data.count {
            it.first.count { c -> c == it.second.char } in it.second.min..it.second.max
        }

    fun part2(): Int =
        data.count { (password, policy) ->
            val firstOccurrence = password[policy.min - 1] == policy.char
            val secondOccurrence = password[policy.max - 1] == policy.char
            firstOccurrence xor secondOccurrence
        }
}