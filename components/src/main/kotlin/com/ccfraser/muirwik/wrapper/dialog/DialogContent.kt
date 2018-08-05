package com.ccfraser.muirwik.wrapper.dialog

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/DialogContent")
private external val dialogContentModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogContentComponent: RComponent<StyledProps, RState> = dialogContentModule.default

fun RBuilder.mDialogContent(
        className: String? = null,
        handler: StyledHandler<StyledProps>) = createStyled(dialogContentComponent) {

    setStyledPropsAndRunHandler(className, handler)
}



