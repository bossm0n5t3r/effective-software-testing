package me.bossm0n5t3r.chapter09.large

class FinalPriceCalculator(private val rules: List<PriceRule>) {
    fun calculate(cart: ShoppingCart): Double {
        var finalPrice = 0.0

        for (rule in rules) {
            finalPrice += rule.priceToAggregate(cart)
        }

        return finalPrice
    }
}
