import java.io.File

fun main(args: Array<String>) {

    val input = File("aoc-2021-04/input.txt")
        .readLines()

    val picks = input[0].split(',')
    val grids = input.drop(2)

    val boards = mapGridsToBoards(grids)

    val result1 = startBingoGame(picks, boards)

    print("Result for puzzle 1: $result1")
}

fun startBingoGame(picks: List<String>, boards: List<List<String>>): Int {
    val pastPicks = mutableListOf<String>()
    var result: MutableList<String>
    picks.forEachIndexed { index, pick ->
        pastPicks.add(pick)
        if (index > 4) {
            boards.forEach { board ->
                // Check board that contains all the past picks
                // Check all rows
                for (i in board.indices step 5) {
                    if (pastPicks.containsAll(board.subList(i, i + 5))) {
                        result = board.toMutableList()
                        result.removeAll(pastPicks)
                        return result.map { value -> value.toInt() }.reduce { acc, value -> acc + value } * pastPicks.last().toInt()
                    }
                }
                // Check all column
                for (i in 1..4) {
                    if (pastPicks.containsAll(listOf(board[i], board[i + (1*5)], board[i + (2*5)], board[i + (3*5)], board[i + (4*5)])))
                    {
                        result = board.toMutableList()
                        result.removeAll(pastPicks)
                        return result.map { value -> value.toInt() }.reduce { acc, value -> acc + value } * pastPicks.last().toInt()
                    }
                }
            }
        }
    }
    return -1
}

fun mapGridsToBoards(grids: List<String>): List<List<String>> {
    var lineIndex = 0
    val boards = mutableListOf(listOf<String>())
    while (lineIndex < grids.size) {
        val (x1, x2, x3, x4, x5) =
            """(\d+)\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)"""
                .toRegex().find(grids[lineIndex])?.destructured ?: error("Oh no - 1!!")
        val (x6, x7, x8, x9, x10) =
            """(\d+)\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)"""
                .toRegex().find(grids[lineIndex + 1])?.destructured ?: error("Oh no - 2!!")
        val (x11, x12, x13, x14, x15) =
            """(\d+)\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)"""
                .toRegex().find(grids[lineIndex + 2])?.destructured ?: error("Oh no - 3!!")
        val (x16, x17, x18, x19, x20) =
            """(\d+)\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)"""
                .toRegex().find(grids[lineIndex + 3])?.destructured ?: error("Oh no - 4!!")
        val (x21, x22, x23, x24, x25) =
            """(\d+)\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)"""
                .toRegex().find(grids[lineIndex + 4])?.destructured ?: error("Oh no - 5!!")

        boards.add(listOf(
            x1, x2, x3, x4, x5,
            x6, x7, x8, x9, x10,
            x11, x12, x13, x14, x15,
            x16, x17, x18, x19, x20,
            x21, x22, x23, x24, x25
        ))
        //boards.add(grids[lineIndex].split(Regex("\\s+")))
        lineIndex += 6
    }
    return boards.drop(1)
}
