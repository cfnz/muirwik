package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Card")
private external val cardModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardComponentType: ComponentType<MCardProps> = cardModule.default

external interface MCardProps : StyledProps {
    var raised: Boolean
}

fun RBuilder.mCard(raised: Boolean = false,
                   className: String? = null,
                   handler: StyledHandler<MCardProps>? = null) = createStyled(cardComponentType) {
    attrs.raised = raised

    setStyledPropsAndRunHandler(className, handler)
}
