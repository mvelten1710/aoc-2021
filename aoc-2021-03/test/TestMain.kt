import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class TestMain {

    private val input = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @Test
    fun testCalculatePowerConsumption() {
        val expected = 198
        assertEquals(expected, calculatePowerConsumption(input))
    }

    @Test
    fun testCalculateLifeSupport() {
        val expected = 230
        assertEquals(expected, calculateLifeSupport(input))
    }
}