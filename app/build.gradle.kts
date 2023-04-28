plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id ("kotlin-parcelize")

}

android {
    namespace = ConfigurationData.applicationId
    compileSdk = ConfigurationData.compileSdk
    buildToolsVersion = "32.0.0"

    defaultConfig {
        applicationId = ConfigurationData.applicationId
        minSdk = ConfigurationData.minSdk
        targetSdk = ConfigurationData.targetSdk
        versionCode = ConfigurationData.versionCode
        versionName = ConfigurationData.versionName

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    implementation(project(":home:presentation"))
    implementation(project(":commons"))
    implementation(project(":home:domain"))
    implementation(project(":home:data"))
    implementation(project(":recipeDetails:details_data"))
    implementation(project(":recipeDetails:details_domain"))
    implementation(project(":recipeDetails:details_presentation"))
    implementation(Dependencies.Core.legacySupport)
    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.hiltCompiler)
    implementation(Dependencies.Navigation.navigationUi)
//Room
    implementation(Dependencies.RoomDB.roomCompiler)
    kapt(Dependencies.RoomDB.roomKapt)
    implementation(Dependencies.RoomDB.room)


    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExt)
    androidTestImplementation(Dependencies.espresso)
}