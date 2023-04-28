plugins {
    id("com.android.library")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.presentation"
    compileSdk = 33
    buildToolsVersion ="32.0.0"

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
    implementation(project(":home:data"))
    implementation(project(":home:domain"))
    implementation(project(":commons"))
    implementation(project(":recipeDetails:details_presentation"))
    implementation(Dependencies.Activity.activityKtx)
    implementation(Dependencies.Core.legacySupport)
    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    /*implementation("com.android.support:support-annotations:28.0.0")
    implementation ("com.android.support:exifinterface:28.0.0")

    implementation ("com.squareup.picasso:picasso:2.5.2")
    implementation("com.android.support:design:28.0.0") {
        exclude(group = "com.android.support", module = "support-annotations")
        exclude(group = "com.android.support", module = "exifinterface")

    }*/
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