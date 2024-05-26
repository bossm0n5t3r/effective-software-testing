package me.bossm0n5t3r.chapter02

import java.math.BigDecimal

class ShoppingCart {
    private val items = mutableListOf<CartItem>()

    fun add(item: CartItem) = items.add(item)

    fun totalPrice() =
        items.foldRight(BigDecimal.ZERO) { cartItem, acc ->
            acc + cartItem.unitPrice.times(BigDecimal.valueOf(cartItem.quantity.toLong()))
        }
}
