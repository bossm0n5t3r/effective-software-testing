package chapter02

import me.bossm0n5t3r.chapter02.CartItem
import me.bossm0n5t3r.chapter02.ShoppingCart
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ShoppingCartTest {
    private val sut = ShoppingCart()

    @Test
    fun noItems() {
        assertThat(sut.totalPrice()).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun itemsInTheCart() {
        sut.add(CartItem("TV", 1, BigDecimal.valueOf(120)))
        assertThat(sut.totalPrice()).isEqualTo(BigDecimal.valueOf(120))

        sut.add(CartItem("Chocolate", 2, BigDecimal.valueOf(2.5)))
        assertThat(sut.totalPrice()).isEqualTo(BigDecimal.valueOf(120 + 2.5 * 2))
    }
}
