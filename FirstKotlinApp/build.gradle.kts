plugins {
    kotlin("jvm") version "1.8.0"
    java
    application
}

group = "com.hashem"
version = "1.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("MainKt")
}

kotlin {
     jvmToolchain(8)
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
