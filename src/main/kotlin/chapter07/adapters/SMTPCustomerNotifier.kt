package me.bossm0n5t3r.chapter07.adapters

import me.bossm0n5t3r.chapter07.domain.ShoppingCart
import me.bossm0n5t3r.chapter07.ports.CustomerNotifier

class SMTPCustomerNotifier : CustomerNotifier {
    override fun sendEstimatedDeliveryNotification(cart: ShoppingCart) {
        // all the required code to
        // send an email via SMTP
    }
}
