package me.bossm0n5t3r.chapter09.sql

import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.ResultSet

class InvoiceDao {
    /**
     * https://jetbrains.github.io/Exposed/frequently-asked-questions.html#q-is-it-possible-to-use-native-sql-sql-as-a-string
     */
    private fun <T : Any> String.execAndMap(transform: (ResultSet) -> T): List<T> {
        val result = mutableListOf<T>()
        TransactionManager.current().exec(this) { rs ->
            while (rs.next()) {
                result += transform(rs)
            }
        }
        return result
    }

    fun all(): List<Invoice> {
        return try {
            "select * from invoice".execAndMap { resultSet ->
                val customer = resultSet.getString("customer")
                val amount = resultSet.getInt("amount")
                Invoice(customer, amount)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun allWithAtLeast(value: Int): List<Invoice> {
        return try {
            "select * from invoice where amount >= $value".execAndMap { resultSet ->
                val customer = resultSet.getString("customer")
                val amount = resultSet.getInt("amount")
                Invoice(customer, amount)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun save(invoice: Invoice) {
        try {
            val (customer, amount) = invoice
            "insert into invoice (customer, amount) values ('$customer', $amount)".execAndMap {}
        } catch (e: Exception) {
            throw e
        }
    }
}
