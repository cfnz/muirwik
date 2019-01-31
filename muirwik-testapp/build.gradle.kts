import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import java.io.ByteArrayOutputStream

val production: Boolean = (properties["production"] as String).toBoolean()
//val production: Boolean by project

buildscript {
    var kotlinVersion: String by extra
    kotlinVersion = "1.3.20"

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
    if (production) plugin("kotlin-dce-js")
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

    compile("org.jetbrains", "kotlin-react", "16.6.0-pre.67-kotlin-${kotlinVersion}")
    compile("org.jetbrains", "kotlin-react-dom", "16.6.0-pre.67-kotlin-${kotlinVersion}")
    compile("org.jetbrains", "kotlin-styled", "1.0.0-pre.67-kotlin-${kotlinVersion}")

    compile(project(":muirwik-components"))
}

val compileKotlin2Js: Kotlin2JsCompile by tasks

compileKotlin2Js.kotlinOptions {
    sourceMap = true
    metaInfo = true
    freeCompilerArgs = listOf("-Xcoroutines=enable")
    outputFile = "${project.buildDir.path}/js/app.js"
    main = "call"
    moduleKind = "commonjs"
}

fun addInputsAndOutputs(exec: Exec) {
    // We uncomment this when we want to recreate even if Gradle thinks we don't need to
//    outputs.upToDateWhen { false }
    exec.inputs.file("yarn.lock")
    exec.inputs.file("webpack.config.js")
    exec.inputs.file("webpack.config.prod.js")
    exec.inputs.dir("$buildDir/js")
    exec.inputs.dir("$projectDir/src/main/resources/public")
//    exec.inputs.dir("node_modules")

    exec.outputs.dir("$buildDir/dist")
}

val webpackDev by tasks.creating(Exec::class) {
    group = "webpack"
    description = "Development build"

    addInputsAndOutputs(this)
    commandLine("$projectDir/node_modules/.bin/webpack-cli", "--config", "$projectDir/webpack.config.js")
}

val webpackProd by tasks.creating(Exec::class) {
    group = "webpack"
    description = "Production build"
    doFirst {
        if (!production) {
            error("Variable production == false and we are doing a production build")
        }
    }

    addInputsAndOutputs(this)
    commandLine("$projectDir/node_modules/.bin/webpack-cli", "-p", "--config", "$projectDir/webpack.config.prod.js")
}

val webpackDevServer by tasks.creating(Exec::class) {
    group = "webpack"
    description = "Development server (doesn't open a new browser window)"

    addInputsAndOutputs(this)
    commandLine("$projectDir/node_modules/.bin/webpack-dev-server", "--hot")
}

val webpackDevServerOpenBrowser by tasks.creating(Exec::class) {
    group = "webpack"
    description = "Development server which opens a new browser window"

    addInputsAndOutputs(this)
    commandLine("$projectDir/node_modules/.bin/webpack-dev-server", "--hot", "--open")
}

val webpackDevServerPublic by tasks.creating(Exec::class) {
    group = "webpack"
    description = "Development server which allows other PCs to navigate to this PC and view the webapp (i.e. browser does not have " +
            "to be localhost, just use this PCs ip address and probably port 8080 (e.g. something like 192.168.0.123:8080))"

    addInputsAndOutputs(this)
    commandLine("$projectDir/node_modules/.bin/webpack-dev-server", "--hot", "--open", "--host", "0.0.0.0")
}

val webpackDevServerProdConfig by tasks.creating(Exec::class) {
    group = "webpack"
    description = "Though not the usual case, this starts the development server but with a production build"

    addInputsAndOutputs(this)
    commandLine("$projectDir/node_modules/.bin/webpack-dev-server", "-p", "--config", "$projectDir/webpack.config.prod.js")
}

val webpackStats by tasks.creating(Exec::class) {
    group = "webpack"
    description = "Does a webpack build with statics output ready for something like webpack-bundle-analyzer to analyse"

    addInputsAndOutputs(this)
    commandLine("$projectDir/node_modules/.bin/webpack-cli", "-p", "--config", "$projectDir/webpack.config.prod.js", "--profile", "--json")

    standardOutput = ByteArrayOutputStream()
    doLast {
        File("$projectDir/build/stats.prod.json").writeText(standardOutput.toString())
    }
}

val webpackStatsAnalyser by tasks.creating(Exec::class) {
    group = "webpack"
    description = "Assumes that webpack-bundle-analyzer has been installed in npm globally and that the webpackStats task has been run. " +
            "This simply calls the command line with the output of the previous webpackStats task output."

    commandLine("webpack-bundle-analyzer", "$projectDir/build/stats.prod.json", "$projectDir/dist",
            "--mode", "static", "--report", "$projectDir/build/report.html")
}

val copyResources by tasks.creating {
    group = "build"
    description = "Assemble resources part of the web application"

    outputs.dir("$projectDir/dist")

    doLast {
        println("copyResources")
        copy {
            from("$projectDir/src/main/resources/public")
            into("$projectDir/dist")
        }
    }
}

val cleanDist by tasks.creating {
    group = "build"
    description = "Cleans files placed in the dist folder by part of the build process"

    doLast {
        delete("$projectDir/dist")
    }
}

tasks["assemble"].dependsOn(copyResources)
//tasks["assemble"].dependsOn(copyLibJsFiles)
tasks["clean"].dependsOn(cleanDist)

if (production) {
    val build by tasks
//    val bundle by tasks
    val webpackDev by tasks
    val webpackProd by tasks
    val webpackDevServerProdConfig by tasks

    build.dependsOn("runDceKotlinJs")
//    bundle.dependsOn("runDceKotlinJs")
    webpackDev.dependsOn("runDceKotlinJs")
    webpackProd.dependsOn("runDceKotlinJs")
    webpackDevServerProdConfig.dependsOn("runDceKotlinJs")
}