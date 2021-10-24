package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import react.*


class TestBottomNavigation : RComponent<Props, State>() {
    private var value1: Any = 0 // Use default which is index position for value
    private var value2: Any = "recents" // Use a string just for the sake of it...

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Bottom Nav with showLabels = true") {
                bottomNavigation(value1, true) {
                    attrs.onChange = { _, indexValue -> setState { value1 = indexValue }}
                    bottomNavigationAction("Recents", buildElement { icon("restore") })
                    bottomNavigationAction("Favourites", buildElement { icon("favorite") })
                    bottomNavigationAction("Nearby", buildElement { icon("location_on") })
                }
            }
            demoPanel("Bottom Nav with showLabels = false (the default)") {
                bottomNavigation(value2) {
                    attrs.onChange = { _, indexValue -> setState { value2 = indexValue }}
                    bottomNavigationAction("Recents", buildElement { icon("restore") }, value = "recents")
                    bottomNavigationAction("Favourites", buildElement { icon("favorite") }, value = "favorite")
                    bottomNavigationAction("Nearby", buildElement { icon("location_on") }, value = "nearby")
                    bottomNavigationAction("Folder", buildElement { icon("folder") }, value = "folder")
                }
            }
        }
    }
}
