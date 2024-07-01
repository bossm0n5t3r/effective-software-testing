package chapter05

import me.bossm0n5t3r.chapter05.Basket
import me.bossm0n5t3r.chapter05.Product
import net.jqwik.api.AfterFailureMode
import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.Combinators
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import net.jqwik.api.stateful.Action
import net.jqwik.api.stateful.ActionSequence
import org.assertj.core.api.Assertions.assertThat
import java.math.BigDecimal

class BasketPBTest {
    data class AddAction(private val product: Product, private val qty: Int) : Action<Basket> {
        override fun run(basket: Basket): Basket {
            val currentValue = basket.totalValue
            basket.add(product, qty)

            val newProductValue = product.price.multiply(BigDecimal.valueOf(qty.toLong()))
            val newValue = currentValue.add(newProductValue)
            assertThat(basket.totalValue).isEqualByComparingTo(newValue)
            return basket
        }
    }

    class RemoveAction : Action<Basket> {
        override fun run(basket: Basket): Basket {
            val currentValue = basket.totalValue

            val productsInBasket: Set<Product> = basket.products()

            // if the basket is empty, simply skip this action
            if (productsInBasket.isEmpty()) return basket

            // pick a random element in the basket to be removed
            val randomProduct = productsInBasket.random()
            val currentProductQty = basket.quantityOf(randomProduct)

            basket.remove(randomProduct)

            val basketValueWithoutRandomProduct =
                currentValue
                    .subtract(randomProduct.price.multiply(BigDecimal.valueOf(currentProductQty.toLong())))

            assertThat(basket.totalValue)
                .isEqualByComparingTo(basketValueWithoutRandomProduct)

            return basket
        }

        override fun toString(): String {
            return "RemoveAction"
        }
    }

    companion object {
        private val randomProducts: List<Product> =
            listOf(
                Product("TV", BigDecimal("100")),
                Product("Playstation", BigDecimal("150.3")),
                Product("Refrigerator", BigDecimal("180.27")),
                Product("Soda", BigDecimal("2.69")),
            )
    }

    private fun addAction(): Arbitrary<AddAction> {
        // create an arbitrary product out of the list of pre-defined products
        val products =
            Arbitraries.oneOf(
                randomProducts
                    .map { product: Product -> Arbitraries.of(product) }
                    .toList(),
            )

        // create arbitrary quantities
        val quantities: Arbitrary<Int> = Arbitraries.integers().between(1, 100)

        // now, we combine products and quantities and generate 'add actions'
        return Combinators
            .combine(products, quantities)
            .`as` { product: Product, qty: Int -> AddAction(product, qty) }
    }

    private fun removeAction(): Arbitrary<RemoveAction> {
        return Arbitraries.of(RemoveAction())
    }

    @Provide
    fun addsAndRemoves(): Arbitrary<ActionSequence<Basket>> {
        // generate arbitrary sequences of adds and removes
        return Arbitraries.sequences(
            Arbitraries.oneOf(
                addAction(),
                removeAction(),
            ),
        )
    }

    @Property(afterFailure = AfterFailureMode.SAMPLE_ONLY)
    fun sequenceOfAddsAndRemoves(
        @ForAll("addsAndRemoves") actions: ActionSequence<Basket>,
    ) {
        actions.run(Basket())
    }
}
