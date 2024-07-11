package configuration

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class MockTablesTest(private vararg val tables: Table) {
    val database =
        Database.connect(
            url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;",
            user = "sa",
            driver = "org.h2.Driver",
        )

    @BeforeAll
    fun setup() {
        transaction(database) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(*tables)
        }
    }

    @AfterEach
    fun clear() {
        transaction(database) {
            addLogger(StdOutSqlLogger)
            tables.forEach { it.deleteAll() }
        }
    }

    @AfterAll
    fun dropTables() {
        transaction(database) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.drop(*tables)
        }
    }
}
