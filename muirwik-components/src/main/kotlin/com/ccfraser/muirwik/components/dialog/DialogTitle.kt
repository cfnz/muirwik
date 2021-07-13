package com.ccfraser.muirwik.components.dialog

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/DialogTitle")
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
        handler: StyledHandler<MDialogTitleProps>? = null) = createStyled(dialogTitleComponentType) {
    attrs.disableTypography = disableTypography

    childList.add(text)

    setStyledPropsAndRunHandler(className, handler)
}



