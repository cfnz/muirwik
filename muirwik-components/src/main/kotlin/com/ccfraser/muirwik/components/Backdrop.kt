package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.transitions.MFadeProps
import com.ccfraser.muirwik.components.transitions.TransitionDuration
import com.ccfraser.muirwik.components.transitions.TransitionDurationDelegate
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler

@JsModule("@material-ui/core/Backdrop")
private external val backdropModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val backdropComponent: RComponent<MBackdropProps, RState> = backdropModule.default

external interface MBackdropProps : MFadeProps, ReactHtmlElementEvents {
    var invisible: Boolean
    var open: Boolean
}
var MBackdropProps.transitionDuration by TransitionDurationDelegate()


fun RBuilder.mBackdrop(
        open: Boolean,
        invisible: Boolean = false,
        transitionDuration: TransitionDuration? = null,
        onClick: ((Event) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MBackdropProps>? = null) = createStyled(backdropComponent) {
    attrs.open = open
    attrs.invisible = invisible
    transitionDuration?.let { attrs.transitionDuration = it }
    onClick?.let { attrs.onClick = it }

    setStyledPropsAndRunHandler(className, handler)
}
