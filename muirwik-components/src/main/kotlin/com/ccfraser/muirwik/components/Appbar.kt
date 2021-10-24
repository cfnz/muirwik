package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/AppBar")
private external val appBarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val appBarComponentType: ComponentType<AppBarProps> = appBarModule.default

@Suppress("EnumEntryName")
enum class AppBarPosition {
    absolute, fixed, relative, static, sticky
}

@Suppress("EnumEntryName")
enum class AppBarColor {
    default, inherit, primary, secondary, transparent
}

external interface AppBarProps : PaperProps {
    var enableColorOnDark: Boolean
}
var AppBarProps.color by EnumPropToString(AppBarColor.values())
var AppBarProps.position by EnumPropToString(AppBarPosition.values())

fun RBuilder.appBar(position: AppBarPosition = AppBarPosition.fixed, handler: StyledHandler<AppBarProps>) {
    createStyled(appBarComponentType, handler) {
        attrs.position = position
    }
}

@Deprecated("Replace with appBar")
fun RBuilder.mAppBar(
    color: AppBarColor = AppBarColor.primary,
    position: AppBarPosition = AppBarPosition.fixed,
    className: String? = null,
    handler: StyledHandler<AppBarProps>? = null
) {
    appBar {
        attrs.color = color
        attrs.position = position
        attrs.className = className
        handler?.invoke(this)
    }
}