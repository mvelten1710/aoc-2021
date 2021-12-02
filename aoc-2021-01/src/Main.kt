package main.kotlin
import java.io.File

fun main(args: Array<String>) {

    // Converting the list<string> to list<int>
    val list = File("aoc-2021-01/input.txt")
        .readLines()
        .stream()
        .map {
            it.toInt()
        }.toList()

    val result1 = countIncreasingMeasurements(list = list, acc = 0)

    println("Result for puzzle 1: $result1\n")

    //val tempList = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    val result2 = countIncreasingMeasurements(accumulateWithSlidingWindow(0, list), 0)

    println("Result for puzzle 2: $result2\n")

}

// Part of Puzzle 1 and 2
fun countIncreasingMeasurements(list: List<Int>, acc: Int): Int {
    if (list.size == 1) return acc
    val newList = list.drop(1)
    return if (list[0] < newList[0]) {
        countIncreasingMeasurements(list = newList, acc = acc + 1)
    } else {
        countIncreasingMeasurements(list = newList, acc = acc + 0)
    }
}

// Part of Puzzle 2 (Sliding Window)
fun accumulateWithSlidingWindow(windowIndex: Int, list: List<Int>): List<Int> {
    if(list.size < 3) return listOf()
    val result = list[windowIndex] + list[windowIndex + 1] + list[windowIndex + 2]
    return listOf(result) + accumulateWithSlidingWindow(windowIndex = windowIndex, list = list.drop(1))
}