package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/ToggleButton")
private external val toggleButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val toggleButtonComponentType: ComponentType<ToggleButtonProps> = toggleButtonModule.default

@Suppress("EnumEntryName")
enum class ToggleButtonSize {
    small, medium, large
}

@Suppress("EnumEntryName")
enum class ToggleButtonColor {
    standard, primary, secondary, error, info, success, warning
}

external interface ToggleButtonProps : ButtonBaseProps {
    var disableFocusRipple: Boolean
    var fullWidth: Boolean
    var selected: Boolean
    var value: Any
}
var ToggleButtonProps.color by EnumPropToString(ToggleButtonColor.values())
var ToggleButtonProps.size by EnumPropToString(ToggleButtonSize.values())


fun RBuilder.toggleButton(
    value: Any,
    color: ToggleButtonColor? = null,
    size: ToggleButtonSize? = null,
    handler: StyledHandler<ToggleButtonProps>
) {
    createStyled(toggleButtonComponentType, handler) {
        color?.let { attrs.color = it }
        size?.let { attrs.size = it }
        attrs.value = value
    }
}


