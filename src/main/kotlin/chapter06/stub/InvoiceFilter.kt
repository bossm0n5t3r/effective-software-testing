package me.bossm0n5t3r.chapter06.stub

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class InvoiceFilter(
    private val database: Database,
    private val issuedInvoices: IssuedInvoices,
) {
    fun lowValueInvoices(): List<InvoiceDto> =
        transaction(database) {
            issuedInvoices
                .all()
                .filter { invoice -> invoice.value < 100 }
        }
}
