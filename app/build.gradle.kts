@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    namespace = "com.tobery.ktmusic"
    compileSdk = Apps.compileSdkVersion

    defaultConfig {
        applicationId = Apps.appID
        minSdk = Apps.minSdkVersion
        targetSdk = Apps.targetSdkVersion
        versionCode = Apps.versionCode
        versionName = Apps.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_version
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Libs
    implementation(project(":libnetwork"))

    implementation("androidx.core:core-ktx:${Versions.androidx_core_ktx}")
    implementation("androidx.activity:activity-compose:${Versions.compose_activity}")
    implementation("androidx.navigation:navigation-compose:${Versions.compose_navigation}")
    implementation("androidx.paging:paging-compose:${Versions.androidx_paging}")
    implementation("androidx.core:core-splashscreen:${Versions.androidx_splash}")

    //compose-bom
    val composeBom = platform("androidx.compose:compose-bom:${Versions.compose_bom_version}")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui:${Versions.compose_ui}")
    implementation("androidx.compose.ui:ui-util")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.material3:material3:${Versions.compose_material3}")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.material:material")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
    implementation("androidx.lifecycle:lifecycle-runtime-compose")
    implementation("androidx.compose.runtime:runtime")

    // Google
    implementation("com.google.accompanist:accompanist-webview:${Versions.google_accompanist}")
    implementation("com.google.accompanist:accompanist-placeholder-material:${Versions.google_accompanist}")
    implementation("com.google.accompanist:accompanist-swiperefresh:${Versions.google_accompanist}")
    implementation("com.google.accompanist:accompanist-flowlayout:${Versions.google_accompanist}")
    implementation("com.google.accompanist:accompanist-pager-indicators:${Versions.google_accompanist}")
    implementation("com.google.code.gson:gson:${Versions.gson_version}")

    //3rd lib
    //okhttp
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttp_version}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}")
    // Coil image
    implementation("io.coil-kt:coil-gif:${Versions.compose_coil}")
    implementation("io.coil-kt:coil-compose:${Versions.compose_coil}")
}