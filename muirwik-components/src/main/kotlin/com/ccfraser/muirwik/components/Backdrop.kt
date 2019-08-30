package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.transitions.TransitionDuration
import com.ccfraser.muirwik.components.transitions.TransitionDurationDelegate
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/core/Backdrop")
private external val backdropModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val backdropComponent: RComponent<MBackdropProps, RState> = backdropModule.default

interface MBackdropProps : StyledProps
var MBackdropProps.transitionDuration by TransitionDurationDelegate()


fun RBuilder.mBackdrop(
        transitionDuration: TransitionDuration,

        className: String? = null,
        handler: StyledHandler<MBackdropProps>? = null) = createStyled(backdropComponent) {
    attrs.transitionDuration = transitionDuration
    setStyledPropsAndRunHandler(className, handler)
}
