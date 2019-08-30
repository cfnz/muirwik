package com.ccfraser.muirwik.components.transitions

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Fade")
private external val fadeModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val fadeComponent: RComponent<MFadeProps, RState> = fadeModule.default

external interface MFadeProps : MTransitionProps
var MFadeProps.timeout by TransitionDurationDelegate()

fun RBuilder.mFade(
        show: Boolean = false,
        timeout: TransitionDuration? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MFadeProps>? = null) = createStyled(fadeComponent, addAsChild) {
    attrs.show = show
    timeout?.let { attrs.timeout = it }

    setStyledPropsAndRunHandler(className, handler)
}

