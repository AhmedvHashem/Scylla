import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    android {
        namespace = "com.hashem.firstkmpapp.shared"
        compileSdk = libs.versions.android.compile.get().toInt()
        minSdk = libs.versions.android.min.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }

        androidResources {
            enable = true
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    macosArm64 {
        binaries.framework {
            baseName = "Shared"
        }
    }

    linuxX64 {
        binaries.sharedLib {
            baseName = "Shared"
        }
    }

    mingwX64 {
        binaries.sharedLib {
            baseName = "Shared"
        }
    }

    sourceSets {
        androidMain.dependencies {
        }
        iosMain.dependencies {
        }
        commonMain.dependencies {
        }
    }
}
