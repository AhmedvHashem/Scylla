plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.hashem.androidnativelib"
    compileSdk = libs.versions.android.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.min.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    externalNativeBuild {
        ndkVersion = "28.0.13004108"
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = "3.31.5"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    testImplementation(libs.testing.junit)
    androidTestImplementation(libs.testing.androidx.junit)
}