package me.bossm0n5t3r.configuration

import org.jetbrains.exposed.sql.Database

object DatabaseConfiguration {
    val db by lazy {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
    }
}
