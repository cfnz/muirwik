import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import java.io.ByteArrayOutputStream

val production: Boolean = (parent!!.properties["production"] as String ).toBoolean()
//val production: Boolean by project

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
    if (production) plugin("kotlin-dce-js")
}

// Not sure why this is needed, but it makes "compile(...)" lines down below work
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
    compile(project(":components"))
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

/**
 * Common things we do before running the webpack command line in the tasks below
 */
fun doBeforeWebpack(exec: Exec) {
    // We uncomment this when we want to recreate even if Gradle thinks we don't need to
//    outputs.upToDateWhen { false }

    exec.inputs.file("yarn.lock")
    exec.inputs.file("webpack.config.js")  // NOTE: Add inputs.file("webpack.config.js") for projects that have it
    exec.inputs.file("webpack.config.prod.js")
    exec.inputs.dir("$buildDir/js")
    exec.inputs.dir("$projectDir/src/main/resource/public")
//    exec.inputs.dir("node_modules")

    exec.outputs.dir("$buildDir/dist")

    copy {
        from("$projectDir/src/main/resources/public")
        into("$projectDir/dist")
    }
}

/**
 * Development build
 */
val webpack by tasks.creating(Exec::class) {
    doBeforeWebpack(this)
    commandLine("$projectDir/node_modules/.bin/webpack-cli", "--config", "$projectDir/webpack.config.js")
}

/**
 * Production build
 */
val webpackProd by tasks.creating(Exec::class) {
    doFirst {
        if (!production) {
            error("Variable production == false and we are doing a production build")
        }
    }
    doBeforeWebpack(this)
    commandLine("$projectDir/node_modules/.bin/webpack-cli", "-p", "--config", "$projectDir/webpack.config.prod.js")
}

/**
 * Development server (doesn't open a new browser window)
 */
val webpackDevServer by tasks.creating(Exec::class) {
    doBeforeWebpack(this)
    commandLine("$projectDir/node_modules/.bin/webpack-dev-server", "--hot")
}

/**
 * Development server which opens a new browser window
 */
val webpackDevServerOpenBrowser by tasks.creating(Exec::class) {
    doBeforeWebpack(this)
    commandLine("$projectDir/node_modules/.bin/webpack-dev-server", "--hot", "--open")
}

/**
 *  Development server which allows other PCs to navigate to this PC and view the webapp (i.e. browser does not have
 *  to be localhost, just use this PCs ip address and probably port 8080 (e.g. something like 192.168.0.123:8080))
 */
val webpackDevServerPublic by tasks.creating(Exec::class) {
    doBeforeWebpack(this)
    commandLine("$projectDir/node_modules/.bin/webpack-dev-server", "--hot", "--open", "--host", "0.0.0.0")
}

/**
 *  Though not the usual case, this starts the development server but with a production build
 */
val webpackDevServerProdConfig by tasks.creating(Exec::class) {
    doBeforeWebpack(this)
    commandLine("$projectDir/node_modules/.bin/webpack-dev-server", "-p", "--config", "$projectDir/webpack.config.prod.js")
}

/**
 *  Does a webpack build with statics output ready for something like webpack-bundle-analyzer to analyse.
 */
val webpackStats by tasks.creating(Exec::class) {
    doBeforeWebpack(this)

    commandLine("$projectDir/node_modules/.bin/webpack-cli", "-p", "--config", "$projectDir/webpack.config.prod.js", "--profile", "--json")

    standardOutput = ByteArrayOutputStream()
    doLast {
        File("$projectDir/build/stats.prod.json").writeText(standardOutput.toString())
    }
}

/**
 *  Assumes that webpack-bundle-analyzer has been installed in npm globally and that the webpackStats task has been run.
 *  This simply calls the command line with the output of the previous webpackStats task output.
 */
val webpackStatsAnalyser by tasks.creating(Exec::class) {
    commandLine("webpack-bundle-analyzer", "$projectDir/build/stats.prod.json", "$projectDir/dist",
            "--mode", "static", "--report", "$projectDir/build/report.html")
}

if (production) {
    val build by tasks
//    val bundle by tasks
    val webpack by tasks
    val webpackProd by tasks
    val webpackDevServerProdConfig by tasks

    build.dependsOn("runDceKotlinJs")
//    bundle.dependsOn("runDceKotlinJs")
    webpack.dependsOn("runDceKotlinJs")
    webpackProd.dependsOn("runDceKotlinJs")
    webpackDevServerProdConfig.dependsOn("runDceKotlinJs")
}