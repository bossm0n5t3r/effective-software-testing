package chapter08

import me.bossm0n5t3r.chapter08.BowlingGame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class BowlingGameTest {
    private fun generateRandomFrame(): String {
        val firstFins = Random.nextInt(0, 9)
        val secondFins = Random.nextInt(0, 9 - firstFins)
        return listOf(
            "[X]",
            "[${Random.nextInt(0, 9)} /]",
            "[$firstFins $secondFins]",
        ).random()
    }

    private fun generateRandomFramesString(count: Int) = (1..count).joinToString(" ") { generateRandomFrame() }

    @Test
    fun framesCountShouldBeTen() {
        val nineFramesString = generateRandomFramesString(9)
        assertThrows<IllegalArgumentException> { BowlingGame.calculateScore(nineFramesString) }

        val elevenFramesString = generateRandomFramesString(11)
        assertThrows<IllegalArgumentException> { BowlingGame.calculateScore(elevenFramesString) }

        val tenFramesString = generateRandomFramesString(10)
        assertDoesNotThrow { BowlingGame.calculateScore(tenFramesString) }
    }
}
