package me.bossm0n5t3r.chapter07.ports

import me.bossm0n5t3r.chapter07.domain.ShoppingCart
import java.time.LocalDate

interface DeliveryCenter {
    fun deliver(cart: ShoppingCart): LocalDate?
}
