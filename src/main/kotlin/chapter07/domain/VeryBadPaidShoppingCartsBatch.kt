package me.bossm0n5t3r.chapter07.domain

import me.bossm0n5t3r.chapter07.adapters.DeliveryCenterRestApi
import me.bossm0n5t3r.chapter07.adapters.SAPSoapWebService
import me.bossm0n5t3r.chapter07.adapters.SMTPCustomerNotifier
import me.bossm0n5t3r.chapter07.adapters.ShoppingCartRepositoryImpl
import java.time.LocalDate

class VeryBadPaidShoppingCartsBatch {
    fun processAll() {
        // we instantiate the db adapter.
        // Bad for testability!

        val db = ShoppingCartRepositoryImpl()
        val paidShoppingCarts: List<ShoppingCart> = db.cartsPaidToday()
        for (cart in paidShoppingCarts) {
            // notify the delivery system about the delivery
            // but first, we need to instantiate its adapter.
            // Bad for testability!

            val deliveryCenter = DeliveryCenterRestApi()
            val estimatedDayOfDelivery: LocalDate? = deliveryCenter.deliver(cart)

            // mark as ready for delivery and persist
            cart.markAsReadyForDelivery(estimatedDayOfDelivery)
            db.persist(cart)

            // send notification using the adapter directly
            // Bad for testability!
            val notifier = SMTPCustomerNotifier()
            notifier.sendEstimatedDeliveryNotification(cart)

            // notify SAP using the adapter directly
            // Bad for testability!
            val sap = SAPSoapWebService()
            sap.cartReadyForDelivery(cart)
        }
    }
}
