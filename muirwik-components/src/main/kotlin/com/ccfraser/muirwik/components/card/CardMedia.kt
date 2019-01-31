package com.ccfraser.muirwik.components.card

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/core/CardMedia")
private external val cardMediaModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val cardMediaComponent: RComponent<MCardMediaProps, RState> = cardMediaModule.default

interface MCardMediaProps : StyledProps {
    var image: String
    var title: String
}

fun RBuilder.mCardMedia(image: String,
                        title: String = "",
                        className: String? = null,
                        handler: StyledHandler<MCardMediaProps>? = null) = createStyled(cardMediaComponent) {
    attrs.image = image
    attrs.title = title

    setStyledPropsAndRunHandler(className, handler)
}
