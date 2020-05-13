rootProject.name = "muirwik"

pluginManagement {
    resolutionStrategy {
        plugins {
//            val kotlinVersion = extra["kotlin.version"] as String
            val kotlinVersion = "1.3.72"
            kotlin("multiplatform") version kotlinVersion
            kotlin("jvm") version kotlinVersion
            kotlin("js") version kotlinVersion
            kotlin("plugin.serialization") version kotlinVersion
        }
    }

    repositories {
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

include("muirwik-components")
include("muirwik-testapp")
