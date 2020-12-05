package solutions

import utils.InputReader
import java.lang.IllegalArgumentException

// https://adventofcode.com/2020/day/3

fun main() {
    val input = InputReader.getInputLines(3)
    Day3(input).also {
        println(it.part1())
        println(it.part2())
    }
}
class Day3(private val data: List<String>) {

    private val width: Int = data.first().length
    private val height: Int = data.size

    private fun checkSlope(posX: Int, posY: Int): Long {
        var coordX = 0
        var coordY = 0
        var trees = 0L

        while (coordY < height) {
            if (data[coordY][coordX].toString() == "#") {
                trees++
            }
            coordX = (coordX + posX) % width
            coordY += posY
        }
        println(trees)
        return trees
    }

    fun part1() : Long =
        checkSlope(3, 1)

    fun part2(): Long =
        listOf(
            1 to 1,
            3 to 1,
            5 to 1,
            7 to 1,
            1 to 2
        ).map {
            checkSlope(it.first, it.second)
        }.reduce { acc, i -> acc * i }
}