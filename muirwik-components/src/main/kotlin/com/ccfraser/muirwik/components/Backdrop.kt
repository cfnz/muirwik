package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.transitions.MFadeProps
import com.ccfraser.muirwik.components.transitions.TransitionDuration
import com.ccfraser.muirwik.components.transitions.TransitionDurationDelegate
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/Backdrop")
private external val backdropModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val backdropComponentType: ComponentType<MBackdropProps> = backdropModule.default

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
    handler: StyledHandler<MBackdropProps>? = null
) {
    createStyled(backdropComponentType, className, handler) {
        attrs.open = open
        attrs.invisible = invisible
        transitionDuration?.let { attrs.transitionDuration = it }
        onClick?.let { attrs.onClick = it }
   }
}
