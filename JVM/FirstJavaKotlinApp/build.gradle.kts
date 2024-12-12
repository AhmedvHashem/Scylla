plugins {
    java
    kotlin("jvm") version "2.0.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
}

group = "com.hashem"
version = "1.0"
