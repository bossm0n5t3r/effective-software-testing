package me.bossm0n5t3r.chapter07.domain

import java.time.LocalDate

class ShoppingCart(val value: Double) {
    var isReadyForDelivery: Boolean = false
        private set

    // more info about the shopping cart...
    fun markAsReadyForDelivery(estimatedDayOfDelivery: LocalDate?) {
        this.isReadyForDelivery = true
        // ...
    }
}
