package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Paper")
private external val paperModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val paperComponent: RComponent<MPaperProps, RState> = paperModule.default

interface MPaperProps : StyledProps {
    var component: String
    var elevation: Int
    var square: Boolean
}

fun RBuilder.mPaper(
        component: String = "div",
        elevation: Int = 2,
        square: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MPaperProps>? = null) = createStyled(paperComponent) {
    attrs.component = component
    attrs.elevation = elevation
    attrs.square = square

    setStyledPropsAndRunHandler(className, handler)
}


