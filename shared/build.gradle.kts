import org.jetbrains.kotlin.konan.target.Family

//plugins {
//    kotlin("multiplatform")
//    id("com.android.library")
//    id("org.jetbrains.compose")
//    kotlin("plugin.serialization") version "1.9.21"
//}
//
//kotlin {
//    androidTarget()
//
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//        }
//    }
//
//    sourceSets {
//        val commonMain by getting {
//            dependencies {
//                implementation(compose.runtime)
//                implementation(compose.foundation)
//                implementation(compose.material)
//                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
//                implementation(compose.components.resources)
//
//                // Image loading library
//                implementation("media.kamel:kamel-image-default:1.0.0")
//
//                // Ktor for networking
//                implementation("io.ktor:ktor-client-core:2.3.1")
//                implementation("io.ktor:ktor-client-content-negotiation:2.3.1")
//                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.1")
//
//                // JSON serialization
//                // TODO use gson instead one day
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1") // Compatible with Kotlin 1.9.0
////                implementation("io.ktor:ktor-serialization-gson:1.5.1")
//            }
//        }
//        val androidMain by getting {
//            dependencies {
//                api("androidx.activity:activity-compose:1.7.2")
//                api("androidx.appcompat:appcompat:1.6.1")
//                api("androidx.core:core-ktx:1.10.1")
//
//                // Ktor for Android
//                implementation("io.ktor:ktor-client-android:2.3.1")
//            }
//        }
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by creating {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//
//            dependencies {
//                implementation("io.ktor:ktor-client-darwin:2.3.1")
//            }
//        }
//    }
//}

//android {
//    compileSdk = (findProperty("android.compileSdk") as String).toInt()
//    namespace = "com.myapplication.common"
//
//    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
//    sourceSets["main"].res.srcDirs("src/androidMain/res")
//    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
//
//    defaultConfig {
//        minSdk = (findProperty("android.minSdk") as String).toInt()
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_17
//        targetCompatibility = JavaVersion.VERSION_17
//    }
//    kotlin {
//        jvmToolchain(17)
//    }
//}


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

//    macosX64 {
//        binaries {
//            executable {
//                entryPoint = "main"
//            }
//        }
//    }
//    macosArm64 {
//        binaries {
//            executable {
//                entryPoint = "main"
//            }
//        }
//    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).filter { it.konanTarget.family == Family.IOS }
        .forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "shared"
                isStatic = true
                with(libs) {
//                    export(bundles.decompose)
//                    export(essenty.lifecycle)
                }
            }
        }

//    jvm("desktop")
//    js(IR) {
//        browser()
//    }

    applyDefaultHierarchyTemplate()

    /*   cocoapods {
           summary = "Some description for the Shared Module"
           homepage = "Link to the Shared Module homepage"
           version = "1.0"
           ios.deploymentTarget = "14.1"
           podfile = project.file("../iosApp/Podfile")
       }*/

    sourceSets {
//        val desktopMain by getting

        commonMain.dependencies {
            with(compose) {
                implementation(ui)
                implementation(foundation)
                implementation(material)
                implementation(material3)
                implementation(runtime)
                implementation(components.resources)


            }

            with(libs) {
//                //implementation(kotlinx.  .serialization.json)
//                implementation(bundles.ktor)
//                api(bundles.decompose)
//                implementation(image.loader)
//                implementation(essenty.lifecycle)
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
        }

        androidMain.dependencies {
//                implementation(libs.androidx.media3.exoplayer)
            // Ktor for Android
            implementation(libs.ktor.client.android)
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
//    kotlinOptions {
//        jvmTarget = "17"
//    }
    kotlin {
        jvmToolchain(17)
    }
}

