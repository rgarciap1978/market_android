plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mitocode.marketcomposeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mitocode.marketcomposeapp"
        minSdk = 24
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
            buildConfigField(
                "String", "URL_BASE", "\"http://35.169.242.154:3000/\""
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            buildConfigField(
                "String", "URL_BASE", "\"http://35.169.242.154:3000/\""
            )
            resValue("string", "title", "Debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Navigation componente compose
    implementation("androidx.navigation:navigation-compose:2.5.3")

    //Material Icons
    implementation("androidx.compose.material:material-icons-extended:1.2.0")

    // Retrofil
    val retrofil_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofil_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofil_version")

    // Coil
    implementation("io.coil-kt:coil-compose:2.0.0")

    // Dagger - Hilt
    val daggerHilt_version = "2.44"
    implementation("com.google.dagger:hilt-android:$daggerHilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$daggerHilt_version")

    val hilt_version = "1.0.0"
    kapt("androidx.hilt:hilt-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-common:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-beta01")

    //Security Crypto - SharedPreferences
    implementation("androidx.security:security-crypto:1.0.0-alpha02")

    //Room
    val room_version = "2.5.0"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    //Permission
    implementation("com.google.accompanist:accompanist-permissions:0.29.1-alpha")

    //CameraX
    val camerax_version = "1.3.0"
    implementation("androidx.camera:camera-view:$camerax_version")
    implementation("androidx.camera:camera-camera2:$camerax_version")
    implementation("androidx.camera:camera-lifecycle:$camerax_version")

    //Google Maps
    implementation("com.google.maps.android:maps-compose:1.0.0")
    implementation("com.google.android.gms:play-services-maps:18.0.2")

    // Work Manager
    val work_version = "2.7.1"

    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$work_version")

    // optional - RxJava2 support
    implementation("androidx.work:work-rxjava2:$work_version")

    // optional - GCMNetworkManager support
    implementation("androidx.work:work-gcm:$work_version")

    // optional - Test helpers
    androidTestImplementation("androidx.work:work-testing:$work_version")

    // optional - Multiprocess support
    implementation("androidx.work:work-multiprocess:$work_version")

    //Hilt-WorkManager
    implementation("androidx.hilt:hilt-work:1.0.0")

    //jwtdecode
    implementation("com.auth0.android:jwtdecode:2.0.1")
}