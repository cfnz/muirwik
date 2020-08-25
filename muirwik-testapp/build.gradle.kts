import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType

group = "com.ccfraser.muirwik"
description = "Test Application for Muirwik (a Material UI React wrapper written in Kotlin)"

plugins {
    id("org.jetbrains.kotlin.js") // Version needs to be specified in root project
//    id("kotlin-1.3-compat")
}

repositories {
    jcenter()
    mavenLocal()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    maven { setUrl("http://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

dependencies {
    val kotlinVersion = "1.4.0"
    val kotlinJsVersion = "pre.112-kotlin-$kotlinVersion"
    val kotlinReactVersion = "16.13.1-$kotlinJsVersion"

    implementation(kotlin("stdlib-js", kotlinVersion))

    implementation("org.jetbrains", "kotlin-react", kotlinReactVersion)
    implementation("org.jetbrains", "kotlin-react-dom", kotlinReactVersion)
    implementation("org.jetbrains", "kotlin-styled", "1.0.0-$kotlinJsVersion")
    implementation(npm("@material-ui/core", "^4.11.0"))
    implementation(npm("@material-ui/icons", "^4.9.1"))
    implementation(npm("react-hot-loader", "^4.12.20"))

    // Just adding these to get rid of warnings...
    implementation(npm("react", "^16.3.1"))
    implementation(npm("react-dom", "^16.3.1"))


    implementation(project(":muirwik-components"))
}

kotlin {
    println("defaultJsCompileType is ${defaultJsCompilerType}")
    defaultJsCompilerType = KotlinJsCompilerType.LEGACY
//        defaultJsCompilerType = KotlinJsCompilerType.IR
//        defaultJsCompilerType = KotlinJsCompilerType.BOTH

    js {
        browser {
            useCommonJs()

            webpackTask {
                cssSupport.enabled = true
            }

            runTask {
                cssSupport.enabled = true
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        binaries.executable()
    }
}

