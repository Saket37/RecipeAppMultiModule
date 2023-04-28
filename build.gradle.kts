buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugins.androidPlugin)
        classpath(BuildPlugins.kotlinPlugin)
        classpath(BuildPlugins.hiltPlugin)
        classpath(BuildPlugins.navSafeArgs)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
