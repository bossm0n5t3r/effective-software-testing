package me.bossm0n5t3r.chapter06.arguments

import me.bossm0n5t3r.chapter06.stub.InvoiceDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object InvoiceToSapInvoiceConverter {
    fun InvoiceDto.toSapInvoice(): SapInvoice {
        val customer: String = this.customer
        val value: Int = this.value
        val sapId = generateId(this)
        return SapInvoice(customer, value, sapId)
    }

    private fun generateId(invoice: InvoiceDto): String {
        val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        val customer: String = invoice.customer
        return date + (if (customer.length >= 2) customer.substring(0, 2) else "X")
    }
}
