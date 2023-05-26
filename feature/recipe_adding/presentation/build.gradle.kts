plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
}

android {
    namespace = "com.recipe_adding.presentation"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        signingConfig = signingConfigs.getByName("debug")
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
    implementation(project(Modules.commonComponents))
    implementation(project(Modules.commonTheme))
    implementation(project(Modules.commonNavigation))

    implementation(project(Modules.featureRecipeAddingDomain))

    // Compose BOM
    implementation(platform(Dependencies.Compose.bom))

    // Compose UI
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.constraintLayout)

    // Compose tooling
    implementation(Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.toolingPreview)

    // FlowRedux
    implementation(Dependencies.FlowRedux.flowRedux)

    // Navigation
    implementation(Dependencies.Compose.navigation)

    // Accompanist
    implementation(Dependencies.Accompanist.animationNavigation)
    implementation(Dependencies.Accompanist.placeholders)

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.compiler)

    // Landscapist
    implementation(Dependencies.Landscapist.bom)
    implementation(Dependencies.Landscapist.coil)

    // Wheel picker
    implementation(Dependencies.WheelPicker.wheelPicker)
}