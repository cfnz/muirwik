package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/CardActionArea")
private external val cardActionAreaModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardActionAreaComponentType: ComponentType<MButtonBaseProps> = cardActionAreaModule.default

fun RBuilder.mCardActionArea(
    onClick: ((Event) -> Unit)? = null,
    disabled: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MButtonBaseProps>? = null
) {
    createStyled(cardActionAreaComponentType, className, handler) {
        attrs.disabled = disabled
        onClick?.let { attrs.onClick = onClick }
    }
}
