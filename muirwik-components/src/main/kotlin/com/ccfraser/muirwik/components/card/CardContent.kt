package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/CardContent")
private external val cardContentModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardContentComponent : RComponent<MCardContentProps, RState> = cardContentModule.default

interface MCardContentProps : StyledProps {
    var component: String
}

fun RBuilder.mCardContent(component: String = "div",
                          className: String? = null,
                          handler: StyledHandler<MCardContentProps>? = null) = createStyled(cardContentComponent) {
    attrs.component = component
    setStyledPropsAndRunHandler(className, handler)
}