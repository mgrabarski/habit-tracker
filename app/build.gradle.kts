plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version Versions.ksp
}

android {
    namespace = "com.mateuszgrabarski.habittracker"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.mateuszgrabarski.habittracker"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Java.version
        targetCompatibility = Java.version
    }
    kotlinOptions {
        jvmTarget = Java.version.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {

    implementation(AndroidDependencies.core)
    implementation(AndroidDependencies.lifecycleRuntime)
    implementation(AndroidDependencies.activityCompose)
    implementation(AndroidDependencies.composeUi)
    implementation(AndroidDependencies.composeToolingPreview)
    implementation(AndroidDependencies.material)
    implementation(AndroidDependencies.destinations)
    ksp(AndroidDependencies.destinationsKsp)

    debugImplementation(AndroidDependencies.composeUiTooling)
    debugImplementation(AndroidDependencies.composeTestManifest)

    testImplementation(UnitTestDependencies.kotestCore)
    testImplementation(UnitTestDependencies.kotestRunner)

    androidTestImplementation(IntegrationTestDependencies.androidJunit)
    androidTestImplementation(IntegrationTestDependencies.espressoCore)
    androidTestImplementation(IntegrationTestDependencies.composeTestJunit)
}
