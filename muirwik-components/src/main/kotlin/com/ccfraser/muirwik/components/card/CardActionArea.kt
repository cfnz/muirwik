package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/CardActionArea")
private external val cardActionAreaModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardActionAreaComponent: RComponent<MButtonBaseProps, RState> = cardActionAreaModule.default

fun RBuilder.mCardActionArea(
        onClick: ((Event) -> Unit)? = null,
        disabled: Boolean = false,
        onKeyboardFocus: ((Event) -> Unit)? = null,

        centerRipple: Boolean = false,
        focusRipple: Boolean = true,
        disableRipple: Boolean = false,

        component: String? = null,

        className: String? = null,
        handler: StyledHandler<MButtonBaseProps>? = null)= createStyled(cardActionAreaComponent) {
    attrs.centerRipple = centerRipple
    component?.let { attrs.component = component}
    attrs.disabled = disabled
    attrs.disableRipple = disableRipple
    attrs.focusRipple = focusRipple
    onClick?.let { attrs.onClick = onClick }
    onKeyboardFocus?.let { attrs.onKeyboardFocus = onKeyboardFocus }

    setStyledPropsAndRunHandler(className, handler)
}
