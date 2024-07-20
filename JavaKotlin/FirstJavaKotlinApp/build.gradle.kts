plugins {
    java
    kotlin("jvm") version "2.0.0"
}

group = "com.hashem"
version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
}

repositories {
    mavenCentral()
}