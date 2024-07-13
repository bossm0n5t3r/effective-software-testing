package me.bossm0n5t3r.chapter06.bookstore

import java.util.Collections

class Overview {
    var totalPrice: Int = 0
        private set
    private val unavailable = mutableMapOf<Book, Int>()

    fun addUnavailable(
        book: Book,
        unavailableQty: Int,
    ) {
        unavailable[book] = unavailableQty
    }

    fun addToTotalPrice(valueToAdd: Int) {
        totalPrice += valueToAdd
    }

    fun getUnavailable(): Map<Book, Int> {
        return Collections.unmodifiableMap(unavailable)
    }
}
