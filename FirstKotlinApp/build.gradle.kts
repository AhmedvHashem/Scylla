plugins {
    kotlin("jvm") version "1.8.21"
    java
    application
}

group = "com.hashem"
version = "1.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("Main")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
     jvmToolchain(17)
}

dependencies {

}
