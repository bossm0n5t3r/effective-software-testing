package chapter06.stub

import configuration.MockTablesTest
import me.bossm0n5t3r.chapter06.stub.InvoiceEntity
import me.bossm0n5t3r.chapter06.stub.InvoiceFilterWithDatabase
import me.bossm0n5t3r.chapter06.stub.InvoiceTable
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test

class InvoiceEntityFilterWithDatabaseTest : MockTablesTest(InvoiceTable) {
    private val sut = InvoiceFilterWithDatabase(database)

    @Test
    fun filterInvoices(): Unit =
        transaction(database) {
            addLogger(StdOutSqlLogger)
            val mauricio = InvoiceEntity.create("Mauricio", 20)
            val steve = InvoiceEntity.create("Steve", 99)
            InvoiceEntity.create("Frank", 100)

            assertThat(sut.lowValueInvoices())
                .containsExactlyInAnyOrder(mauricio, steve)
        }
}
