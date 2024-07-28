package chapter09.large

import io.mockk.every
import io.mockk.mockk
import me.bossm0n5t3r.chapter09.large.FinalPriceCalculator
import me.bossm0n5t3r.chapter09.large.Item
import me.bossm0n5t3r.chapter09.large.ItemType
import me.bossm0n5t3r.chapter09.large.PriceRule
import me.bossm0n5t3r.chapter09.large.ShoppingCart
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FinalPriceCalculatorTest {
    @Test
    fun callAllPriceRules() {
        val rule1: PriceRule = mockk<PriceRule>()
        val rule2: PriceRule = mockk<PriceRule>()
        val rule3: PriceRule = mockk<PriceRule>()

        val cart = ShoppingCart()
        cart.add(Item(ItemType.OTHER, "ITEM", 1, 1.0))

        every { rule1.priceToAggregate(cart) } returns 1.0
        every { rule2.priceToAggregate(cart) } returns 0.0
        every { rule3.priceToAggregate(cart) } returns 2.0

        val rules: List<PriceRule> = listOf(rule1, rule2, rule3)
        val calculator = FinalPriceCalculator(rules)
        val price = calculator.calculate(cart)

        assertThat(price).isEqualTo(3.0)
    }
}
