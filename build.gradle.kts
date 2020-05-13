
plugins {
    base
    kotlin("js") apply false
}

allprojects {
    group = "com.ccfraser.muirwik"
    version = "1.0-SNAPSHOT"

    repositories {
        jcenter()
    }
}

dependencies {
    // Make the root project archives configuration depend on every sub-project
    subprojects.forEach {
        archives(it)
    }
}