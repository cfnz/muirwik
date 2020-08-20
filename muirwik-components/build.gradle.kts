import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType

version = "0.6.0"
description = "Muirwik Components - a Material UI React wrapper written in Kotlin"

plugins {
    id("org.jetbrains.kotlin.js") // Version needs to be specified in root project
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://dl.bintray.com/kotlin/kotlin-dev")
    maven("http://dl.bintray.com/kotlin/kotlin-js-wrappers")
}

dependencies {
    val kotlinVersion = "1.4.0"
    val kotlinJsVersion = "pre.111-kotlin-$kotlinVersion"
    val kotlinReactVersion = "16.13.1-$kotlinJsVersion"

    implementation(kotlin("stdlib-js", kotlinVersion))
    implementation("org.jetbrains", "kotlin-react", kotlinReactVersion)
    implementation("org.jetbrains", "kotlin-react-dom", kotlinReactVersion)
    implementation("org.jetbrains", "kotlin-styled", "1.0.0-$kotlinJsVersion")


    // Comment might not apply now???

    // We don't have peer dependencies and projects using this project via gradle fail to run
    // if we have the dependencies listed below...
    // So, the user of this project needs to include the material-ui dependencies themselves
    // and be careful to select the correct version!
//    implementation(peerNpm("@material-ui/core", "^4.11.0"))
//    implementation(peerNpm("@material-ui/icons", "^4.9.1"))
//    implementation(peerNpm("@material-ui/core", "^4.9.14"))
//    implementation(peerNpm("@material-ui/icons", "^4.9.1"))
}



kotlin {
//    defaultJsCompilerType = KotlinJsCompilerType.LEGACY
    defaultJsCompilerType = KotlinJsCompilerType.IR
//        defaultJsCompilerType = KotlinJsCompilerType.BOTH

    js {
        browser {
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
//        binaries.executable()

        useCommonJs()
//        nodejs {
//        }
    }
}

// TODO: Look at javadoc/kdoc/dokka
//val dokka: DokkaTask by tasks
//tasks.register<Jar>("KDocJar") {
//    from(tasks.javadoc)
//    classifier = "javadoc"
//    from(tasks.dokka)
//}
//
//val publicationName = "kotlin"
//publishing {
//    repositories {
//        mavenLocal()
//    }
//
//    publications {
//        create<MavenPublication>(publicationName) {
//            from(components["kotlin"])
////            artifact(tasks["KDocJar"])
//            artifact(tasks.getByName<Zip>("JsSourcesJar"))
//
//            pom {
//                name.set("Muirwik Components")
//                description.set(project.description)
//                url.set("https://github.com/cfnz/muirwik")
//                licenses {
//                    license {
//                        name.set("Mozilla Public License 2.0")
//                    }
//                }
//                scm {
//                    connection.set("https://github.com/cfnz/muirwik.git")
//                    url.set("https://github.com/cfnz/muirwik")
//                }
//            }
//        }
//    }
//}
//
//bintray {
//    // Bintray keys are kept in a local, non version controlled, properties file
//    if (project.file("local.properties").exists()) {
//        val properties = Properties()
//        properties.load(FileInputStream(project.file("local.properties")))
//        fun findProperty(propertyName: String) = properties[propertyName] as String?
//
//        user = findProperty("bintray.user")
//        key = findProperty("bintray.apikey")
//        publish = true
//        override = true
//
//        pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
//            // Mandatory fields
//            repo = project.parent?.name
//            name = "${project.group}:${project.name}"
//            setLicenses("MPL-2.0")
//            vcsUrl = "https://github.com/cfnz/muirwik"
//
//            // Some optional fields
//            description = project.description
//            desc = description
//            websiteUrl = "https://github.com/cfnz/muirwik"
//            issueTrackerUrl = "https://github.com/cfnz/muirwik/issues"
//            githubRepo = "https://github.com/cfnz/muirwik"
//            setLabels("kotlin", "material-ui", "react")
//        })
//        setPublications(publicationName)
//    }
//}

