package com.ccfraser.muirwik.components

import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Icon")
private external val iconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val iconComponentType: ComponentType<MIconProps> = iconModule.default

@Suppress("EnumEntryName")
enum class MIconColor {
    inherit, action, disabled, primary, secondary, error, info, success, warning
}

@Suppress("EnumEntryName")
enum class MIconFontSize {
    inherit, default, small, large
}

external interface MIconProps : StyledProps {
//    var style: Object
    var component: String?
}

var MIconProps.color by EnumPropToStringNullable(MIconColor.values())
var MIconProps.fontSize by EnumPropToString(MIconFontSize.values())

fun RBuilder.mIcon(
    iconName: String,
    color: MIconColor = MIconColor.inherit,
    fontSize: MIconFontSize = MIconFontSize.default,
    className: String? = null,
    handler: StyledHandler<MIconProps>? = null
) {
    createStyled(iconComponentType, className, handler) {
        attrs.color = color
        attrs.fontSize = fontSize

        childList.add(ReactNode(iconName))
    }
}


