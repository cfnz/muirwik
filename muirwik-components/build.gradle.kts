group = "com.ccfraser.muirwik"
version = "0.10.1"
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
    val kotlinVersion = "1.6.10"
    val kotlinJsVersion = "pre.290-kotlin-$kotlinVersion"
    val kotlinReactVersion = "17.0.2-$kotlinJsVersion"

    implementation(kotlin("stdlib-js", kotlinVersion))
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-react-legacy", kotlinReactVersion)
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-react-dom-legacy", kotlinReactVersion)
    implementation("org.jetbrains.kotlin-wrappers", "kotlin-styled", "5.3.3-$kotlinJsVersion")

    implementation(npm("@mui/material", "5.0.3"))
    implementation(npm("@mui/icons-material", "5.0.3"))

    // For styling with Styled Components
    implementation(npm("@mui/styled-engine-sc", "5.0.3"))
    implementation(npm("styled-components", "5.3.1"))
    implementation(npm("@mui/styled-engine", "npm:@mui/styled-engine-sc@5.0.3"))

    // For styling with Emotion
//    implementation(npm("@emotion/react", "11.4.1"))
//    implementation(npm("@emotion/styled","11.3.0"))

    // This is for legacy styling
    implementation(npm("@mui/styles", "5.0.1"))

    implementation(npm("@mui/lab", "5.0.0-alpha.48"))
}



kotlin {
    // Everything should now work with the IR compiler. However, at time of writing, the IR compiler is still
    // alpha and does not support incremental compilation, so while the IR compiler should work, for development
    // purposes, the Legacy compiler is more productive.
    // For releases, we will try and use BOTH
//    js(IR) {
//    js(BOTH) {
    js(LEGACY) {
        useCommonJs()

//        compilations["main"].packageJson {
//            customField("resolutions", mapOf("@mui/styled-engine" to "npm:@mui/styled-engine-sc@5.0.3"))
//        }

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

