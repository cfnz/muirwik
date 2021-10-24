package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/DialogActions")
private external val dialogActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogActionsComponentType: ComponentType<DialogActionsProps> = dialogActionsModule.default

external interface DialogActionsProps : StyledProps {
    var disableSpacing: Boolean
}

fun RBuilder.dialogActions(handler: StyledHandler<DialogActionsProps>) {
    createStyled(dialogActionsComponentType, handler)
}



