package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.RHandler
import react.ReactNode
import styled.StyledProps


@JsModule("@mui/material/DialogContentText")
private external val dialogContentTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogContentTextComponentType: ComponentType<StyledProps> = dialogContentTextModule.default

fun RBuilder.dialogContentText(text: String, handler: RHandler<StyledProps>? = null) {
    createStyled(dialogContentTextComponentType, handler) {
        childList.add(ReactNode(text))
    }
}



