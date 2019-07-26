package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/ButtonGroup")
private external val buttonGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val buttonGroupComponent: RComponent<MButtonGroupProps, RState> = buttonGroupModule.default

enum class MButtonGroupVariant {
    outlined, contained
}

interface MButtonGroupProps : StyledProps {
    var color: String
    var component: String
    var disabled: Boolean
    var disableFocusRipple: Boolean
    var disableRipple: Boolean
    var fullWidth: Boolean
    var size: String
    var variant: String
}

fun RBuilder.mButtonGroup(
        color: MColor = MColor.default,
        variant: MButtonGroupVariant = MButtonGroupVariant.outlined,
        size: MButtonSize = MButtonSize.medium,
        fullWidth: Boolean = false,
        disabled: Boolean = false,
        component: String? = null,

        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MButtonGroupProps>? = null) = createStyled(buttonGroupComponent) {
    attrs.color = color.toString()
    component?.let { attrs.component = component}
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.fullWidth = fullWidth
    attrs.size = size.toString()
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

