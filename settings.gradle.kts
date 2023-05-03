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
include(":common:network")

// Features

// Recipes
include(":feature:recipes:presentation")
include(":feature:recipes:domain")
include(":feature:recipes:data")

// Recipe adding
include(":feature:recipe_adding:presentation")
