package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.ReactHtmlElementEvents
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.Props
import react.RBuilder
import styled.StyledHandler

@JsModule("@mui/material/Backdrop")
private external val backdropModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val backdropComponentType: ComponentType<BackdropProps> = backdropModule.default

external interface BackdropProps : FadeProps, ReactHtmlElementEvents {
    var component: ElementType
    var components: dynamic
    var componentsProps: Props
    var invisible: Boolean
    var open: Boolean
}
var BackdropProps.transitionDuration by TransitionDurationDelegate()


fun RBuilder.backdrop(open: Boolean, handler: StyledHandler<BackdropProps>) {
    createStyled(backdropComponentType, handler) {
        attrs.open = open
   }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mBackdrop(
    open: Boolean,
    invisible: Boolean = false,
    transitionDuration: TransitionDuration? = null,
    onClick: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<BackdropProps>? = null
) {
    createStyled(backdropComponentType, className, handler) {
        attrs.open = open
        attrs.invisible = invisible
        transitionDuration?.let { attrs.transitionDuration = it }
        onClick?.let { attrs.onClick = it }
   }
}
