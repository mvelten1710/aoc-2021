import main.kotlin.calculateNewPosition
import main.kotlin.calculatePosition
import org.junit.Test
import kotlin.test.assertEquals

class TestMain {

    private val input = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )

    @Test
    fun testCalculatePosition() {
        val expected = Pair(15, 10)
        assertEquals(expected, calculatePosition(input))
    }

    @Test
    fun testCalculateNewPosition() {
        val expected = Pair(15, 60)
        assertEquals(expected, calculateNewPosition(input))
    }
}