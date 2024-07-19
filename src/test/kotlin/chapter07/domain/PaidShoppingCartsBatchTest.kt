package chapter07.domain

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import me.bossm0n5t3r.chapter07.domain.PaidShoppingCartsBatch
import me.bossm0n5t3r.chapter07.domain.ShoppingCart
import me.bossm0n5t3r.chapter07.ports.CustomerNotifier
import me.bossm0n5t3r.chapter07.ports.DeliveryCenter
import me.bossm0n5t3r.chapter07.ports.SAP
import me.bossm0n5t3r.chapter07.ports.ShoppingCartRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PaidShoppingCartsBatchTest {
    private val db =
        mockk<ShoppingCartRepository> {
            every { persist(any()) } just Runs
        }
    private val deliveryCenter = mockk<DeliveryCenter>()
    private val notifier =
        mockk<CustomerNotifier> {
            every { sendEstimatedDeliveryNotification(any()) } just Runs
        }
    private val sap =
        mockk<SAP> {
            every { cartReadyForDelivery(any()) } just Runs
        }

    private val sut = PaidShoppingCartsBatch(db, deliveryCenter, notifier, sap)

    @Test
    fun happyPath() {
        val someCart: ShoppingCart = ShoppingCart(100.0)
        assertThat(someCart.isReadyForDelivery).isFalse()

        val someDate: LocalDate = LocalDate.now()
        every { db.cartsPaidToday() } returns listOf(someCart)
        every { deliveryCenter.deliver(someCart) } returns someDate

        sut.processAll()

        verify {
            deliveryCenter.deliver(someCart)
            notifier.sendEstimatedDeliveryNotification(someCart)
            db.persist(someCart)
            sap.cartReadyForDelivery(someCart)
        }

        assertThat(someCart.isReadyForDelivery).isTrue()
    }
}
