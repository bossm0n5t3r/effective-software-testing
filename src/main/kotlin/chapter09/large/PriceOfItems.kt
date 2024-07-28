package me.bossm0n5t3r.chapter09.large

class PriceOfItems : PriceRule {
    override fun priceToAggregate(cart: ShoppingCart): Double {
        var price = 0.0
        val items = cart.getItems()
        for ((_, _, quantity, pricePerUnit) in items) {
            price += pricePerUnit * quantity
        }
        return price
    }
}
