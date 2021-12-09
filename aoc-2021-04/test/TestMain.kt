import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class TestMain {

    private val input = File("E:\\Projects\\aoc-2021\\aoc-2021-04\\input_test.txt")
        .readLines()

    @Test
    fun testBingGame() {
        val expected = 4512
        val picks = input[0].split(',')
        val grids = input.drop(2)
        val boards = mapGridsToBoards(grids)
        assertEquals(expected, startBingoGame(picks, boards))
    }
}