package configuration

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.TransactionManager

internal class MockTransactionManager : TransactionManager {
    override var defaultIsolationLevel: Int = -1
    override var defaultReadOnly: Boolean = false
    override var defaultMaxAttempts: Int = -1
    override var defaultMinRetryDelay: Long = 0
    override var defaultMaxRetryDelay: Long = 0

    @Deprecated("This will be removed when the interface property is fully deprecated", level = DeprecationLevel.WARNING)
    override var defaultRepetitionAttempts: Int = -1

    @Deprecated("This will be removed when the interface property is fully deprecated", level = DeprecationLevel.WARNING)
    override var defaultMinRepetitionDelay: Long = 0

    @Deprecated("This will be removed when the interface property is fully deprecated", level = DeprecationLevel.WARNING)
    override var defaultMaxRepetitionDelay: Long = 0

    val mockedDatabase = mockk<Database>(relaxed = true)

    override fun bindTransactionToThread(transaction: Transaction?) {}

    override fun currentOrNull() = transaction()

    private fun transaction(): Transaction {
        return mockk(relaxed = true) {
            every { db } returns mockedDatabase
        }
    }

    override fun newTransaction(
        isolation: Int,
        readOnly: Boolean,
        outerTransaction: Transaction?,
    ) = transaction()

    fun apply() {
        TransactionManager.registerManager(mockedDatabase, this@MockTransactionManager)
        Database.connect({ mockk(relaxed = true) }, null, { this })
    }

    fun reset() {
        TransactionManager.resetCurrent(null)
        TransactionManager.closeAndUnregister(mockedDatabase)
    }
}
