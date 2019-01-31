package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.StyledProps


@JsModule("@material-ui/core/DialogContentText")
private external val dialogContentTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogContentTextComponent: RComponent<StyledProps, RState> = dialogContentTextModule.default

fun RBuilder.mDialogContentText(
        text: String,

        className: String? = null,
        handler: RHandler<StyledProps>? = null) = createStyled(dialogContentTextComponent) {
    childList.add(text)

    setStyledPropsAndRunHandler(className, handler)
}



