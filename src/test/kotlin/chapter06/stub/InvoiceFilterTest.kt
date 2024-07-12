package chapter06.stub

import configuration.TransactionTest
import io.mockk.every
import io.mockk.mockk
import me.bossm0n5t3r.chapter06.stub.InvoiceDto
import me.bossm0n5t3r.chapter06.stub.InvoiceFilter
import me.bossm0n5t3r.chapter06.stub.IssuedInvoices
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InvoiceFilterTest : TransactionTest() {
    private val mockIssuedInvoices = mockk<IssuedInvoices>(relaxed = true)
    private val sut = InvoiceFilter(database, mockIssuedInvoices)

    @Test
    fun filterInvoices() {
        val mauricio = InvoiceDto(1, "Mauricio", 20)
        val steve = InvoiceDto(2, "Steve", 99)
        val frank = InvoiceDto(3, "Frank", 100)

        val listOfInvoices: List<InvoiceDto> = listOf(mauricio, steve, frank)
        every { mockIssuedInvoices.all() } returns listOfInvoices

        assertThat(sut.lowValueInvoices())
            .containsExactlyInAnyOrder(mauricio, steve)
    }
}
