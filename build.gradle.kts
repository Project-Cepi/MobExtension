import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version "1.4.30"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("org.jetbrains.dokka") version "1.4.32"
    kotlin("plugin.serialization") version "1.4.21"
    `maven-publish`

    // Apply the application plugin to add support for building a jar
    java
}

repositories {
    // Use jcenter for resolving dependencies.
    jcenter()
    mavenCentral()

    // Use mavenCentral
    maven(url = "https://repo1.maven.org/maven2/")
    maven(url = "https://repo.spongepowered.org/maven")
    maven(url = "https://libraries.minecraft.net")
    maven(url = "https://jitpack.io")
    maven(url = "https://jcenter.bintray.com/")
    maven(url = "https://repo.velocitypowered.com/snapshots/")
}

dependencies {
    // Align versions of all Kotlin components
    compileOnly(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    compileOnly(kotlin("stdlib"))

    // Use the Kotlin reflect library.
    compileOnly(kotlin("reflect"))

    // Use the JUpiter test library.
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")

    // Compile Minestom into project
    compileOnly("com.github.Project-Cepi:Minestom:b29217e076")

    // Add OkHTTP3
    compileOnly("com.squareup.okhttp3", "okhttp", "4.9.0")

    // Use kotlinx serialization
    compileOnly("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.0.1")

    // implement KStom
    compileOnly("com.github.Project-Cepi:KStom:ca9d47c53e")

    // Use mworlza's canvas
    implementation("com.github.mworzala:canvas:bb1772580e")

    implementation("net.kyori:adventure-text-minimessage:4.0.0-SNAPSHOT")

    // Add levels
    compileOnly("com.github.Project-Cepi:LevelExtension:cfcbcd8bf7")

    // Add Kepi
    compileOnly("com.github.Project-Cepi:Kepi:c6ed2d6884")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configurations {
    testImplementation {
        extendsFrom(configurations.compileOnly.get())
    }
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("mob")
        mergeServiceFiles()
        minimize()
    }

    test { useJUnitPlatform() }

    build { dependsOn(shadowJar) }

}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()

compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}
