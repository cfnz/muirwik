package com.ccfraser.muirwik.components.transitions

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/Grow")
private external val growModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val growComponentType: ComponentType<MGrowProps> = growModule.default

external interface MGrowProps : MTransitionProps
var MGrowProps.timeout by TransitionDurationWithAutoDelegate()

fun RBuilder.mGrow(
    show: Boolean = false,
    timeout: TransitionDurationWithAuto? = null,
    className: String? = null,
    handler: StyledHandler<MGrowProps>? = null
) {
    createStyled(growComponentType, className, handler) {
        attrs.show = show
        timeout?.let { attrs.timeout = it }
    }
}

