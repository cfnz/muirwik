
version = "0.5.2"
description = "Test Application for Muirwik (a Material UI React wrapper written in Kotlin)"

plugins {
    kotlin("js")
}

repositories {
    jcenter()
    mavenLocal()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    maven { setUrl("http://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

dependencies {
    val kotlinVersion = "1.3.72"
    val kotlinJsVersion = "pre.104-kotlin-$kotlinVersion"
    val kotlinReactVersion = "16.13.1-$kotlinJsVersion"

    implementation(kotlin("stdlib-js", kotlinVersion))

    implementation("org.jetbrains", "kotlin-react", kotlinReactVersion)
    implementation("org.jetbrains", "kotlin-react-dom", kotlinReactVersion)
    implementation("org.jetbrains", "kotlin-styled", "1.0.0-$kotlinJsVersion")
    implementation(npm("@material-ui/core", "^4.9.14"))
    implementation(npm("@material-ui/icons", "^4.9.1"))
    implementation(npm("react-hot-loader", "^4.12.20"))

    implementation(project(":muirwik-components"))
}

kotlin {
    target {
        browser {
            useCommonJs()
        }
    }
}
