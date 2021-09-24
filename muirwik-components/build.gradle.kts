group = "com.ccfraser.muirwik"
version = "0.9.2"
description = "Muirwik Components - a Material UI React wrapper written in Kotlin"

plugins {
    kotlin("js") // Version needs to be specified in root project
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion = "1.5.31"
    val kotlinJsVersion = "pre.248-kotlin-$kotlinVersion"
    val kotlinReactVersion = "17.0.2-$kotlinJsVersion"

    implementation(kotlin("stdlib-js", kotlinVersion))
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-react", kotlinReactVersion)
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-react-dom", kotlinReactVersion)
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-styled", "5.3.1-$kotlinJsVersion")

    implementation(npm("@material-ui/core", "4.11.4"))
    implementation(npm("@material-ui/lab", "4.0.0-alpha.57"))
    implementation(npm("@material-ui/icons", "4.11.2"))
}

kotlin {
    // Everything should now work with the IR compiler. However, at time of writing, the IR compiler is still
    // alpha and does not support incremental compilation, so while the IR compiler should work, for development
    // purposes, the Legacy compiler is more productive.
    // For releases, we will try and use BOTH
//    js(IR) {
    js(BOTH) {
//    js(LEGACY) {
        useCommonJs()
        browser {
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

// TODO: Look at javadoc/kdoc/dokka
//val dokka: DokkaTask by tasks
//tasks.register<Jar>("KDocJar") {
//    from(tasks.javadoc)
//    classifier = "javadoc"
//    from(tasks.dokka)
//}

val publicationName = "kotlin"
publishing {
    repositories {
        mavenLocal()
        maven {
            name = "sonatype"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = extra["ossrhUsername"]?.toString()
                password = extra["ossrhPassword"]?.toString()
            }
        }
    }

    publications {
        create<MavenPublication>(publicationName) {
            from(components["kotlin"])
//            artifact(tasks["KDocJar"])
            if (tasks.names.contains("jsSourcesJar")) { artifact(tasks.getByName<Zip>("jsSourcesJar")) }
            if (tasks.names.contains("jsIrSourcesJar")) { artifact(tasks.getByName<Zip>("jsIrSourcesJar")) }

            pom {
                name.set("Muirwik Components")
                description.set(project.description)
                url.set("https://github.com/cfnz/muirwik")
                licenses {
                    license {
                        name.set("Mozilla Public License 2.0")
                    }
                }
                scm {
                    connection.set("https://github.com/cfnz/muirwik.git")
                    url.set("https://github.com/cfnz/muirwik")
                }
                developers {
                    developer {
                        name.set("cfnz")
                        organizationUrl.set("https://github.com/cfnz")
                    }
                }
            }
        }
        signing {
            sign(publishing.publications[publicationName])
        }
    }
}

