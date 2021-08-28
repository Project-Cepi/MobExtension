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
    implementation("com.google.guava:guava:21.0")
    implementation("org.javassist:javassist:3.22.0-CR1")
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.2.2")

    // Compile Minestom into project
    implementation("com.github.Minestom:Minestom:fa07d861a6")

    // Use the Kotlin JDK 16 standard library.
    implementation(kotlin("stdlib"))

    // Use the Kotlin reflect library.
    implementation(kotlin("reflect"))

    // Add project meta
    implementation(project(":meta"))
}

application {
    mainClass.set("world.cepi.mob.generator.GeneratorKt")
}