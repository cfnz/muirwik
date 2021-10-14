package com.ccfraser.muirwik.components

import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/AppBar")
private external val appBarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val appBarComponentType: ComponentType<MAppBarProps> = appBarModule.default

@Suppress("EnumEntryName")
enum class MAppBarPosition {
    absolute, fixed, relative, static, sticky
}

enum class MAppBarColor {
    default, inherit, primary, secondary, transparent
}

external interface MAppBarProps : MPaperProps {
    var enableColorOnDark: Boolean
}
var MAppBarProps.color by EnumPropToString(MAppBarColor.values())
var MAppBarProps.position by EnumPropToString(MAppBarPosition.values())

fun RBuilder.mAppBar(
    color: MAppBarColor = MAppBarColor.primary,
    position: MAppBarPosition = MAppBarPosition.fixed,
    className: String? = null,
    handler: StyledHandler<MAppBarProps>? = null
) {
    createStyled(appBarComponentType, className, handler) {
        attrs.color = color
        attrs.position = position
    }
}
