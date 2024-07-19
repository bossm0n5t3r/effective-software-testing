package me.bossm0n5t3r.chapter07.adapters

import me.bossm0n5t3r.chapter07.domain.ShoppingCart
import me.bossm0n5t3r.chapter07.ports.SAP

class SAPSoapWebService : SAP {
    override fun cartReadyForDelivery(cart: ShoppingCart) {
        // all the code required to send the
        // cart to SAP's SOAP web service
    }
}
