package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/TabScrollButton")
private external val tabScrollButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val tabScrollButtonComponentType: ComponentType<TabScrollButtonProps> = tabScrollButtonModule.default



@Suppress("EnumEntryName")
enum class TabScrollButtonDirection {
    left, right
}

external interface TabScrollButtonProps: StyledProps {
    var disabled: Boolean
}
var TabScrollButtonProps.direction by EnumPropToString(TabScrollButtonDirection.values())
var TabScrollButtonProps.orientation by EnumPropToString(TabOrientation.values())

fun RBuilder.tabScrollButton(
    direction: TabScrollButtonDirection,
    orientation: TabOrientation,
    disabled: Boolean = false,
    handler: StyledHandler<TabScrollButtonProps>? = null
) {
    createStyled(tabScrollButtonComponentType, handler) {
        attrs.direction = direction
        attrs.orientation = orientation
        attrs.disabled = disabled
    }
}
