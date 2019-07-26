import com.ccfraser.gradle.GradleWebpackPluginSettings
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJsDce

val productionConfig: Boolean = (properties["production"] as String).toBoolean()
//val production: Boolean by project

version = "0.2.2"
description = "Test Application for Muirwik (a Material UI React wrapper written in Kotlin)"

buildscript {
    var kotlinVersion: String by extra
    kotlinVersion = "1.3.41"

    repositories {
        jcenter()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
        maven { setUrl("https://dl.bintray.com/cfraser/gradle-webpack-plugin") }
    }

    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
//        classpath(kotlin("frontend-plugin", "0.0.45"))
        classpath("com.ccfraser.gradle:gradle-webpack-plugin:0.1")
    }
}

apply {
    plugin("kotlin2js")
    plugin("kotlin-dce-js")
//    plugin("org.jetbrains.kotlin.frontend")
    plugin("com.ccfraser.gradle.gradle-webpack-plugin")
}

plugins {
    java // Not sure why this is needed, but it makes the dependencies below work.
    id("com.moowork.node") version "1.2.0"
}

val kotlinVersion: String by extra

repositories {
    jcenter()
    mavenLocal()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    maven { setUrl("http://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

dependencies {
    compile(kotlin("stdlib-js", kotlinVersion))

    compile("org.jetbrains", "kotlin-react", "16.6.0-pre.78-kotlin-$kotlinVersion")
    compile("org.jetbrains", "kotlin-react-dom", "16.6.0-pre.78-kotlin-$kotlinVersion")
    compile("org.jetbrains", "kotlin-styled", "1.0.0-pre.78-kotlin-$kotlinVersion")

    compile(project(":muirwik-components"))
}

val compileKotlin2Js: Kotlin2JsCompile by tasks
compileKotlin2Js.kotlinOptions {
    sourceMap = true
    if (!productionConfig) {
        sourceMapEmbedSources = "always"
    }
    metaInfo = true
    outputFile = "${project.buildDir.path}/js/app.js"
    main = "call"
    moduleKind = "commonjs"
}

val runDceKotlinJs: KotlinJsDce by tasks
runDceKotlinJs.apply {
    // Turns out that when devMode is true, it still copies all the required js modules but does not strip any
    // code from them... just what we were doing with our copyJsForBundle task!
    dceOptions.devMode = !productionConfig
    dceOptions.outputDirectory = "${buildDir}/js-for-bundle"
    keep.add("kotlin.defineModule")
}

configure<GradleWebpackPluginSettings> {
    production = productionConfig
}
