package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.mBottomNavigation
import com.ccfraser.muirwik.components.mBottomNavigationAction
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mTypography
import react.*
import react.dom.br


class TestBottomNavigation : RComponent<Props, State>() {
    private var value1: Any = 0 // Use default which is index position for value
    private var value2: Any = "recents" // Use a string just for the sake of it...

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Bottom Nav with showLabels = true") {
                mBottomNavigation(value1, true, onChange = { _, indexValue -> setState { value1 = indexValue }}) {
                    mBottomNavigationAction("Recents", buildElement { mIcon("restore") })
                    mBottomNavigationAction("Favourites", buildElement { mIcon("favorite") })
                    mBottomNavigationAction("Nearby", buildElement { mIcon("location_on") })
                }
            }
            demoPanel("Bottom Nav with showLabels = false (the default)") {
                mBottomNavigation(value2, onChange = { _, indexValue -> setState { value2 = indexValue }}) {
                    mBottomNavigationAction("Recents", buildElement { mIcon("restore") }, value = "recents")
                    mBottomNavigationAction("Favourites", buildElement { mIcon("favorite") }, value = "favorite")
                    mBottomNavigationAction("Nearby", buildElement { mIcon("location_on") }, value = "nearby")
                    mBottomNavigationAction("Folder", buildElement { mIcon("folder") }, value = "folder")
                }
            }
        }
    }
}
