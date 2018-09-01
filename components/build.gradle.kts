import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

val production: Boolean = (parent!!.properties["production"] as String ).toBoolean()

buildscript {
    var kotlinVersion: String by extra
    kotlinVersion = "1.2.61"

    repositories {
        jcenter()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    }

    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
    }
}

apply {
    plugin("kotlin2js")
}

// Not sure why this is needed, but it makes "compile(kotlinModule("stdlib-js", kotlinVersion))" line down below work
plugins {
    java
    id("com.moowork.node") version "1.2.0"
}

val kotlinVersion: String by extra

repositories {
    jcenter()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    maven { setUrl("http://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

dependencies {
    compile(kotlin("stdlib-js", kotlinVersion))
    compile("org.jetbrains", "kotlin-react", "16.4.2-pre.49-kotlin-1.2.60")
    compile("org.jetbrains", "kotlin-react-dom", "16.4.2-pre.49-kotlin-1.2.60")
    compile("org.jetbrains", "kotlin-styled", "1.0.0-pre.49-kotlin-1.2.60")
}


val compileKotlin2Js: Kotlin2JsCompile by tasks

compileKotlin2Js.kotlinOptions {
    sourceMap = true
    metaInfo = true
    outputFile = "${project.buildDir.path}/js/muirwik-components.js"
    main = "call"
    moduleKind = "commonjs"
}
