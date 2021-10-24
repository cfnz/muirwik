package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/SpeedDialIcon")
private external val speedDialIconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val speedDialIconComponentType: ComponentType<SpeedDialIconProps> = speedDialIconModule.default


external interface SpeedDialIconProps : TooltipProps {
    var icon: ReactNode
    var openIcon: ReactNode
}

fun RBuilder.speedDialIcon(
    icon: ReactNode? = null,
    openIcon: ReactNode? = null,
    handler: StyledHandler<SpeedDialIconProps>? = null
) {
    createStyled(speedDialIconComponentType, handler) {
        icon?.let { attrs.icon = it }
        openIcon?.let { attrs.openIcon = it }
    }
}
