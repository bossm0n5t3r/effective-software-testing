package chapter08

import me.bossm0n5t3r.chapter08.BowlingGame
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.random.Random

class BowlingGameTest {
    private fun generateRandomFramesString(
        count: Int = 10,
        isLastFrameStrikeOrSpare: Boolean = Random.nextBoolean(),
    ): String {
        val firstFins = Random.nextInt(0, 9)
        val secondFins = Random.nextInt(0, 9 - firstFins)
        val previousFrames =
            (1 until count).map {
                listOf(
                    "[X]",
                    "[${Random.nextInt(0, 9)} /]",
                    "[$firstFins $secondFins]",
                ).random()
            }
        val lastFrame =
            if (isLastFrameStrikeOrSpare) {
                listOf(
                    "[X X X]",
                    "[X X $firstFins]",
                    "[X $firstFins $secondFins]",
                    "[$firstFins / X]",
                    "[$firstFins / ${Random.nextInt(0, 9)}]",
                ).random()
            } else {
                "[$firstFins $secondFins]"
            }
        return (previousFrames + lastFrame).joinToString(" ")
    }

    @Test
    fun framesCountShouldBeTen() {
        val nineFramesString = generateRandomFramesString(9)
        assertThrows<IllegalArgumentException> { BowlingGame.calculateScore(nineFramesString) }

        val elevenFramesString = generateRandomFramesString(11)
        assertThrows<IllegalArgumentException> { BowlingGame.calculateScore(elevenFramesString) }

        val tenFramesString = generateRandomFramesString(10)
        assertDoesNotThrow { BowlingGame.calculateScore(tenFramesString) }
    }

    @ParameterizedTest
    @CsvSource(
        "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X X X], 230",
        "[0 2] [1 3] [X] [1 2] [X] [X] [3 1] [2 /] [X] [X 1 2], 116",
        "[3 /] [X] [6 2] [3 5] [1 /] [4 1] [X] [8 0] [6 1] [2 1], 109",
        "[X] [X] [4 1] [6 /] [0 8] [2 0] [1 0] [3 /] [8 /] [5 3], 102",
        "[X] [X] [X] [X] [5 /] [X] [X] [2 2] [4 0] [6 / X], 162",
    )
    fun calculateScoreTest(
        framesString: String,
        expectedScore: Int,
    ) {
        assertThat(BowlingGame.calculateScore(framesString)).isEqualTo(expectedScore)
    }
}
