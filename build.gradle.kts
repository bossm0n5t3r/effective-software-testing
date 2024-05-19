import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

group = "me.bossm0n5t3r"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("net.jqwik:jqwik-kotlin:1.8.4")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

ktlint {
    version.set("1.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs =
            listOf(
                // Strict interpretation of nullability annotations in jqwik API
                "-Xjsr305=strict",
                // Enable nnotations on type variables
                "-Xemit-jvm-type-annotations",
            )
        jvmTarget = "21"
        javaParameters = true // Get correct parameter names in jqwik reporting
    }
}
