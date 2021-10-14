package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/DialogContent")
private external val dialogContentModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogContentComponentType: ComponentType<MDialogContentProps> = dialogContentModule.default

external interface MDialogContentProps : StyledProps {
    var dividers: Boolean
}

fun RBuilder.mDialogContent(dividers: Boolean = false, handler: StyledHandler<MDialogContentProps>) {
    createStyled(dialogContentComponentType, handler) {
        attrs.dividers = dividers
    }
}
