package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/ListItemIcon")
private external val listItemIconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemIconComponentType: ComponentType<ButtonBaseProps> = listItemIconModule.default

fun RBuilder.listItemIcon(iconName: String? = null, handler: StyledHandler<ButtonBaseProps>? = null) {
    createStyled(listItemIconComponentType, handler) {
        iconName?.let { icon(iconName) }
    }
}
