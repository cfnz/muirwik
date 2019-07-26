package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

class TestThemeComponent : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        div {
            mAppBar(MColor.primary, MAppBarPosition.static) {
                mToolbar {
                    mTypography("Title", variant = MTypographyVariant.h6, color = MTypographyColor.inherit)
                }
            }
            mTypography("This is the TestThemeComponent. The attrs.theme here is " +
                    "${if (attrs.asDynamic().theme == undefined) "undefined" else attrs.asDynamic().theme}, " +
                    "the props.theme here is ${if (props.asDynamic().theme == undefined) "undefined" else props.asDynamic().theme}.")
            themeContext.Consumer { theme ->
                mTypography("The themeContext type is ${theme.palette.type}, and the spacing unit is ${1.spacingUnits}")
            }
        }
    }
}


fun RBuilder.testThemeComponent() = child(TestThemeComponent::class) {}

