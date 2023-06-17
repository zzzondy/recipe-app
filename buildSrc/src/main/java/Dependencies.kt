object Dependencies {
    object Compose {
        const val composeVersion = "1.4.5"
        private const val composeBomVersion = "2023.04.00"

        const val bom = "androidx.compose:compose-bom:$composeBomVersion"

        const val ui = "androidx.compose.ui:ui"
        const val material = "androidx.compose.material:material"
        const val foundation = "androidx.compose.foundation:foundation"
        const val runtime = "androidx.compose.runtime:runtime"

        const val navigation = "androidx.navigation:navigation-compose:2.5.3"

        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha04"

        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val tooling = "androidx.compose.ui:ui-tooling"
    }

    object Lifecycle {
        const val core = "androidx.core:core-ktx:1.10.0"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
        const val composeActivity = "androidx.activity:activity-compose:1.7.0"
    }

    object Testing {
        const val junit = "junit:junit:4.13.2"
        const val androidJunit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4:1.4.3"
        const val manifest = "androidx.compose.ui:ui-test-manifest"
    }

    object Dagger {
        private const val daggerVersion = "2.45"

        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object Accompanist {
        private const val accompanistVersion = "0.31.2-alpha"

        const val animationNavigation =
            "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion"

        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"

        const val placeholders =
            "com.google.accompanist:accompanist-placeholder-material:$accompanistVersion"

    }

    object Serialization {
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:1.8.10"
        const val kotlinSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"
    }

    object OkHttp3 {
        private const val okHttpVersion = "4.9.0"

        const val okhttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val serialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0"
    }

    object Paging {
        private const val pagingVersion = "3.1.1"

        const val common = "androidx.paging:paging-common-ktx:$pagingVersion"
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha17"
    }

    object FlowRedux {
        private const val flowReduxVersion = "1.0.2"

        const val flowRedux = "com.freeletics.flowredux:flowredux-jvm:$flowReduxVersion"
    }

    object Landscapist {
        private const val landscapistVersion = "2.1.0"

        const val bom = "com.github.skydoves:landscapist-bom:$landscapistVersion"
        const val coil = "com.github.skydoves:landscapist-coil"
        const val animation = "com.github.skydoves:landscapist-animation"
    }

    object WheelPicker {
        const val wheelPicker = "com.github.zj565061763:compose-wheel-picker:1.0.0-alpha16"
    }
}