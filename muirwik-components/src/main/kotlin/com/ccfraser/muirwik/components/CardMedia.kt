package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/CardMedia")
private external val cardMediaModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardMediaComponentType: ComponentType<CardMediaProps> = cardMediaModule.default

external interface CardMediaProps : StyledPropsWithCommonAttributes {
    var component: String
    var image: String
    var src: String
}

fun RBuilder.cardMedia(image: String, handler: StyledHandler<CardMediaProps>? = null) {
    createStyled(cardMediaComponentType, handler) {
        attrs.image = image
    }
}
