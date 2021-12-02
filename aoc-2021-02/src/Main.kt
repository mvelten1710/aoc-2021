package main.kotlin
import java.io.File

fun main(args: Array<String>) {

    val input = File("aoc-2021-02/input.txt")
        .readLines()


    val pos1 = calculatePosition(input)
    val result1 = pos1.first * pos1.second

    println("Result of puzzle 1: $result1")

    val pos2 = calculateNewPosition(input)
    val result2 = pos2.first * pos2.second

    println("Result of puzzle 2: $result2")
}

val regex = """(forward|down|up) ([0-9]*)""".toRegex()

// Pair Values: First => Horizontal, Second => Depth
fun calculatePosition(list: List<String>): Pair<Int, Int> =
    list.map {
        val (command, value) = regex.find(it)?.destructured ?: error("Oh no!")
        when(command) {
            "forward" -> Pair(value.toInt(), 0)
            "up" -> Pair(0, -value.toInt())
            else -> Pair(0, value.toInt())

        }
    }.reduce{ acc, it -> Pair(acc.first + it.first, acc.second + it.second) }

// Triple Values: First => Horizontal, Second => Depth, Third => Aim
fun calculateNewPosition(list: List<String>): Pair<Int, Int> {
    var horizontalValue = 0
    var depth = 0
    var aim = 0

    list.forEach {
        val (command, value) = regex.find(it)?.destructured ?: error("Oh no!")
        when(command) {
            "forward" -> {
                horizontalValue += value.toInt()
                depth += (aim * value.toInt())
            }
            "up" -> aim -= value.toInt()
            else -> aim += value.toInt()
        }
    }

    return Pair(horizontalValue, depth)
}
