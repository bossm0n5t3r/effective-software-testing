package me.bossm0n5t3r.chapter06.bookstore

interface BookRepository {
    fun findByISBN(ISBN: String?): Book?
}
