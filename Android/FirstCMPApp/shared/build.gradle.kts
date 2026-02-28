plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {

// Target declarations - add or remove as needed below. These define
// which platforms this KMP module supports.
// See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets

// For iOS targets, this is also where you should
// configure native binary output. For more information, see:
// https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

// A step-by-step guide on how to include this library in an XCode
// project can be found here:
// https://developer.android.com/kotlin/multiplatform/migrate

    androidLibrary {
        namespace = "com.hashem.shared"
        compileSdk = 36
        minSdk = 28
    }
    val sharedLibraryName = "SharedLibrary"

    androidNativeArm64 {
        binaries.sharedLib {
            baseName = sharedLibraryName
        }
    }

//    iosSimulatorArm64 {
//        binaries.framework {
//            baseName = sharedLibraryName
//        }
//    }
    iosArm64 {
        binaries.framework {
            baseName = sharedLibraryName
        }
    }

    macosArm64 {
        binaries.framework {
            baseName = sharedLibraryName
        }
    }

    mingwX64 {
        binaries.sharedLib {
            baseName = sharedLibraryName
        }
    }

    linuxX64 {
        binaries.sharedLib {
            baseName = sharedLibraryName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                // Add KMP dependencies here
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
            }
        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }
    }
}