plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.tobery.libnetwork"
    compileSdk = Apps.compileSdkVersion

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:${Versions.androidx_core_ktx}")
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttp_version}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}")
    implementation("com.google.code.gson:gson:${Versions.gson_version}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines_version}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinx_coroutines_version}")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}