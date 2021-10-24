package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/SwipeableDrawer")
private external val swipeableDrawerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val swipeableDrawerComponentType: ComponentType<SwipeableDrawerProps> = swipeableDrawerModule.default

external interface SwipeableDrawerProps : DrawerProps {
    var disableBackdropTransition: Boolean
    var disableDiscovery: Boolean
    var disableSwipeToOpen: Boolean
    var hysteresis: Number
    var minFlingVelocity: Number
    var onOpen: (Event) -> Unit

    @JsName("SwipeAreaProps")
    var swipeAreaProps: SwipeAreaProps

    var swipeAreaWidth: Number
}


external interface SwipeAreaProps : StyledProps


fun RBuilder.swipeableDrawer(
    open: Boolean,
    onOpen: ((Event) -> Unit),
    onClose: ((Event) -> Unit),
    anchor: DrawerAnchor = DrawerAnchor.left,
    handler: StyledHandler<SwipeableDrawerProps>
) {
    createStyled(swipeableDrawerComponentType, handler) {
        attrs.anchor = anchor
        attrs.onClose = onClose
        attrs.onOpen = onOpen
        attrs.open = open
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mSwipeableDrawer(
    open: Boolean = false,
    anchor: DrawerAnchor = DrawerAnchor.left,
    variant: DrawerVariant = DrawerVariant.temporary,
    onOpen: ((Event) -> Unit)? = null,
    onClose: ((Event) -> Unit)? = null,
    swipeAreaWidth: Number = 20,
    elevation: Int = 16,
    transitionDuration: TransitionDuration? = null,
    className: String? = null,
    handler: StyledHandler<SwipeableDrawerProps>
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



