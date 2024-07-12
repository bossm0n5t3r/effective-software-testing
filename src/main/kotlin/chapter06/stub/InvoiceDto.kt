package me.bossm0n5t3r.chapter06.stub

data class InvoiceDto(
    val id: Long,
    val customer: String,
    val value: Int,
)

fun InvoiceEntity.toDto() =
    InvoiceDto(
        id = this.id.value,
        customer = this.customer,
        value = this.value,
    )
