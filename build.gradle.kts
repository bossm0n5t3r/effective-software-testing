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

private val exposedVersion = "0.52.0"
private val mockkVersion = "1.13.11"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("net.jqwik:jqwik-kotlin:1.8.4")

    implementation("com.h2database:h2:2.2.224")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
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
