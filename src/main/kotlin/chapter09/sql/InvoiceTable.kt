package me.bossm0n5t3r.chapter09.sql

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object InvoiceTable : Table("invoice") {
    val customer: Column<String> = varchar("customer", 50)
    val amount: Column<Int> = integer("amount")
}
