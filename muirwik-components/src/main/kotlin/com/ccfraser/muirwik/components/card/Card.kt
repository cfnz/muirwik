package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Card")
private external val cardModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardComponent : RComponent<MCardProps, RState> = cardModule.default

interface MCardProps : StyledProps {
    var raised: Boolean
}

fun RBuilder.mCard(raised: Boolean = false,
                   className: String? = null,
                   handler: StyledHandler<MCardProps>? = null) = createStyled(cardComponent) {
    attrs.raised = raised

    setStyledPropsAndRunHandler(className, handler)
}
