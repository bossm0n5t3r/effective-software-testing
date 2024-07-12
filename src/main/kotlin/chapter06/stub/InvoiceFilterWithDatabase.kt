package me.bossm0n5t3r.chapter06.stub

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class InvoiceFilterWithDatabase(
    private val database: Database,
) {
    fun lowValueInvoices(): List<InvoiceDto> =
        transaction(database) {
            val issuedInvoices = IssuedInvoices(database)
            issuedInvoices.all().filter { invoice -> invoice.value < 100 }
        }
}
