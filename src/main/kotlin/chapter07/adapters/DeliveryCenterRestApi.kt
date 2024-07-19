package me.bossm0n5t3r.chapter07.adapters

import me.bossm0n5t3r.chapter07.domain.ShoppingCart
import me.bossm0n5t3r.chapter07.ports.DeliveryCenter
import java.time.LocalDate

class DeliveryCenterRestApi : DeliveryCenter {
    override fun deliver(cart: ShoppingCart): LocalDate? {
        // all the code required to communicate
        // with the delivery API
        // and returns a LocalDate
        return null
    }
}
