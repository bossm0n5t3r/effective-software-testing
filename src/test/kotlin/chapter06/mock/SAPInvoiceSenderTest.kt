package chapter06.mock

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.bossm0n5t3r.chapter06.mock.SAP
import me.bossm0n5t3r.chapter06.mock.SAPInvoiceSender
import me.bossm0n5t3r.chapter06.stub.InvoiceDto
import me.bossm0n5t3r.chapter06.stub.InvoiceFilter
import org.junit.jupiter.api.Test

class SAPInvoiceSenderTest {
    private val filter: InvoiceFilter = mockk<InvoiceFilter>()
    private val sap: SAP = mockk<SAP>(relaxed = true)
    private val sut = SAPInvoiceSender(filter, sap)

    @Test
    fun sendToSap() {
        val mauricio = InvoiceDto(1, "Mauricio", 20)
        val frank = InvoiceDto(2, "Frank", 99)

        val invoices: List<InvoiceDto> = listOf(mauricio, frank)
        every { filter.lowValueInvoices() } returns invoices

        sut.sendLowValuedInvoices()

        verify { sap.send(mauricio) }
        verify { sap.send(frank) }
    }

    @Test
    fun noLowValueInvoices() {
        val invoices = emptyList<InvoiceDto>()
        every { filter.lowValueInvoices() } returns invoices

        sut.sendLowValuedInvoices()

        verify(exactly = 0) { sap.send(any()) }
    }
}
