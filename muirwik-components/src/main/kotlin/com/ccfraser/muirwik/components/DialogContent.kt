package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/DialogContent")
private external val dialogContentModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogContentComponentType: ComponentType<DialogContentProps> = dialogContentModule.default

external interface DialogContentProps : StyledProps {
    var dividers: Boolean
}

fun RBuilder.dialogContent(dividers: Boolean = false, handler: StyledHandler<DialogContentProps>) {
    createStyled(dialogContentComponentType, handler) {
        attrs.dividers = dividers
    }
}
