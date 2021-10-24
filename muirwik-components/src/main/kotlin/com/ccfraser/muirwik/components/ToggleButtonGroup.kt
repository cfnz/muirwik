package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/material/ToggleButtonGroup")
private external val toggleButtonGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val toggleButtonGroupComponentType: ComponentType<ToggleButtonGroupProps<*>> = toggleButtonGroupModule.default

@Suppress("EnumEntryName")
enum class ToggleButtonGroupOrientation {
    horizontal, vertical
}

external interface ToggleButtonGroupProps<T> : StyledProps {
    var disabled: Boolean
    var exclusive: Boolean
    var fullWidth: Boolean
    var onChange: (event: Event, value: T?) -> Unit
    var value: T?
}
var ToggleButtonGroupProps<*>.color by EnumPropToString(ToggleButtonColor.values())
var ToggleButtonGroupProps<*>.orientation by EnumPropToString(ToggleButtonGroupOrientation.values())
var ToggleButtonGroupProps<*>.size by EnumPropToString(ToggleButtonSize.values())


fun <T> RBuilder.toggleButtonGroup(
    value: T? = null,
    exclusive: Boolean = false,
    color: ToggleButtonColor = ToggleButtonColor.standard,
    size: ToggleButtonSize = ToggleButtonSize.medium,
    handler: StyledHandler<ToggleButtonGroupProps<T>>
) {
    createStyled(toggleButtonGroupComponentType, handler) {
        attrs.color = color
        attrs.exclusive = exclusive
        attrs.size = size
        value?.let { attrs.value = it }
    }
}


