package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Divider")
private external val dividerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dividerComponent: RComponent<MDividerProps, RState> = dividerModule.default

interface MDividerProps : StyledProps {
    var absolute: Boolean
    var component: String
    var inset: Boolean
    var light: Boolean
}

fun RBuilder.mDivider(
        absolute: Boolean = false,
        component: String = "hr",
        inset: Boolean = false,
        light: Boolean = false,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MDividerProps>? = null) = createStyled(dividerComponent, addAsChild) {
    attrs.absolute = absolute
    attrs.component = component
    attrs.inset = inset
    attrs.light = light

    setStyledPropsAndRunHandler(className, handler)
}

