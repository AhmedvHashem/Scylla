plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.hashem.mediapicker"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true // Enable view binding for easier UI access
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1") // Updated version
    implementation("androidx.appcompat:appcompat:1.7.0") // Updated version
    implementation("com.google.android.material:material:1.12.0") // For Modal UI
    implementation("androidx.activity:activity-ktx:1.9.0") // For Activity Result APIs
    implementation("androidx.fragment:fragment-ktx:1.8.0") // For Activity Result APIs in Fragments

    // Testing dependencies (optional but good practice)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.0") // Updated version
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.0") // Updated version
}

