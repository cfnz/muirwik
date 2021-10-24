package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Box")
private external val boxModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val boxComponentType: ComponentType<MBoxProps> = boxModule.default

external interface MBoxProps : StyledProps {
    var component: ElementType
}

fun RBuilder.box(component: ElementType = "div", handler: StyledHandler<MBoxProps>) {
    createStyled(boxComponentType, handler) {
        attrs.component = component
    }
}


