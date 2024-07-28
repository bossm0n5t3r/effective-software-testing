package chapter09.large

import me.bossm0n5t3r.chapter09.large.FinalPriceCalculatorFactory
import me.bossm0n5t3r.chapter09.large.Item
import me.bossm0n5t3r.chapter09.large.ItemType
import me.bossm0n5t3r.chapter09.large.ShoppingCart
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FinalPriceCalculatorFactoryTest {
    private val sut = FinalPriceCalculatorFactory().build()

    @Test
    fun appliesAllRules() {
        val cart = ShoppingCart()
        cart.add(Item(ItemType.ELECTRONIC, "PS5", 1, 299.0))
        cart.add(Item(ItemType.OTHER, "BOOK", 1, 29.0))
        cart.add(Item(ItemType.OTHER, "CD", 2, 12.0))
        cart.add(Item(ItemType.OTHER, "CHOCOLATE", 3, 1.50))

        val price: Double = sut.calculate(cart)

        val expectedPrice =
            299 + 29 + 12 * 2 + 1.50 * 3 + // price of the items
                7.50 + // has an electronic
                12.5 // delivery price

        assertThat(price).isEqualTo(expectedPrice)
    }
}
