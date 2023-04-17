object Dependencies {
    object Compose {
        const val composeVersion = "1.4.5"
        private const val composeBomVersion = "2023.04.00"

        const val bom = "androidx.compose:compose-bom:$composeBomVersion"

        const val ui = "androidx.compose.ui:ui"
        const val material = "androidx.compose.material:material"
        const val foundation = "androidx.compose.foundation:foundation"

        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val tooling = "androidx.compose.ui:ui-tooling"
        const val manifest = "androidx.compose.ui:ui-test-manifest"
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
        const val composeJunit = "androidx.compose.ui:ui-test-junit4:1.4.1"
    }
}