package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.*
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/ButtonGroup")
private external val buttonGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val buttonGroupComponentType: ComponentType<MButtonGroupProps> = buttonGroupModule.default

enum class MButtonGroupVariant {
    text, outlined, contained
}

enum class MButtonGroupOrientation {
    vertical, horizontal
}

external interface MButtonGroupProps : StyledPropsWithCommonAttributes {
    var component: String
    var disabled: Boolean
    var disableElevation: Boolean
    var disableFocusRipple: Boolean
    var disableRipple: Boolean
    var fullWidth: Boolean
}

var MButtonGroupProps.color by EnumPropToString(MButtonColor.values())
var MButtonGroupProps.orientation by EnumPropToString(MButtonGroupOrientation.values())
var MButtonGroupProps.size by EnumPropToString(MButtonSize.values())
var MButtonGroupProps.variant by EnumPropToString(MButtonGroupVariant.values())


fun RBuilder.mButtonGroup(
    color: MButtonColor = MButtonColor.primary,
    variant: MButtonGroupVariant = MButtonGroupVariant.outlined,
    orientation: MButtonGroupOrientation = MButtonGroupOrientation.horizontal,
    size: MButtonSize = MButtonSize.medium,
    fullWidth: Boolean = false,
    disabled: Boolean = false,
    component: String? = null,

    disableFocusRipple: Boolean = false,
    disableRipple: Boolean = false,

    className: String? = null,
    handler: StyledHandler<MButtonGroupProps>? = null
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

