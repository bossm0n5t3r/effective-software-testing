package chapter09.sql

import configuration.MockTablesTest
import me.bossm0n5t3r.chapter09.sql.Invoice
import me.bossm0n5t3r.chapter09.sql.InvoiceDao
import me.bossm0n5t3r.chapter09.sql.InvoiceTable
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test

class InvoiceDaoIntegrationTest : MockTablesTest(InvoiceTable) {
    private val sut = InvoiceDao()

    @Test
    fun save() {
        transaction {
            val inv1 = Invoice("Mauricio", 10)
            val inv2 = Invoice("Frank", 11)

            sut.save(inv1)

            val afterSaving: List<Invoice> = sut.all()
            assertThat(afterSaving).containsExactlyInAnyOrder(inv1)

            sut.save(inv2)
            val afterSavingAgain: List<Invoice> = sut.all()
            assertThat(afterSavingAgain).containsExactlyInAnyOrder(inv1, inv2)
        }
    }

    @Test
    fun atLeast() {
        transaction {
            val value = 50

            /**
             * Explore the boundary: value >= x
             * On point = x
             * Off point = x-1
             * In point = x + 1 (not really necessary, but it's cheap, and makes the
             * test strategy easier to comprehend)
             */
            val inv1 = Invoice("Mauricio", value - 1)
            val inv2 = Invoice("Arie", value)
            val inv3 = Invoice("Frank", value + 1)

            sut.save(inv1)
            sut.save(inv2)
            sut.save(inv3)

            val afterSaving: List<Invoice> = sut.allWithAtLeast(value)
            assertThat(afterSaving).containsExactlyInAnyOrder(inv2, inv3)
        }
    }
}
