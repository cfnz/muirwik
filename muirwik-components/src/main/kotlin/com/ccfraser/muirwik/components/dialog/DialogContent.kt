package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/DialogContent")
private external val dialogContentModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogContentComponentType: ComponentType<MDialogContentProps> = dialogContentModule.default

external interface MDialogContentProps : StyledProps {
    var dividers: Boolean
}

fun RBuilder.mDialogContent(
        dividers: Boolean = false,
        className: String? = null,
        handler: StyledHandler<MDialogContentProps>) = createStyled(dialogContentComponentType) {
    attrs.dividers = dividers
    setStyledPropsAndRunHandler(className, handler)
}



