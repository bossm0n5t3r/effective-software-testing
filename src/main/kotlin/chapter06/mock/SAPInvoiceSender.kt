package me.bossm0n5t3r.chapter06.mock

import me.bossm0n5t3r.chapter06.stub.InvoiceDto
import me.bossm0n5t3r.chapter06.stub.InvoiceFilter

class SAPInvoiceSender(
    private val filter: InvoiceFilter,
    private val sap: SAP,
) {
    fun sendLowValuedInvoices() {
        val lowValuedInvoices: List<InvoiceDto> = filter.lowValueInvoices()
        for (invoice in lowValuedInvoices) {
            sap.send(invoice)
        }
    }
}
