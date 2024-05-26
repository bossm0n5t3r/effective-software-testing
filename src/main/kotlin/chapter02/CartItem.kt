package me.bossm0n5t3r.chapter02

import java.math.BigDecimal

data class CartItem(
    val product: String,
    val quantity: Int,
    val unitPrice: BigDecimal,
)
