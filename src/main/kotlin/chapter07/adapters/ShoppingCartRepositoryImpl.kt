package me.bossm0n5t3r.chapter07.adapters

import me.bossm0n5t3r.chapter07.domain.ShoppingCart
import me.bossm0n5t3r.chapter07.ports.ShoppingCartRepository

class ShoppingCartRepositoryImpl : ShoppingCartRepository {
    override fun cartsPaidToday(): List<ShoppingCart> {
        // get the list of all invoices that were paid today
        return emptyList()
    }

    override fun persist(cart: ShoppingCart) {
        // persist the cart in the database
    }
}
