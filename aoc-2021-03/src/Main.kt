import java.io.File

fun main(args: Array<String>) {

    val input = File("aoc-2021-03/input.txt")
        .readLines()

    val result = calculatePowerConsumption(input)
    println("Result of puzzle 1: $result")

    val result2 = calculateLifeSupport(input)
    println("Result of puzzle 2: $result2")


}

fun calculatePowerConsumption(list: List<String>): Int {
    val itemLength = list.size

    var rowIndex = 0
    var columnIndex = 0

    var zeroCount = 0
    var oneCount = 0

    var gammaRate = 0
    var epsilonRate = 0

//    var gammaRateString = ""
//    var epsilonRateString = ""


    // Better Version
//    while (rowIndex < list[0].length) {
//        list.forEach {
//            if (it[columnIndex] == '1') {
//                oneCount++
//            }
//        }
//
//        if (oneCount > (itemLength / 2)) {
//            gammaRateString += "1"
//            epsilonRateString += "0"
//        } else {
//            gammaRateString += "0"
//            epsilonRateString += "1"
//        }
//        oneCount = 0
//        columnIndex++
//        rowIndex++
//    }

    // Worse Version
    while (columnIndex < list[rowIndex].length) {
        while (rowIndex < list.size) {
            if (list[rowIndex][columnIndex] == '0') zeroCount++ else oneCount++
            rowIndex++
        }
        rowIndex = 0
        if(zeroCount < oneCount) gammaRate = gammaRate.or(1.shl((list[rowIndex].length-1) - columnIndex))
        oneCount = 0
        zeroCount = 0
        columnIndex++
    }

    rowIndex = 0
    columnIndex = list[rowIndex].length-1
    while (columnIndex >= 0) {
        while (rowIndex < list.size) {
            if (list[rowIndex][columnIndex] == '0') zeroCount++ else oneCount++
            rowIndex++
        }
        rowIndex = 0
        if(zeroCount > oneCount) epsilonRate = epsilonRate.or(1.shl((list[rowIndex].length-1) - columnIndex))
        oneCount = 0
        zeroCount = 0
        columnIndex--
    }

    return gammaRate * epsilonRate
}

fun calculateLifeSupport(list: List<String>): Int {
    var charPos = 0
    var oxygenRating = list
    var co2Scrubber = list
    while (oxygenRating.size > 1 && charPos < list[0].length) {
        oxygenRating = when (countMostCommonBit(oxygenRating, charPos)) {
            CommonBit.ONE -> oxygenRating.filter { it[charPos] == '1' }
            CommonBit.ZERO -> oxygenRating.filter { it[charPos] == '0' }
            CommonBit.EQUAL -> oxygenRating.filter { it[charPos] == '1' }
        }
        charPos++
    }
    charPos = 0
    while (co2Scrubber.size > 1 && charPos < co2Scrubber[0].length) {
        co2Scrubber = when (countMostCommonBit(co2Scrubber, charPos)) {
            CommonBit.ONE -> co2Scrubber.filter { it[charPos] == '0' }
            CommonBit.ZERO -> co2Scrubber.filter { it[charPos] == '1' }
            CommonBit.EQUAL -> co2Scrubber.filter { it[charPos] == '0' }
        }
        charPos++
    }
    return oxygenRating[0].toInt(2) * co2Scrubber[0].toInt(2)
}

enum class CommonBit {
    ONE,
    ZERO,
    EQUAL
}

private fun countMostCommonBit(list: List<String>, index: Int): CommonBit {
    var oneBits = 0f
    val halfSize = (list.size.toFloat() / 2)
    list.forEach {
        if(it[index] == '1') oneBits++
    }
    return if(oneBits > halfSize) {
        CommonBit.ONE
    } else if(oneBits < halfSize) {
        CommonBit.ZERO
    } else {
        CommonBit.EQUAL
    }
}
