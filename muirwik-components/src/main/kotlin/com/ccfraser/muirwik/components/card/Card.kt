package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Card")
private external val cardModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardComponentType: ComponentType<MCardProps> = cardModule.default

external interface MCardProps : StyledProps {
    var raised: Boolean
}

fun RBuilder.mCard(
    raised: Boolean = false,
   className: String? = null,
   handler: StyledHandler<MCardProps>? = null
) {
    createStyled(cardComponentType, className, handler) {
        attrs.raised = raised
    }
}
