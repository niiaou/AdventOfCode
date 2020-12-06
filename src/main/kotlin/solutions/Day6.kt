package solutions

import utils.InputReader

// https://adventofcode.com/2020/day/6

fun main() {
    val input = InputReader.getInputText(6)
    Day6(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day6(data: String) {
    private val groupsAnswers = data.split("\\r\\n\\r\\n".toRegex())
    private val alphabets = CharArray(26) { (it + 97).toChar() }.joinToString("")
    private fun String.countUniqueChar(): Int = this.toCharArray().distinct().size

    fun part1(): Int = groupsAnswers.map {
        it.replace("\\r\\n".toRegex(), "").countUniqueChar()
    }.sum()

    private fun String.charSet() = split("").toSet() - ""

    fun part2(): Int {
        return groupsAnswers.map {
            it.lines().fold(alphabets.charSet()) { acc: Set<String>, s: String ->
                acc.intersect(s.charSet()) }.size
        }.sum()
    }

}