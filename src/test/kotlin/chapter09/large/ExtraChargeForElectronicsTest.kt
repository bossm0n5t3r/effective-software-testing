package chapter09.large

import me.bossm0n5t3r.chapter09.large.ExtraChargeForElectronics
import me.bossm0n5t3r.chapter09.large.Item
import me.bossm0n5t3r.chapter09.large.ItemType
import me.bossm0n5t3r.chapter09.large.ShoppingCart
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ExtraChargeForElectronicsTest {
    private val sut = ExtraChargeForElectronics()

    @ParameterizedTest
    @CsvSource("1", "2")
    fun chargeTheExtraPriceIfThereIsAnyElectronicInTheCart(numberOfElectronics: Int) {
        val cart = ShoppingCart()
        for (i in 0 until numberOfElectronics) {
            cart.add(Item(ItemType.ELECTRONIC, "ANY ELECTRONIC", 1, 1.0))
        }

        val price = sut.priceToAggregate(cart)
        assertThat(price).isEqualTo(7.50)
    }

    @Test
    fun noExtraChargesIfNoElectronics() {
        val cart = ShoppingCart()
        cart.add(Item(ItemType.OTHER, "BOOK", 1, 1.0))
        cart.add(Item(ItemType.OTHER, "CD", 1, 1.0))
        cart.add(Item(ItemType.OTHER, "BABY TOY", 1, 1.0))

        val price = sut.priceToAggregate(cart)
        assertThat(price).isEqualTo(0.0)
    }

    @Test
    fun noItems() {
        val cart = ShoppingCart()
        val price = sut.priceToAggregate(cart)
        assertThat(price).isEqualTo(0.0)
    }
}
