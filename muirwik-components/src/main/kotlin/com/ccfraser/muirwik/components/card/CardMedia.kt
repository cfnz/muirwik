package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/material/CardMedia")
private external val cardMediaModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardMediaComponentType: ComponentType<MCardMediaProps> = cardMediaModule.default

external interface MCardMediaProps : StyledProps {
    var component: String
    var image: String
    var title: String
}

fun RBuilder.mCardMedia(
    image: String,
    title: String = "",
    className: String? = null,
    handler: StyledHandler<MCardMediaProps>? = null
) {
    createStyled(cardMediaComponentType, className, handler) {
        attrs.image = image
        attrs.title = title
    }
}
