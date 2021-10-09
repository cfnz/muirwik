import kotlin.math.min

group = "com.ccfraser.muirwik"
version = "0.10.0"
description = "Test Application for Muirwik (a Material UI React wrapper written in Kotlin)"

plugins {
    kotlin("js") // Version needs to be specified in root project
}

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion = "1.5.31"
    val kotlinJsVersion = "pre.248-kotlin-$kotlinVersion"

    implementation(kotlin("stdlib-js", kotlinVersion))
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-styled", "5.3.1-$kotlinJsVersion")
    implementation(npm("react-hot-loader", "^4.12.20"))
    implementation(devNpm("webpack-bundle-analyzer", "^3.8.0"))

    implementation(project(":muirwik-components"))
}

kotlin {
    // See notes on the components project about which compiler to use...
//    js(IR) {
    js(LEGACY) {
//    js(BOTH) {
        useCommonJs()


//        compilations["main"].packageJson {
//            customField("resolutions2", mapOf("@mui/styled-engine" to "npm:@mui/styled-engine-sc@latest"))
//        }


        browser {
            binaries.executable()

            commonWebpackConfig {
                cssSupport.enabled = true
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }

        }
    }
}


tasks.register("webpackStatsFile") {
    description = "Creates a webpack stats file for webpack-bundle-analyzer to use, and shows this in a browser"

    /**
     * Windows and Linux/Mac has different executables for webpack, so this just centralises getting the name
     * of the executable depending on platform. We are just making an assumption that if the path separator is '\' then
     * we are on a windows OS, otherwise a Unix based OS (based on https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html).
     */
    fun appendCmdExtIfRequired(executableName: String) = executableName + if (File.separatorChar == '\\') ".cmd" else ""

    doLast {
        val createdWebPackFile = rootProject.buildDir.resolve("js/packages/${rootProject.name}-${project.name}/webpack.config.js")
        if (!createdWebPackFile.exists()) {
            throw IllegalStateException("Could not create stats file as can't find webpack config file.")
        } else {
            val outputFile = buildDir.resolve("reports/webpack/webpack-stats.json")
            val workingDirFile = rootProject.buildDir.resolve("js/node_modules/.bin")

            val execResult = exec {
                workingDir = workingDirFile
                standardOutput = outputFile.outputStream()
                commandLine = listOf(appendCmdExtIfRequired("./webpack-cli"), "--config", createdWebPackFile.toString(), "--profile", "--json")
            }
            if (execResult.exitValue == 0) {
                // The output seems to put a %complete before outputting the json, so we strip that from the file
                val filteredLines = outputFile.readLines().filter { !(it.substring(0, min(it.length, 5)).contains("%")) }
                outputFile.writeText(filteredLines.joinToString("\n"))
                exec {
                    workingDir = workingDirFile
                    commandLine = listOf(appendCmdExtIfRequired("./webpack-bundle-analyzer"),
                            outputFile.toString(), "-m", "static", "-r", outputFile.resolveSibling("webpack-stats-report.html").toString())
                }
            } else {
                println("webpack-cli stats creation exit value was not zero: ${execResult.exitValue}")
            }
        }
    }
}
