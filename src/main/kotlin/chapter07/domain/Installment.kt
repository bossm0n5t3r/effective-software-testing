package me.bossm0n5t3r.chapter07.domain

import java.time.LocalDate

data class Installment(
    val date: LocalDate,
    val value: Double,
)
