package com.ccfraser.muirwik.wrapper

import com.ccfraser.muirwik.wrapper.transitions.MSlideProps
import com.ccfraser.muirwik.wrapper.transitions.TransitionDuration
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Drawer")
private external val drawerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val drawerComponent: RComponent<MDrawerProps, RState> = drawerModule.default

enum class MDrawerAnchor {
    Left, Top, Right, Bottom;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class MDrawerVariant {
    Permanent, Persistent, Temporary;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MDrawerProps : StyledProps {
    var anchor: String
    var elevation: Int

    @JsName("ModalProps")
    var modalProps: RProps

    var onClose: (Event) -> Unit
    var open: Boolean

    @JsName("PaperProps")
    var paperProps: MPaperProps

    @JsName("SlideProps")
    var slideProps: MSlideProps

    var transitionDuration: dynamic
    var variant: String
}

fun RBuilder.mDrawer(
        open: Boolean = false,
        anchor: MDrawerAnchor = MDrawerAnchor.Left,
        variant: MDrawerVariant = MDrawerVariant.Temporary,
        onClose: ((Event) -> Unit)? = null,
        elevation: Int = 16,
        modalProps: RProps? = null,
        paperProps: MPaperProps? = null,
        slideProps: MSlideProps? = null,
        transitionDuration: TransitionDuration? = null,

        className: String? = null,
        handler: StyledHandler<MDrawerProps>) = createStyled(drawerComponent) {
    attrs.anchor = anchor.toString()
    attrs.elevation = elevation
    modalProps?.let { attrs.modalProps = modalProps }
    onClose?.let { attrs.onClose = onClose }
    attrs.open = open
    paperProps?.let { attrs.paperProps = paperProps }
    slideProps?.let { attrs.slideProps = slideProps }
    attrs.variant = variant.toString()
    transitionDuration?.let { attrs.transitionDuration = transitionDuration.value() }

    setStyledPropsAndRunHandler(className, handler)
}



