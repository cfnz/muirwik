package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import react.RHandler
import styled.StyledProps


@JsModule("@material-ui/core/DialogContentText")
private external val dialogContentTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogContentTextComponentType: ComponentType<StyledProps> = dialogContentTextModule.default

fun RBuilder.mDialogContentText(
        text: String,

        className: String? = null,
        handler: RHandler<StyledProps>? = null) = createStyled(dialogContentTextComponentType) {
    childList.add(text)
    setStyledPropsAndRunHandler(className, handler)
}



