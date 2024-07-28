package me.bossm0n5t3r.chapter09.large

class ShoppingCart {
    private val items = mutableListOf<Item>()

    fun add(item: Item) {
        items.add(item)
    }

    fun getItems(): List<Item> {
        return items.toList()
    }

    fun numberOfItems(): Int {
        return items.size
    }
}
