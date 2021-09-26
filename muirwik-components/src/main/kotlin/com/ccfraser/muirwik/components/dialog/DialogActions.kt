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

fun RBuilder.mDialogActions(
    disableSpacing: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MDialogActionsProps>
) {
    createStyled(dialogActionsComponentType, className, handler) {
        attrs.disableSpacing = disableSpacing
    }
}



