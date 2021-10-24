package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/SnackbarContent")
private external val SnackbarContentModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val snackbarContentComponentType: ComponentType<SnackbarContentProps> = SnackbarContentModule.default

external interface SnackbarContentProps : StyledProps {
    var action: ReactNode
    var message: ReactNode
    var role: String
}

fun RBuilder.snackbarContent(handler: StyledHandler<SnackbarContentProps>) {
    createStyled(snackbarContentComponentType, handler)
}

fun RBuilder.snackbarContent(
    message: String,
    handler: StyledHandler<SnackbarContentProps>? = null
) {
    createStyled(snackbarContentComponentType, handler) {
        attrs.message = ReactNode(message)
    }
}
