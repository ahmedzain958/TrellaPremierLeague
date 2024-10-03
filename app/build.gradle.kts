plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    kotlin("kapt")
}
android {
    namespace = "com.zainco.trellapremierleague"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zainco.trellapremierleague"
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            versionNameSuffix = "-test"
            val baseUrl: String by project
            val apiKey: String by project
            buildConfigField("String", "API_BASE", "\"$baseUrl\"")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.ktxCore)
    implementation(libs.androidLifecycle)
    implementation(libs.androidActivityCompose)
    implementation(platform(libs.composeBom))
    implementation(libs.composeUi)
    implementation(libs.composeGraphic)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeMaterial3)
    implementation(libs.materialIconsExtended)
    implementation(libs.viewmodelKtx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.extJunit)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(platform(libs.composeBom))
    androidTestImplementation(libs.junitComposetwo)
    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeTestManifest)
    implementation(libs.retrofitSqure)
    implementation(libs.retrofitConverter)
    implementation(libs.retrofitAdapter)
    implementation(libs.loggingInterceptor)
    implementation(libs.timber)
    implementation(libs.viewmodelSavedState)
    implementation(libs.lifecycleExt)
    annotationProcessor(libs.lifecycleCompiler)
    implementation(libs.constraintCompose)
    implementation(libs.coilCompose)
    implementation(libs.coilSvg)
    implementation(libs.hiltAndroid)
    annotationProcessor(libs.hiltCompiler)
    androidTestImplementation(libs.hiltTesting)
    kapt(libs.hiltCompiler)
    testImplementation(libs.hiltTesting)
    testAnnotationProcessor(libs.hiltCompiler)
    implementation(libs.datastore)
    kaptTest(libs.hiltKaptTesting)
    testAnnotationProcessor(libs.hiltKaptTesting)
    kaptAndroidTest(libs.hiltKaptTesting)
    androidTestAnnotationProcessor(libs.hiltKaptTesting)
    androidTestImplementation(libs.testRunner)
}