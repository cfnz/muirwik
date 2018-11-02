package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.wrapper.currentTheme
import com.ccfraser.muirwik.wrapper.mTypography
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

class TestThemeComponent : RComponent<RProps, RState>() {

    override fun RBuilder.render() {

        div {
            mTypography("This is the TestThemeComponent.")
            mTypography("The attrs.theme here is ${if (attrs.asDynamic().theme == undefined) "undefined" else attrs.asDynamic().theme}, " +
                    "the props.theme here is ${if (props.asDynamic().theme == undefined) "undefined" else props.asDynamic().theme}, " +
                    "the currentTheme here is ${currentTheme}.")
            mTypography("The ThemeType is ${currentTheme.palette.type}, and the spacing unit is ${currentTheme.spacing.unit}")
        }
    }
}


fun RBuilder.testThemeComponent() = child(TestThemeComponent::class) {}

