package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Grow")
private external val growModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val growComponentType: ComponentType<GrowProps> = growModule.default

external interface GrowProps : TransitionProps
var GrowProps.timeout by TransitionDurationWithAutoDelegate()

fun RBuilder.grow(show: Boolean = false, handler: StyledHandler<GrowProps>) {
    createStyled(growComponentType, handler) {
        attrs.show = show
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mGrow(
    show: Boolean = false,
    timeout: TransitionDurationWithAuto? = null,
    className: String? = null,
    handler: StyledHandler<GrowProps>? = null
) {
    createStyled(growComponentType, className, handler) {
        attrs.show = show
        timeout?.let { attrs.timeout = it }
    }
}

