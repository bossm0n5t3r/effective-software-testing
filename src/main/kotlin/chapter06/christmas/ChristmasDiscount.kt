package me.bossm0n5t3r.chapter06.christmas

import java.time.LocalDate
import java.time.Month

class ChristmasDiscount(
    private val clock: Clock,
) {
    fun applyDiscount(rawAmount: Double): Double {
        val today: LocalDate = clock.now()

        var discountPercentage = 0.0
        val isChristmas = today.month == Month.DECEMBER && today.dayOfMonth == 25

        if (isChristmas) discountPercentage = 0.15

        return rawAmount - (rawAmount * discountPercentage)
    }
}
