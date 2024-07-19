package me.bossm0n5t3r.chapter07.domain

import me.bossm0n5t3r.chapter07.ports.CustomerNotifier
import me.bossm0n5t3r.chapter07.ports.DeliveryCenter
import me.bossm0n5t3r.chapter07.ports.SAP
import me.bossm0n5t3r.chapter07.ports.ShoppingCartRepository
import java.time.LocalDate

class PaidShoppingCartsBatch(
    private val db: ShoppingCartRepository,
    private val deliveryCenter: DeliveryCenter,
    private val notifier: CustomerNotifier,
    private val sap: SAP,
) {
    fun processAll() {
        val paidShoppingCarts = db.cartsPaidToday()
        for (cart in paidShoppingCarts) {
            // notify the delivery system about the delivery
            val estimatedDayOfDelivery: LocalDate? = deliveryCenter.deliver(cart)

            // mark as ready for delivery and persist
            cart.markAsReadyForDelivery(estimatedDayOfDelivery)
            db.persist(cart)

            // send e-mail
            notifier.sendEstimatedDeliveryNotification(cart)

            // notify SAP
            sap.cartReadyForDelivery(cart)
        }
    }
}
