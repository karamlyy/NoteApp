plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.devtools.kts)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.navigation.safeargs)
    kotlin("kapt")

}

android {
    namespace = "com.karamlyy.noteapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.karamlyy.noteapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.roomdb)
    implementation(libs.roomKtx)
    implementation(libs.hilt)
    implementation(libs.coroutines)
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)
    implementation(libs.viewmodel)
    implementation(libs.livedata)
    implementation(libs.lifecycle)
    implementation(libs.savedsatateViewmodel)

    ksp(libs.roomdbCompiler)
    ksp(libs.hiltCompiler)

    kapt(libs.lifecycleCompiler)


    testImplementation (libs.junit)
    testImplementation (libs.mockito.core)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.androidx.core.testing)
    testImplementation (libs.androidx.core)
    testImplementation (libs.androidx.junit)


    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}