package com.ccfraser.muirwik.components.button

import com.ccfraser.muirwik.components.*
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Button")
private external val buttonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val buttonComponent: RComponent<MButtonProps, RState> = buttonModule.default


interface MButtonProps : MButtonBaseProps {
    var color: String
    var disableFocusRipple: Boolean
    var fullWidth: Boolean
    var href: String
    var size: String
    var variant: String
}

fun RBuilder.mButton(
        caption: String,
        primary: Boolean = false, // If true, then this overrides the color... just an easier setter...
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        color: MColor = MColor.default,
        variant: MButtonVariant = MButtonVariant.text,
        size: MButtonSize = MButtonSize.medium,
        hRefOptions: HRefOptions? = null,
        fullWidth: Boolean = false,
        component: String? = null,

        centerRipple: Boolean = false,
        focusRipple: Boolean = true,
        disableFocusRipple: Boolean = false,
        disableRipple: Boolean = false,
        touchRippleProps: RProps? = null,

        onKeyboardFocus: ((Event) -> Unit)? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MButtonProps>? = null) = createStyled(buttonComponent, addAsChild) {
    attrs.centerRipple = centerRipple
    attrs.color = if (primary) MColor.primary.toString() else color.toString()
    component?.let { attrs.component = component}
    attrs.disabled = disabled
    attrs.disableFocusRipple = disableFocusRipple
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    attrs.fullWidth = fullWidth
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let { attrs.onKeyboardFocus = onKeyboardFocus }
    attrs.size = size.toString()
    touchRippleProps?.let { attrs.touchRippleProps = touchRippleProps }
    attrs.variant = variant.toString()

    childList.add(caption)

    setStyledPropsAndRunHandler(className, handler)
}

