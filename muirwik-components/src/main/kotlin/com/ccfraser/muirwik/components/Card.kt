package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Card")
private external val cardModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardComponentType: ComponentType<CardProps> = cardModule.default

external interface CardProps : StyledProps {
    var raised: Boolean
}

fun RBuilder.card(raised: Boolean = false, handler: StyledHandler<CardProps>) {
    createStyled(cardComponentType, handler) {
        attrs.raised = raised
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mCard(
    raised: Boolean = false,
    className: String? = null,
    handler: StyledHandler<CardProps>? = null
) {
    createStyled(cardComponentType, className, handler) {
        attrs.raised = raised
    }
}
