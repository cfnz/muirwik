package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Icon")
private external val iconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val iconComponentType: ComponentType<IconProps> = iconModule.default

@Suppress("EnumEntryName")
enum class IconColor {
    inherit, action, disabled, primary, secondary, error, info, success, warning
}

@Suppress("EnumEntryName")
enum class IconFontSize {
    inherit, large, medium, small
}

external interface IconProps : StyledProps {
    var baseClassName: String
    var component: ElementType

}

var IconProps.color by EnumPropToStringNullable(IconColor.values())
var IconProps.fontSize by EnumPropToString(IconFontSize.values())


fun RBuilder.icon(
    iconName: String,
    color: IconColor = IconColor.inherit,
    handler: StyledHandler<IconProps>? = null
) {
    createStyled(iconComponentType, handler) {
        attrs.color = color
        childList.add(ReactNode(iconName))
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mIcon(
    iconName: String,
    color: IconColor = IconColor.inherit,
    fontSize: IconFontSize = IconFontSize.medium,
    className: String? = null,
    handler: StyledHandler<IconProps>? = null
) {
    createStyled(iconComponentType, className, handler) {
        attrs.color = color
        attrs.fontSize = fontSize

        childList.add(ReactNode(iconName))
    }
}


