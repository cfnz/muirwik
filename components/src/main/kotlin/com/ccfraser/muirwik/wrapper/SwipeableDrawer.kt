package com.ccfraser.muirwik.wrapper

import com.ccfraser.muirwik.wrapper.transitions.MSlideProps
import com.ccfraser.muirwik.wrapper.transitions.TransitionDuration
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/SwipeableDrawer")
private external val swipeableDrawerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val swipeableDrawerComponent: RComponent<MSwipeableDrawerProps, RState> = swipeableDrawerModule.default

interface MSwipeableDrawerProps : MDrawerProps {
    var disableBackdropTransition: Boolean
    var disableDiscovery: Boolean
    var disableSwipeToOpen: Boolean
    var hysteresis: Number
    var minFlingVelocity: Number
    var onOpen: (Event) -> Unit
    var swipeAreaWidth: Number
}

fun RBuilder.mSwipeableDrawer(
        open: Boolean = false,
        anchor: MDrawerAnchor = MDrawerAnchor.left,
        variant: MDrawerVariant = MDrawerVariant.temporary,
        onOpen: ((Event) -> Unit)? = null,
        onClose: ((Event) -> Unit)? = null,
        disableBackdropTransition: Boolean = false,
        disableDiscovery: Boolean = false,
        disableSwipeToOpen: Boolean? = null,
        hysteresis: Number = 0.55,
        minFlingVelocity: Number = 400,
        swipeAreaWidth: Number = 20,

        elevation: Int = 16,
        modalProps: RProps? = null,
        paperProps: MPaperProps? = null,
        slideProps: MSlideProps? = null,
        transitionDuration: TransitionDuration? = null,

        className: String? = null,
        handler: StyledHandler<MSwipeableDrawerProps>) = createStyled(swipeableDrawerComponent) {
    attrs.anchor = anchor.toString()
    attrs.disableBackdropTransition = disableBackdropTransition
    attrs.disableDiscovery = disableDiscovery
    disableSwipeToOpen?.let{ attrs.disableSwipeToOpen = it }
    attrs.elevation = elevation
    attrs.hysteresis = hysteresis
    attrs.minFlingVelocity = minFlingVelocity
    attrs.swipeAreaWidth = swipeAreaWidth
    modalProps?.let { attrs.modalProps = it }
    onClose?.let { attrs.onClose = it }
    attrs.open = open
    onOpen?.let { attrs.onOpen = it }
    paperProps?.let { attrs.paperProps = it }
    slideProps?.let { attrs.slideProps = it }
    transitionDuration?.let { attrs.transitionDuration = it.value() }
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}



