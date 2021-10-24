package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import com.ccfraser.muirwik.components.utils.toHyphenCase
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/SpeedDialAction")
private external val speedDialActionModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val speedDialActionComponentType: ComponentType<SpeedDialActionProps> = speedDialActionModule.default

@Suppress("EnumEntryName")
enum class SpeedDialActionPlacement {
    bottomStart, bottom, leftEnd, leftStart, left, rightEnd, rightStart, right, topEnd, topStart, top;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

external interface SpeedDialActionProps : TooltipProps {
    var delay: Int

    @JsName("FabProps")
    var fabProps: FabProps

    var key: String

    var icon: ReactNode

    @JsName("TooltipClasses")
    var tooltipClasses: Any

    var tooltipOpen: Boolean
    var tooltipTitle: ReactNode
}
var SpeedDialActionProps.tooltipPlacement by EnumPropToString(SpeedDialActionPlacement.values())

fun RBuilder.speedDialAction(handler: StyledHandler<SpeedDialActionProps>) {
    createStyled(speedDialActionComponentType, handler)
}

fun RBuilder.speedDialAction(
    key: String,
    icon: ReactNode,
    tooltipTitle: String? = null,
    handler: StyledHandler<SpeedDialActionProps>? = null
) {
    createStyled(speedDialActionComponentType, handler) {
        attrs.key = key
        attrs.icon = icon
        tooltipTitle?.let { attrs.tooltipTitle = ReactNode(it) }
    }
}
