package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/DialogActions")
private external val dialogActionsModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogActionsComponentType: ComponentType<MDialogActionsProps> = dialogActionsModule.default

external interface MDialogActionsProps : StyledProps {
    var disableSpacing: Boolean
}

fun RBuilder.mDialogActions(
        disableSpacing: Boolean = false,
        className: String? = null,
        handler: StyledHandler<MDialogActionsProps>) = createStyled(dialogActionsComponentType) {
    attrs.disableSpacing = disableSpacing
    setStyledPropsAndRunHandler(className, handler)
}



