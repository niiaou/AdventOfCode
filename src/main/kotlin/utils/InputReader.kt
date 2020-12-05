package utils

import java.io.File

object InputReader {
    private fun getInputFileName(day: Int): String =
        "day${day}.txt"

    private fun getInputFile(day: Int): File =
        File(javaClass.classLoader.getResource(getInputFileName(day)).toURI())

    fun getInputText(day: Int): String =
        getInputFile(day).readText()

    fun getInputLines(day: Int): List<String> =
        getInputFile(day).readLines()
}