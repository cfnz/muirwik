package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.transitions.MSlideProps
import com.ccfraser.muirwik.components.transitions.TransitionDuration
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Drawer")
private external val drawerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val drawerComponent: RComponent<MDrawerProps, RState> = drawerModule.default

@Suppress("EnumEntryName")
enum class MDrawerAnchor {
    left, top, right, bottom
}

@Suppress("EnumEntryName")
enum class MDrawerVariant {
    permanent, persistent, temporary
}

interface MDrawerProps : StyledPropsWithCommonAttributes {
    var anchor: MDrawerAnchor
    var elevation: Int

    @JsName("ModalProps")
    var modalProps: RProps

    var onClose: (Event) -> Unit
    var open: Boolean

    @JsName("PaperProps")
    var paperProps: MPaperProps

    @JsName("SlideProps")
    var slideProps: MSlideProps

    var transitionDuration: TransitionDuration
    var variant: MDrawerVariant
}

fun MDrawerProps.redefineTypedProps() {
    this.asDynamic().anchor = anchor.toString()
    this.asDynamic().variant = variant.toString()
    if (this.transitionDuration != undefined) {this.asDynamic().transitionDuration = transitionDuration.value()}
}

fun RBuilder.mDrawer(
        open: Boolean = false,
        anchor: MDrawerAnchor = MDrawerAnchor.left,
        variant: MDrawerVariant = MDrawerVariant.temporary,
        onClose: ((Event) -> Unit)? = null,
        elevation: Int = 16,
        modalProps: RProps? = null,
        paperProps: MPaperProps? = null,
        slideProps: MSlideProps? = null,
        transitionDuration: TransitionDuration? = null,

        className: String? = null,
        handler: StyledHandler<MDrawerProps>) = createStyled(drawerComponent) {
    attrs.anchor = anchor
    attrs.elevation = elevation
    modalProps?.let { attrs.modalProps = it }
    onClose?.let { attrs.onClose = it }
    attrs.open = open
    paperProps?.let { attrs.paperProps = it }
    slideProps?.let { attrs.slideProps = it }
    attrs.variant = variant
    transitionDuration?.let { attrs.transitionDuration = it }

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineTypedProps()
}



