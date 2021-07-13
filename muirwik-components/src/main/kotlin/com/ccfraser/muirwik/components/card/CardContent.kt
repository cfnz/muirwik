package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/CardContent")
private external val cardContentModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardContentComponentType: ComponentType<MCardContentProps> = cardContentModule.default

external interface MCardContentProps : StyledProps {
    var component: String
}

fun RBuilder.mCardContent(className: String? = null,
                          handler: StyledHandler<MCardContentProps>? = null) = createStyled(cardContentComponentType) {
    setStyledPropsAndRunHandler(className, handler)
}