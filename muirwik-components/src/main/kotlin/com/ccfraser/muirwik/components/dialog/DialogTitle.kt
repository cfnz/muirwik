package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/DialogTitle")
private external val dialogTitleModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dialogTitleComponentType: ComponentType<MDialogTitleProps> = dialogTitleModule.default

external interface MDialogTitleProps : StyledProps {
    var disableTypography: Boolean
}

fun RBuilder.mDialogTitle(
    text: String,
    disableTypography: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MDialogTitleProps>? = null
) {
    createStyled(dialogTitleComponentType, className, handler) {
        attrs.disableTypography = disableTypography
        childList.add(ReactNode(text))
    }
}