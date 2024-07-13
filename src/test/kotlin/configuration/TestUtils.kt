package configuration

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import java.time.LocalDate

fun <T> resetClock(
    targetLocalDate: LocalDate,
    block: () -> T,
): T {
    mockkStatic(LocalDate::class)
    every { LocalDate.now() } returns targetLocalDate
    return block.invoke().also { unmockkStatic(LocalDate::class) }
}
