package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.*
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/Button")
private external val buttonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val buttonComponentType: ComponentType<ButtonProps> = buttonModule.default


external interface ButtonProps : ButtonBaseProps {
    var disableFocusRipple: Boolean
    var disableElevation: Boolean
    var endIcon: ReactElement
    var fullWidth: Boolean
    var startIcon: ReactElement
}

@Suppress("EnumEntryName")
enum class ButtonColor {
    inherit, primary, secondary, success, error, info, warning
}

//var MButtonProps.color by EnumPropToStringNullable(MColor.values())
var ButtonProps.color by EnumPropToString(ButtonColor.values())
var ButtonProps.hrefOptions by HrefOptionsDelegate()
var ButtonProps.size by EnumPropToString(ButtonSize.values())
var ButtonProps.variant by EnumPropToStringNullable(ButtonVariant.values())



fun RBuilder.button(
    caption: String,
    color: ButtonColor = ButtonColor.primary,
    variant: ButtonVariant? = null,
    size: ButtonSize = ButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    handler: StyledHandler<ButtonProps>? = null
) {
    createStyled(buttonComponentType, null, handler) {
        attrs.color = color
        attrs.size = size
        attrs.variant = variant
        hRefOptions?.let { attrs.hrefOptions = it }
        childList.add(ReactNode(caption))
    }
}


fun RBuilder.button(handler: StyledHandler<ButtonProps>) {
    createStyled(buttonComponentType, null, handler) { }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
//Setting the color and variant to the default values seems to upset button groups... the buttons don't inherit the
//groups color or variant, even if color is default... so allowing color and variant to default to null which seems
//to fix the issue and does not cause any issues
fun RBuilder.mButton(
    caption: String,
    color: ButtonColor = ButtonColor.primary,
    variant: ButtonVariant? = null,
    disabled: Boolean = false,
    onClick: ((Event) -> Unit)? = null,
    size: ButtonSize = ButtonSize.medium,
    hRefOptions: HRefOptions? = null,
    className: String? = null,
    handler: StyledHandler<ButtonProps>? = null
) {
    createStyled(buttonComponentType, className, handler) {
        attrs.color = color
        attrs.disabled = disabled
        hRefOptions?.let { attrs.hrefOptions = it }
        onClick?.let { attrs.onClick = onClick }
        attrs.size = size
        attrs.variant = variant

        childList.add(ReactNode(caption))
    }
}
