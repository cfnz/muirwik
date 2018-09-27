package com.ccfraser.muirwik.wrapper

import kotlinext.js.JsObject
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Icon")
private external val iconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val iconComponent: RComponent<MIconProps, RState> = iconModule.default

@Suppress("EnumEntryName")
enum class MIconColor {
    inherit, primary, secondary, action, error, disabled
}

@Suppress("EnumEntryName")
enum class MIconFontSize {
    inherit, default, small, large
}

interface MIconProps : StyledProps {
    var color: String
    var fontSize: String
    var style: JsObject
}

fun RBuilder.mIcon(
        iconName: String,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        color: MIconColor? = null,
        fontSize: MIconFontSize = MIconFontSize.default,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MIconProps>? = null) = createStyled(iconComponent, addAsChild) {
    if (primary) {
        attrs.color = MColor.primary.toString()
    } else {
        color?.let { attrs.color = color.toString() }
    }
    attrs.fontSize = fontSize.toString()

    childList.add(iconName)
    setStyledPropsAndRunHandler(className, handler)
}


