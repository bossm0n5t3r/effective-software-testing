package me.bossm0n5t3r.chapter06.stub

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object InvoiceTable : LongIdTable() {
    val customer: Column<String> = varchar("customer", 50).uniqueIndex()
    val value: Column<Int> = integer("value")
}
