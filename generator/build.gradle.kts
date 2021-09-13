plugins {
    id("application")
}

repositories {
    // Use mavenCentral
    mavenCentral()

    maven(url = "https://jitpack.io")
    maven(url = "https://repo.spongepowered.org/maven")
    maven(url = "https://repo.velocitypowered.com/snapshots/")
}

dependencies {
    implementation("com.squareup:kotlinpoet:1.9.0")
    implementation("org.reflections:reflections:0.9.9-RC1")
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("org.javassist:javassist:3.28.0-GA")
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.2.2")

    // Compile Minestom into project
    implementation("com.github.Minestom:Minestom:fa07d861a6")

    // Use the Kotlin JDK 16 standard library.
    implementation(kotlin("stdlib"))

    // Use the Kotlin reflect library.
    implementation(kotlin("reflect"))

    // Add project meta
    implementation(project(":meta"))

    // Add KStom
    implementation("com.github.Project-Cepi:KStom:785fab5ea2")
}

application {
    mainClass.set("world.cepi.mob.generator.GeneratorKt")
}