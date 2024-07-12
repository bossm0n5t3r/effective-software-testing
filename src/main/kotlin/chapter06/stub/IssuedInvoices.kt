package me.bossm0n5t3r.chapter06.stub

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class IssuedInvoices(
    private val database: Database,
) {
    fun all(): List<InvoiceDto> =
        transaction(db = database) {
            InvoiceEntity.all().map { it.toDto() }
        }
}
