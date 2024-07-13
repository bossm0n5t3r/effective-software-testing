package me.bossm0n5t3r.chapter06.bookstore

class BookStore(
    private val bookRepository: BookRepository,
    private val process: BuyBookProcess,
) {
    private fun retrieveBook(
        ISBN: String?,
        amount: Int?,
        overview: Overview,
    ) {
        var amountValue = amount ?: return
        val book = bookRepository.findByISBN(ISBN) ?: return
        if (book.amount < amountValue) {
            overview.addUnavailable(book, amountValue - book.amount)
            amountValue = book.amount
        }

        overview.addToTotalPrice(amountValue * book.price)
        process.buyBook(book, amountValue)
    }

    fun getPriceForCart(order: Map<String?, Int?>?): Overview? {
        if (order == null) return null

        val overview = Overview()
        for (ISBN in order.keys) retrieveBook(ISBN, order[ISBN], overview)
        return overview
    }
}
