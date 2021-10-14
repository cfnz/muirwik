package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/DialogActions")
private external val dialogActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogActionsComponentType: ComponentType<MDialogActionsProps> = dialogActionsModule.default

external interface MDialogActionsProps : StyledProps {
    var disableSpacing: Boolean
}

fun RBuilder.mDialogActions(handler: StyledHandler<MDialogActionsProps>) {
    createStyled(dialogActionsComponentType, handler)
}



