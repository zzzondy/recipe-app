buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.Serialization.kotlinSerialization)
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    id(Plugins.androidApplication) version Plugins.androidApplicationVersion apply false
    id(Plugins.androidLibrary) version Plugins.androidLibraryVersion apply false
    id(Plugins.kotlinAndroid) version Plugins.kotlinVersion apply false
    id(Plugins.kotlinJvm) version Plugins.kotlinJvmVersion apply false
}