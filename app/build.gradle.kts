plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.jasmeet.cleanarch"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jasmeet.cleanarch"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.paging.runtime.ktx)
    implementation (libs.androidx.paging.compose)

    //Compose-Runtime
    implementation (libs.androidx.runtime.livedata)
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    //Retrofit
    implementation (libs.retrofit)

    //moshi
    implementation(libs.moshi.kotlin)
    implementation (libs.converter.moshi)

//coil
    implementation(libs.coil.compose)

    //hilt
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)
    ksp(libs.hiltCompilerKapt)
    implementation (libs.androidx.hilt.navigation.compose)
}