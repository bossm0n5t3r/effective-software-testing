package me.bossm0n5t3r.chapter06.arguments

import me.bossm0n5t3r.chapter06.stub.InvoiceDto
import me.bossm0n5t3r.chapter06.stub.InvoiceFilter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SAPInvoiceSenderWithArguments(
    private val filter: InvoiceFilter,
    private val sap: SAP,
) {
    fun sendLowValuedInvoices() {
        val lowValuedInvoices: List<InvoiceDto> = filter.lowValueInvoices()
        for (invoice in lowValuedInvoices) {
            val customer: String = invoice.customer
            val value: Int = invoice.value
            val sapId = generateId(invoice)

            val sapInvoice = SapInvoice(customer, value, sapId)
            sap.send(sapInvoice)
        }
    }

    private fun generateId(invoice: InvoiceDto): String {
        val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        val customer: String = invoice.customer
        return date + (if (customer.length >= 2) customer.substring(0, 2) else "X")
    }
}
