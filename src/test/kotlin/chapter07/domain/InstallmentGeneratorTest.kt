package chapter07.domain

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import me.bossm0n5t3r.chapter07.domain.Installment
import me.bossm0n5t3r.chapter07.domain.InstallmentGenerator
import me.bossm0n5t3r.chapter07.domain.InstallmentRepository
import me.bossm0n5t3r.chapter07.domain.ShoppingCart
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class InstallmentGeneratorTest {
    private val repository =
        mockk<InstallmentRepository> {
            every { persist(any()) } just Runs
        }

    private val sut = InstallmentGenerator(repository)

    @Test
    fun checkInstallments() {
        val cart = ShoppingCart(100.0)

        sut.generateInstallments(cart, 10)

        // create a Mockito captor
        val allInstallments = mutableListOf<Installment>()

        // get all the Installments that were passed to the repository
        verify(exactly = 10) { repository.persist(capture(allInstallments)) }

        // now, we assert that the installments are correct
        // all  them should have a value of 10.0
        assertThat(allInstallments)
            .hasSize(10)
            .allMatch { i -> i.value == 10.0 }

        // they should have to be one month apart
        for (month in 1..10) {
            val dueDate: LocalDate = LocalDate.now().plusMonths(month.toLong())
            assertThat(allInstallments)
                .anyMatch { i -> i.date == dueDate }
        }
    }

    @Test
    fun checkInstallments2() {
        val cart = ShoppingCart(100.0)

        val allInstallments = sut.generateInstallments2(cart, 10)

        // now, we assert that the installments are correct
        // all  them should have a value of 10.0
        assertThat(allInstallments)
            .hasSize(10)
            .allMatch { i -> i.value == 10.0 }

        // they should have to be one month apart
        for (month in 1..10) {
            val dueDate = LocalDate.now().plusMonths(month.toLong())
            assertThat(allInstallments)
                .anyMatch { i -> i.date == dueDate }
        }
    }
}
