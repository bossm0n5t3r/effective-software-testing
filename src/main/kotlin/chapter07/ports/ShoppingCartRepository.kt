package me.bossm0n5t3r.chapter07.ports

import me.bossm0n5t3r.chapter07.domain.ShoppingCart

interface ShoppingCartRepository {
    fun cartsPaidToday(): List<ShoppingCart>

    fun persist(cart: ShoppingCart)
}
