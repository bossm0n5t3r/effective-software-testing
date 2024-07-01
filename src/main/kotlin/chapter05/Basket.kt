package me.bossm0n5t3r.chapter05

import java.math.BigDecimal

class Basket {
    var totalValue: BigDecimal = BigDecimal.ZERO
    private val basket = mutableMapOf<Product, Int>()

    fun add(
        product: Product?,
        qtyToAdd: Int,
    ) {
        requireNotNull(product) { "Product is required" }
        require(qtyToAdd > 0) { "Quantity has to be greater than zero" }
        val oldTotalValue: BigDecimal = totalValue

        val existingQuantity: Int = basket.getOrDefault(product, 0)
        val newQuantity: Int = existingQuantity + qtyToAdd
        basket[product] = newQuantity

        val valueAlreadyInTheCart: BigDecimal = product.price.multiply(BigDecimal.valueOf(existingQuantity.toLong()))
        val newFinalValueForTheProduct: BigDecimal = product.price.multiply(BigDecimal.valueOf(newQuantity.toLong()))

        totalValue =
            totalValue
                .subtract(valueAlreadyInTheCart)
                .add(newFinalValueForTheProduct)

        assert(basket.containsKey(product)) { "Product was not inserted in the basket" }
        assert(totalValue > oldTotalValue) { "Total value should be greater than previous total value" }
        assert(invariant()) { "Invariant does not hold" }
    }

    private fun invariant(): Boolean {
        return totalValue >= BigDecimal.ZERO
    }

    fun remove(product: Product?) {
        requireNotNull(product) { "product can't be null" }

        val qty = basket[product]
        requireNotNull(qty) { "Product must already be in the basket" }

        totalValue = totalValue.subtract(product.price.multiply(BigDecimal.valueOf(qty.toLong())))

        basket.remove(product)

        assert(!basket.containsKey(product)) { "Product is still in the basket" }
        assert(invariant()) { "Invariant does not hold" }
    }

    fun products(): Set<Product> {
        return basket.keys.toSet()
    }

    fun quantityOf(product: Product): Int {
        val quantity = basket[product]
        requireNotNull(quantity)
        return quantity
    }
}
