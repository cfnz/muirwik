package com.ccfraser.muirwik.components

import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@material-ui/core/AppBar")
private external val appBarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val appBarComponentType: ComponentType<MAppBarProps> = appBarModule.default

@Suppress("EnumEntryName")
enum class MAppBarPosition {
    fixed, absolute, sticky, static, relative
}

enum class MAppBarColor {
    default, inherit, primary, secondary, transparent
}

external interface MAppBarProps : MPaperProps
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
