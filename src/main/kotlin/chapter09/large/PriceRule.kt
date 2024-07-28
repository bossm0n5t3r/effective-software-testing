package me.bossm0n5t3r.chapter09.large

interface PriceRule {
    fun priceToAggregate(cart: ShoppingCart): Double
}
