plugins {
    java
    kotlin("jvm") version "1.8.21"
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
    implementation(kotlin("stdlib"))
}

repositories {
    mavenCentral()
}