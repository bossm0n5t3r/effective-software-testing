package chapter06.arguments

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import me.bossm0n5t3r.chapter06.arguments.SAP
import me.bossm0n5t3r.chapter06.arguments.SAPInvoiceSenderWithArguments
import me.bossm0n5t3r.chapter06.arguments.SapInvoice
import me.bossm0n5t3r.chapter06.stub.InvoiceDto
import me.bossm0n5t3r.chapter06.stub.InvoiceFilter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SAPInvoiceSenderWithArgumentsTest {
    private val filter: InvoiceFilter = mockk<InvoiceFilter>()
    private val sap: SAP = mockk<SAP>(relaxed = true)
    private val sender = SAPInvoiceSenderWithArguments(filter, sap)

    @ParameterizedTest
    @CsvSource(
        "Mauricio,Ma",
        "M,X",
    )
    fun sendToSapWithTheGeneratedId(
        customer: String,
        initialId: String,
    ) {
        val mauricio = InvoiceDto(1, customer, 20)

        val invoices: List<InvoiceDto> = listOf(mauricio)
        every { filter.lowValueInvoices() } returns invoices

        sender.sendLowValuedInvoices()

        val sapInvoiceSlot = slot<SapInvoice>()
        verify { sap.send(capture(sapInvoiceSlot)) }

        val generatedSapInvoice: SapInvoice = sapInvoiceSlot.captured

        val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        assertThat(generatedSapInvoice).isEqualTo(SapInvoice(customer, 20, date + initialId))
    }

    @Test
    fun oldExample() {
        val mauricio = InvoiceDto(1, "Mauricio", 20)

        val invoices: List<InvoiceDto> = listOf(mauricio)
        every { filter.lowValueInvoices() } returns invoices

        sender.sendLowValuedInvoices()

        verify { sap.send(any()) }
    }
}
