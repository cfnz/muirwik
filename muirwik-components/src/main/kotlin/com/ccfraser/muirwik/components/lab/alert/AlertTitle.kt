package com.ccfraser.muirwik.components.lab.alert

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/lab/AlertTitle")
private external val alertTitleModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val alertTitleComponent: RComponent<StyledProps, RState> = alertTitleModule.default

fun RBuilder.mAlertTitle(
        title: String,
        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<StyledProps>? = null) = createStyled(alertTitleComponent, addAsChild) {

    +title
    setStyledPropsAndRunHandler(className, handler)
}
