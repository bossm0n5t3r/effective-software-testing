package chapter06.christmas

import configuration.resetClock
import me.bossm0n5t3r.chapter06.christmas.ChristmasDiscountWithoutClock
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class ChristmasDiscountWithoutClockTest {
    private val sut = ChristmasDiscountWithoutClock()

    @Test
    fun christmas() {
        val christmas: LocalDate = LocalDate.of(2015, Month.DECEMBER, 25)

        val finalValue =
            resetClock(christmas) {
                sut.applyDiscount(100.0)
            }

        assertThat(finalValue).isCloseTo(85.0, offset(0.001))
    }

    @Test
    fun notChristmas() {
        val notChristmas: LocalDate = LocalDate.of(2015, Month.DECEMBER, 26)

        val finalValue =
            resetClock(notChristmas) {
                sut.applyDiscount(100.0)
            }

        assertThat(finalValue).isCloseTo(100.0, offset(0.001))
    }
}
