package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/DialogTitle")
private external val dialogTitleModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogTitleComponentType: ComponentType<MDialogTitleProps> = dialogTitleModule.default

external interface MDialogTitleProps : StyledProps

fun RBuilder.dialogTitle(text: String, handler: StyledHandler<MDialogTitleProps>? = null) {
    createStyled(dialogTitleComponentType, handler) {
        childList.add(ReactNode(text))
    }
}