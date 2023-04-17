buildscript {
    repositories {
        mavenCentral()
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
}