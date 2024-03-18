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

    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation ("androidx.paging:paging-compose:3.2.1")

    implementation ("androidx.compose.runtime:runtime-livedata:1.6.3")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

//coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    //hilt
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)
    ksp(libs.hiltCompilerKapt)
    implementation (libs.androidx.hilt.navigation.compose)
}