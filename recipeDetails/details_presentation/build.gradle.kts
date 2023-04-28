plugins {
    id("com.android.library")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.details_presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

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
        viewBinding = true
    }
}

dependencies {
    implementation(project(":recipeDetails:details_domain"))
    implementation(project(":recipeDetails:details_data"))
    implementation(project(":commons"))

    implementation(Dependencies.Activity.activityKtx)
    implementation(Dependencies.Core.legacySupport)
    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)

    implementation(Dependencies.picasso)

    implementation(Dependencies.Coroutines.androidCoroutines)
    implementation(Dependencies.Coroutines.coreCoroutines)
    implementation(Dependencies.LifeCycle.viewModel)
    implementation(Dependencies.LifeCycle.viewModelSavedState)
    implementation(Dependencies.LifeCycle.lifeCycleRuntime)
    implementation(Dependencies.LifeCycle.lifeCycleRuntimeKtx)
    implementation(Dependencies.LifeCycle.lifecycleExt)


    // Dagger - Hilt
    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.hiltCompiler)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExt)
    androidTestImplementation(Dependencies.espresso)
}