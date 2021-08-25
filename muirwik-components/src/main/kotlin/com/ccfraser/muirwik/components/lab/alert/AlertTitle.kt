package com.ccfraser.muirwik.components.lab.alert

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/lab/AlertTitle")
private external val alertTitleModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val alertTitleComponentType: ComponentType<StyledProps> = alertTitleModule.default

fun RBuilder.mAlertTitle(
    title: String,
    className: String? = null,
    handler: StyledHandler<StyledProps>? = null
) {
    createStyled(alertTitleComponentType, className, handler) {
        +title
    }
}
