package me.bossm0n5t3r.chapter09.large

class DeliveryPrice : PriceRule {
    override fun priceToAggregate(cart: ShoppingCart): Double {
        val totalItems = cart.numberOfItems()
        if (totalItems == 0) {
            return 0.0
        }
        if (totalItems <= 3) {
            return 5.0
        }
        if (totalItems <= 10) {
            return 12.5
        }
        return 20.0
    }
}
