package chapter06.bookstore

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.bossm0n5t3r.chapter06.bookstore.Book
import me.bossm0n5t3r.chapter06.bookstore.BookRepository
import me.bossm0n5t3r.chapter06.bookstore.BookStore
import me.bossm0n5t3r.chapter06.bookstore.BuyBookProcess
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.Test

class BookStoreTest {
    @Test
    fun emptyOrder() {
        val bookRepo: BookRepository = mockk<BookRepository>()
        val process: BuyBookProcess = mockk<BuyBookProcess>()
        val bookStore = BookStore(bookRepo, process)

        val orderMap = emptyMap<String?, Int?>()
        val overview = bookStore.getPriceForCart(orderMap)

        assertThat(overview).isNotNull
        assertThat(overview?.totalPrice).isEqualTo(0)
        assertThat(overview?.getUnavailable()).isEmpty()
    }

    @Test
    fun nullOrder() {
        val bookRepo: BookRepository = mockk<BookRepository>()
        val process: BuyBookProcess = mockk<BuyBookProcess>()
        val bookStore = BookStore(bookRepo, process)

        val overview = bookStore.getPriceForCart(null)

        assertThat(overview).isNull()
    }

    @Test
    fun moreComplexOrder() {
        val bookRepo: BookRepository = mockk<BookRepository>()
        val process: BuyBookProcess = mockk<BuyBookProcess>(relaxed = true)

        val orderMap: MutableMap<String?, Int?> = HashMap()

        /**
         * Let's have three books:
         * - one where there's enough quantity
         * - one where the available quantity is precisely what's asked in the order
         * - one where there's not enough quantity
         */
        orderMap["PRODUCT-ENOUGH-QTY"] = 5
        orderMap["PRODUCT-PRECISE-QTY"] = 10
        orderMap["PRODUCT-NOT-ENOUGH"] = 22

        val book1 = Book("PRODUCT-ENOUGH-QTY", 20, 11) // 11 is more than 5
        every { bookRepo.findByISBN("PRODUCT-ENOUGH-QTY") } returns book1
        val book2 = Book("PRODUCT-PRECISE-QTY", 25, 10) // 10 == 10
        every { bookRepo.findByISBN("PRODUCT-PRECISE-QTY") } returns book2
        val book3 = Book("PRODUCT-NOT-ENOUGH", 37, 21) // 21 < 22
        every { bookRepo.findByISBN("PRODUCT-NOT-ENOUGH") } returns book3

        val bookStore = BookStore(bookRepo, process)
        val overview = bookStore.getPriceForCart(orderMap)

        // First, we ensure that the total price is correct
        val expectedPrice =
            // from the first product
            // from the second product
            5 * 20 + 10 * 25 + 21 * 37 // from the third product

        assertThat(overview).isNotNull
        assertThat(overview?.totalPrice).isEqualTo(expectedPrice)

        // Then, we ensure that the buy process was called
        verify { process.buyBook(book1, 5) }
        verify { process.buyBook(book2, 10) }
        verify { process.buyBook(book3, 21) }

        // Finally, we ensure that the list of unavailable contains the one book that's missing
        assertThat(overview?.getUnavailable())
            .containsExactly(entry(book3, 1))
    }
}
