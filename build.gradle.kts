import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    jacoco
}

group = "me.bossm0n5t3r"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("net.jqwik:jqwik-kotlin:1.8.4")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
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

jacoco {
    toolVersion = "0.8.11"
}
