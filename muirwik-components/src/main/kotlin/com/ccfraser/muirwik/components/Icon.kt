package com.ccfraser.muirwik.components

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
//    var style: Object
    var component: String?
}

var MIconProps.color by EnumPropToString(MIconColor.values())
var MIconProps.fontSize by EnumPropToString(MIconFontSize.values())

fun RBuilder.mIcon(
        iconName: String,
        color: MIconColor = MIconColor.inherit,
        fontSize: MIconFontSize = MIconFontSize.default,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MIconProps>? = null) = createStyled(iconComponent, addAsChild) {
    attrs.color = color
    attrs.fontSize = fontSize

    childList.add(iconName)
    setStyledPropsAndRunHandler(className, handler)
}


