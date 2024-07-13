package chapter06.exception

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.bossm0n5t3r.chapter06.exception.SAP
import me.bossm0n5t3r.chapter06.exception.SAPException
import me.bossm0n5t3r.chapter06.exception.SAPInvoiceSenderWithException
import me.bossm0n5t3r.chapter06.exception.SapInvoice
import me.bossm0n5t3r.chapter06.stub.InvoiceDto
import me.bossm0n5t3r.chapter06.stub.InvoiceFilter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SAPInvoiceSenderWithExceptionTest {
    private val filter: InvoiceFilter = mockk<InvoiceFilter>()
    private val sap: SAP = mockk<SAP>(relaxed = true)
    private val sut: SAPInvoiceSenderWithException = SAPInvoiceSenderWithException(filter, sap)

    @Test
    fun sendSapInvoiceToSap() {
        val mauricio = InvoiceDto(1, "Mauricio", 20)

        val invoices = listOf(mauricio)
        every { filter.lowValueInvoices() } returns invoices

        sut.sendLowValuedInvoices()

        verify { sap.send(any()) }
    }

    @Test
    fun returnFailedInvoices() {
        val mauricio = InvoiceDto(1, "Mauricio", 20)
        val frank = InvoiceDto(2, "Frank", 25)
        val steve = InvoiceDto(3, "Steve", 48)

        val invoices: List<InvoiceDto> = listOf(mauricio, frank, steve)
        every { filter.lowValueInvoices() } returns invoices

        val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        val franksInvoice = SapInvoice("Frank", 25, date + "Fr")
        every { sap.send(franksInvoice) } throws SAPException()

        val failedInvoices: List<InvoiceDto> = sut.sendLowValuedInvoices()
        assertThat(failedInvoices).containsExactly(frank)

        val mauriciosInvoice = SapInvoice("Mauricio", 20, date + "Ma")
        verify { sap.send(mauriciosInvoice) }

        val stevesInvoice = SapInvoice("Steve", 48, date + "St")
        verify { sap.send(stevesInvoice) }
    }
}
