package me.bossm0n5t3r.chapter06.mock

import me.bossm0n5t3r.chapter06.stub.InvoiceDto

interface SAP {
    fun send(invoice: InvoiceDto?)
}
