package chapter09.large

import me.bossm0n5t3r.chapter09.large.DeliveryPrice
import me.bossm0n5t3r.chapter09.large.Item
import me.bossm0n5t3r.chapter09.large.ItemType
import me.bossm0n5t3r.chapter09.large.ShoppingCart
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DeliveryPriceTest {
    private val sut = DeliveryPrice()

    @ParameterizedTest
    @CsvSource(
        "0,0",
        "1,5",
        "3,5",
        "4,12.5",
        "10,12.5",
        "11,20",
    )
    fun deliveryIsAccordingToTheNumberOfItems(
        noOfItems: Int,
        expectedDeliveryPrice: Double,
    ) {
        val cart = ShoppingCart()
        for (i in 0 until noOfItems) {
            cart.add(Item(ItemType.OTHER, "ANY", 1, 1.0))
        }

        val price = sut.priceToAggregate(cart)
        assertThat(price).isEqualTo(expectedDeliveryPrice)
    }
}
