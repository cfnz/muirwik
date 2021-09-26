package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.transitions.TransitionDuration
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/SwipeableDrawer")
private external val swipeableDrawerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val swipeableDrawerComponentType: ComponentType<MSwipeableDrawerProps> = swipeableDrawerModule.default

external interface MSwipeableDrawerProps : MDrawerProps {
    var disableBackdropTransition: Boolean
    var disableDiscovery: Boolean
    var disableSwipeToOpen: Boolean
    var hysteresis: Number
    var minFlingVelocity: Number
    var onOpen: (Event) -> Unit
    var swipeAreaProps: MSwipeAreaProps
    var swipeAreaWidth: Number
}

external interface MSwipeAreaProps : StyledProps

fun RBuilder.mSwipeableDrawer(
    open: Boolean = false,
    anchor: MDrawerAnchor = MDrawerAnchor.left,
    variant: MDrawerVariant = MDrawerVariant.temporary,
    onOpen: ((Event) -> Unit)? = null,
    onClose: ((Event) -> Unit)? = null,
    swipeAreaWidth: Number = 20,
    elevation: Int = 16,
    transitionDuration: TransitionDuration? = null,
    className: String? = null,
    handler: StyledHandler<MSwipeableDrawerProps>
) {
    createStyled(swipeableDrawerComponentType, className, handler) {
        attrs.anchor = anchor
        attrs.elevation = elevation
        attrs.swipeAreaWidth = swipeAreaWidth
        onClose?.let { attrs.onClose = it }
        attrs.open = open
        onOpen?.let { attrs.onOpen = it }
        transitionDuration?.let { attrs.transitionDuration = it }
        attrs.variant = variant
    }
}



