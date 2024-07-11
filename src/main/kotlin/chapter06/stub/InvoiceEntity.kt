package me.bossm0n5t3r.chapter06.stub

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class InvoiceEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<InvoiceEntity>(InvoiceTable) {
        fun create(
            customer: String,
            value: Int,
        ): InvoiceEntity {
            return InvoiceEntity.new {
                this.customer = customer
                this.value = value
            }
        }
    }

    var customer by InvoiceTable.customer
    var value by InvoiceTable.value
}
