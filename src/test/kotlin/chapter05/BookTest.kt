package chapter05

import me.bossm0n5t3r.chapter05.Book
import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.Combinators
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide

class BookTest {
    @Property
    fun differentBooks(
        @ForAll("books") book: Book,
    ) {
        // different books!
        println(book)

        // write your test here!
    }

    @Provide
    fun books(): Arbitrary<Book> {
        val titles: Arbitrary<String> =
            Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(10).ofMaxLength(100)
        val authors: Arbitrary<String> =
            Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(5).ofMaxLength(21)
        val qtyOfPages: Arbitrary<Int> = Arbitraries.integers().between(0, 450)
        return Combinators.combine(titles, authors, qtyOfPages)
            .`as` { title: String, author: String, pages: Int ->
                Book(
                    title,
                    author,
                    pages,
                )
            }
    }
}
