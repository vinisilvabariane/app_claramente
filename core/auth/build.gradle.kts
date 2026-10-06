plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.claramente.core.auth"
    compileSdk {
        version = release(37)
    }
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    api(project(":core:network"))
    api(libs.kotlinx.coroutines.android)
}
