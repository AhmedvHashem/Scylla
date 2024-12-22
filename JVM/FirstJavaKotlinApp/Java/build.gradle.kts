plugins {
    id("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation("io.reactivex.rxjava3:rxjava:3.1.10")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
}

tasks.test {
    useJUnitPlatform()
}

group = "com.hashem.java"
version = "1.0"
