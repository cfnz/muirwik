package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps

@JsModule("@mui/material/AlertTitle")
private external val alertTitleModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val alertTitleComponentType: ComponentType<StyledProps> = alertTitleModule.default


fun RBuilder.alertTitle(title: String, handler: StyledHandler<StyledProps>? = null) {
    createStyled(alertTitleComponentType, handler) {
        +title
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mAlertTitle(
    title: String,
    className: String? = null,
    handler: StyledHandler<StyledProps>? = null
) {
    createStyled(alertTitleComponentType, className, handler) {
        +title
    }
}
