import utils.InputReader
import java.lang.System.lineSeparator

// https://adventofcode.com/2020/day/4

fun main() {
    val input = InputReader.getInputText(4)
    Day4(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day4(data: String) {

    companion object {
        private val requiredFields = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    }

    private val passports = data.split("\\r\\n\\r\\n".toRegex()).map { passport ->
        passport.replace("\\r\\n".toRegex(), " ").split(" ").map {
            val (k, v) = it.split(":")
            k to v
        }.toMap()
    }

    private fun isValid(passport: Map<String, String>): Boolean =
        requiredFields.all { it in passport }

    fun part1(): Int = passports.count {
        isValid(it)
    }

    private val fieldValidators = mapOf<String, (String?) -> Boolean>(
        "byr" to { it?.toInt() in 1920..2002 },
        "iyr" to { it?.toInt() in 2010..2020 },
        "eyr" to { it?.toInt() in 2020..2030 },
        "hgt" to {
            it?.let {
                when (it.takeLast(2)) {
                    "cm" -> it.dropLast(2).toInt() in 150..193
                    "in" -> it.dropLast(2).toInt() in 59..76
                    else -> false
                }
            } ?: false
        },
        "hcl" to { it?.matches(Regex("^#[0-9a-f]{6}$")) ?: false },
        "ecl" to { it?.matches(Regex("^amb|blu|brn|gry|grn|hzl|oth$")) ?: false },
        "pid" to { it?.matches(Regex("^\\d{9}$")) ?: false }
    )

    fun part2(): Int {
        return passports.count {
            fieldValidators.entries.all { (key, value) -> value(it[key]) }
        }
    }
}