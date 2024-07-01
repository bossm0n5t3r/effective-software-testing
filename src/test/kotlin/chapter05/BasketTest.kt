package chapter05

import me.bossm0n5t3r.chapter05.Basket
import me.bossm0n5t3r.chapter05.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class BasketTest {
    private val sut = Basket()

    @Test
    fun addProducts() {
        sut.add(Product("TV", BigDecimal.valueOf(10)), 2)
        sut.add(Product("Playstation", BigDecimal.valueOf(100)), 1)

        assertThat(sut.totalValue)
            .isEqualByComparingTo(BigDecimal.valueOf(10 * 2 + 100 * 1))
    }

    @Test
    fun addSameProductTwice() {
        val p = Product("TV", BigDecimal.valueOf(10))
        sut.add(p, 2)
        sut.add(p, 3)

        assertThat(sut.totalValue)
            .isEqualTo(BigDecimal.valueOf(10 * 5))
    }

    @Test
    fun removeProducts() {
        sut.add(Product("TV", BigDecimal.valueOf(100)), 1)

        val p = Product("Playstation", BigDecimal.valueOf(10))
        sut.add(p, 2)
        sut.remove(p)

        assertThat(sut.totalValue)
            .isEqualTo(BigDecimal.valueOf(100))
    }

    // tests for exceptional cases...
}
