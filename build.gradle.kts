allprojects {
    group = "com.ccfraser.muirwik"
    version = "1.0-SNAPSHOT"

    repositories {
        jcenter()
    }
}

plugins {
    base
}

dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}