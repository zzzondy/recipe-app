plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
}

android {
    namespace = "com.recipeapp.presentation"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.composeVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Modules
    implementation(project(Modules.commonTheme))
    implementation(project(Modules.commonComponents))
    implementation(project(Modules.commonNavigation))

    implementation(project(Modules.featureRecipesDomain))
    implementation(project(Modules.featureRecipesData))

    // Compose BOM
    implementation(platform(Dependencies.Compose.bom))

    // Compose UI
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.material)

    // Lifecycle
    implementation(Dependencies.Lifecycle.composeActivity)

    // Navigation
    implementation(Dependencies.Compose.navigation)

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.compiler)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
}