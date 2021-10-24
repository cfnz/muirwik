package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/ButtonGroup")
private external val buttonGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val buttonGroupComponentType: ComponentType<ButtonGroupProps> = buttonGroupModule.default

enum class ButtonGroupVariant {
    text, outlined, contained
}

enum class ButtonGroupOrientation {
    vertical, horizontal
}

external interface ButtonGroupProps : StyledPropsWithCommonAttributes {
    var component: String
    var disabled: Boolean
    var disableElevation: Boolean
    var disableFocusRipple: Boolean
    var disableRipple: Boolean
    var fullWidth: Boolean
}

var ButtonGroupProps.color by EnumPropToString(ButtonColor.values())
var ButtonGroupProps.orientation by EnumPropToString(ButtonGroupOrientation.values())
var ButtonGroupProps.size by EnumPropToString(ButtonSize.values())
var ButtonGroupProps.variant by EnumPropToString(ButtonGroupVariant.values())

fun RBuilder.buttonGroup(
    color: ButtonColor = ButtonColor.primary,
    variant: ButtonGroupVariant = ButtonGroupVariant.outlined,
    orientation: ButtonGroupOrientation = ButtonGroupOrientation.horizontal,
    handler: StyledHandler<ButtonGroupProps>) {
    createStyled(buttonGroupComponentType, handler) {
        attrs.color = color
        attrs.orientation = orientation
        attrs.variant = variant
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mButtonGroup(
    color: ButtonColor = ButtonColor.primary,
    variant: ButtonGroupVariant = ButtonGroupVariant.outlined,
    orientation: ButtonGroupOrientation = ButtonGroupOrientation.horizontal,
    size: ButtonSize = ButtonSize.medium,
    fullWidth: Boolean = false,
    disabled: Boolean = false,
    component: String? = null,

    disableFocusRipple: Boolean = false,
    disableRipple: Boolean = false,

    className: String? = null,
    handler: StyledHandler<ButtonGroupProps>? = null
) {
    createStyled(buttonGroupComponentType, className, handler) {
        attrs.color = color
        component?.let { attrs.component = component }
        attrs.disabled = disabled
        attrs.disableFocusRipple = disableFocusRipple
        attrs.disableRipple = disableRipple
        attrs.fullWidth = fullWidth
        attrs.orientation = orientation
        attrs.size = size
        attrs.variant = variant
    }
}

