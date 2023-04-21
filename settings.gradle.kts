pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "RecipeApp"

// App
include(":app")

// Common
include(":common:theme")
include(":common:components")
include(":common:navigation")

// Features
include(":feature:recipes:presentation")
