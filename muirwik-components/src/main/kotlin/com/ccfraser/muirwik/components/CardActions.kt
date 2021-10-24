package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/CardActions")
private external val cardActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardActionsComponentType: ComponentType<CardActionsProps> = cardActionsModule.default

external interface CardActionsProps : StyledProps {
    var disableSpacing: Boolean
}

fun RBuilder.cardActions(handler: StyledHandler<CardActionsProps>) {
    createStyled(cardActionsComponentType, handler)
}


@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mCardActions(handler: StyledHandler<CardActionsProps>) {
    createStyled(cardActionsComponentType, handler)
}
