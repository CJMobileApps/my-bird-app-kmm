plugins {
    // kotlin("multiplatform")
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    //alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    //alias(libs.plugins.kotlinMultiplatform)
//    (libs.plugins.kotlinMultiplatform)
//    kotlin("multiplatform") version "2.0.0"
}


kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(libs.androidx.activity.compose)
            }
        }
    }
}

android {
   // compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    compileSdk = 34
    defaultConfig {
        applicationId = "com.myapplication.MyApplication" //todo update this
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
