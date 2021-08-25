package com.ccfraser.muirwik.components.transitions

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/Fade")
private external val fadeModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val fadeComponentType: ComponentType<MFadeProps> = fadeModule.default

external interface MFadeProps : MTransitionProps
var MFadeProps.timeout by TransitionDurationDelegate()

fun RBuilder.mFade(
    show: Boolean = false,
    timeout: TransitionDuration? = null,
    className: String? = null,
    handler: StyledHandler<MFadeProps>? = null
) {
    createStyled(fadeComponentType, className, handler) {
        attrs.show = show
        timeout?.let { attrs.timeout = it }
    }
}

