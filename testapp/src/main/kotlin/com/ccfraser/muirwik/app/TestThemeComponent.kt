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
            mTypography("I am the TestThemeComponent.")
            mTypography("The attrs.theme here is ${attrs.asDynamic().theme}, "
                    +"The props.theme here is ${props.asDynamic().theme}, "
                    +"The currentTheme here is ${currentTheme}.")
            mTypography("The ThemeType is ${currentTheme.palette.type}, and the spacing using is ${currentTheme.spacing.unit}")
        }
    }
}


fun RBuilder.testThemeComponent() = child(TestThemeComponent::class) {}

