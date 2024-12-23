import org.jetbrains.kotlin.konan.target.Family

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).filter { it.konanTarget.family == Family.IOS }
        .forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "shared"
                isStatic = true
            }
        }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            with(compose) {
                implementation(ui)
                implementation(foundation)
                implementation(material)
                implementation(material3)
                implementation(runtime)
                implementation(components.resources)
            }

            // Image loader
            implementation(libs.kamel.image.default)

            // Ktor for networking
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization)

            // JSON serialization
            // TODO use gson instead one day
            implementation(libs.kotlinx.serialization.json) // Compatible with Kotlin 1.9.0

            // https://github.com/icerockdev/moko-mvvm
            implementation("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
            implementation("dev.icerock.moko:mvvm-compose:0.16.1") // api mvvm-core, getViewModel for Compose Multiplatform

            // needed for koin
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)
        }

        val androidMain by getting {
            dependencies {
                // Ktor for Android
                implementation(libs.ktor.client.android)

                // learning koin
                api(libs.koin.android)
                api(libs.koin.androidx.compose)
                api(libs.androidx.activity.compose)

            }
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.cjmobileapps.quidditch_players_kmm_2024"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
