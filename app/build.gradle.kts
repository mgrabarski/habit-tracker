import Libraries.androidTests
import Libraries.baseDependencies
import Libraries.uiAndCompose

plugins {
    androidApp()
    kotlinAndroid()
    ksp()
}

android {
    setAppConfig()

    flavorDimensions += AppProductFlavours.flavorDimensions

    productFlavors {
        create(AppProductFlavours.production) {
            namespace = ApplicationId.production
            applicationId = ApplicationId.production
        }
        create(AppProductFlavours.stage) {
            namespace = ApplicationId.stage
            applicationId = ApplicationId.stage
        }
        create(AppProductFlavours.dev) {
            namespace = ApplicationId.dev
            applicationId = ApplicationId.dev
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    kotlinOptions {
        jvmTarget = Java.version.toString()
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    baseDependencies()

    implementation(AndroidDependencies.core)
    uiAndCompose()

    implementation(project(Modules.resources))

    androidTests()
}
