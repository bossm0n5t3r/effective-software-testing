package configuration

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class TransactionTest {
    private val mockTransactionManager = MockTransactionManager()
    val database = mockTransactionManager.mockedDatabase

    @BeforeEach
    fun setup() {
        mockTransactionManager.apply()
    }

    @AfterEach
    fun clean() {
        mockTransactionManager.reset()
    }
}
