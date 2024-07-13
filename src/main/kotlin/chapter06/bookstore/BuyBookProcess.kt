package me.bossm0n5t3r.chapter06.bookstore

interface BuyBookProcess {
    fun buyBook(
        book: Book?,
        amount: Int,
    )
}
