package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.mBottomNavigation
import com.ccfraser.muirwik.components.mBottomNavigationAction
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mTypography
import react.*
import react.dom.br


class TestBottomNavigation : RComponent<RProps, RState>() {
    private var value1: Any = 0 // Use default which is index position for value
    private var value2: Any = "recents" // Use a string just for the sake of it...

    override fun RBuilder.render() {
        mTypography("Bottom Nav with showLabels = true")
        mBottomNavigation(value1, true, onChange = { _, indexValue -> setState { value1 = indexValue }}) {
            mBottomNavigationAction("Recents", mIcon("restore", addAsChild = false))
            mBottomNavigationAction("Favourites", mIcon("favorite", addAsChild = false))
            mBottomNavigationAction("Nearby", mIcon("location_on", addAsChild = false))
        }

        br {}

        mTypography("Bottom Nav with showLabels = false (the default)")
        mBottomNavigation(value2, onChange = { _, indexValue -> setState { value2 = indexValue }}) {
            mBottomNavigationAction("Recents", mIcon("restore", addAsChild = false), value = "recents")
            mBottomNavigationAction("Favourites", mIcon("favorite", addAsChild = false), value = "favorite")
            mBottomNavigationAction("Nearby", mIcon("location_on", addAsChild = false), value = "nearby")
            mBottomNavigationAction("Folder", mIcon("folder", addAsChild = false), value = "folder")
        }
    }
}

fun RBuilder.testBottomNavigation() = child(TestBottomNavigation::class) {}
