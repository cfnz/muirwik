package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import react.RHandler
import react.ReactNode
import styled.StyledProps


@JsModule("@material-ui/core/DialogContentText")
private external val dialogContentTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogContentTextComponentType: ComponentType<StyledProps> = dialogContentTextModule.default

fun RBuilder.mDialogContentText(
    text: String,
    className: String? = null,
    handler: RHandler<StyledProps>? = null
) {
    createStyled(dialogContentTextComponentType, className, handler) {
        childList.add(ReactNode(text))
    }
}



