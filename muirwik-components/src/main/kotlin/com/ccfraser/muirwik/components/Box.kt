package com.ccfraser.muirwik.components

import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Box")
private external val boxModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val boxComponentType: ComponentType<MBoxProps> = boxModule.default

external interface MBoxProps : StyledProps {
    var component: String
}

fun RBuilder.mBox(component: String = "div", handler: StyledHandler<MBoxProps>) {
    createStyled(boxComponentType, handler) {
        attrs.component = component
    }
}


