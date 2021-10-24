package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Fade")
private external val fadeModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val fadeComponentType: ComponentType<FadeProps> = fadeModule.default

external interface FadeProps : TransitionProps
var FadeProps.timeout by TransitionDurationDelegate()


fun RBuilder.fade(show: Boolean = false, handler: StyledHandler<FadeProps>) {
    createStyled(fadeComponentType, handler) {
        attrs.show = show
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mFade(
    show: Boolean = false,
    timeout: TransitionDuration? = null,
    className: String? = null,
    handler: StyledHandler<FadeProps>? = null
) {
    createStyled(fadeComponentType, className, handler) {
        attrs.show = show
        timeout?.let { attrs.timeout = it }
    }
}

