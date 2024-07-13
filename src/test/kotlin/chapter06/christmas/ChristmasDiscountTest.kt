package chapter06.christmas

import io.mockk.every
import io.mockk.mockk
import me.bossm0n5t3r.chapter06.christmas.ChristmasDiscount
import me.bossm0n5t3r.chapter06.christmas.Clock
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class ChristmasDiscountTest {
    private val clock: Clock = mockk<Clock>()
    private val sut = ChristmasDiscount(clock)

    @Test
    fun christmas() {
        val christmas: LocalDate = LocalDate.of(2015, Month.DECEMBER, 25)
        every { clock.now() } returns christmas

        val finalValue = sut.applyDiscount(100.0)
        assertThat(finalValue).isCloseTo(85.0, offset(0.001))
    }

    @Test
    fun notChristmas() {
        val notChristmas: LocalDate = LocalDate.of(2015, Month.DECEMBER, 26)
        every { clock.now() } returns notChristmas

        val finalValue = sut.applyDiscount(100.0)
        assertThat(finalValue).isCloseTo(100.0, offset(0.001))
    }
}
