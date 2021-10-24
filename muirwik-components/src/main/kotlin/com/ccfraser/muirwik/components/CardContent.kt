package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/CardContent")
private external val cardContentModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardContentComponentType: ComponentType<CardContentProps> = cardContentModule.default

external interface CardContentProps : StyledProps {
    var component: String
}

fun RBuilder.cardContent(handler: StyledHandler<CardContentProps>) {
    createStyled(cardContentComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mCardContent(handler: StyledHandler<CardContentProps>) {
    createStyled(cardContentComponentType, handler)
}