package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/CardActions")
private external val cardActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardActionsComponentType: ComponentType<MCardActionsProps> = cardActionsModule.default

external interface MCardActionsProps : StyledProps {
    var disableSpacing: Boolean
}

fun RBuilder.mCardActions(
    disableSpacing: Boolean = false,
    handler: StyledHandler<MCardActionsProps>?
) {
    createStyled(cardActionsComponentType, null, handler) {
        attrs.disableSpacing = disableSpacing
    }
}
