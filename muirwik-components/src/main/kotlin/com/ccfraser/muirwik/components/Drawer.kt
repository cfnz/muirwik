package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.transitions.MSlideProps
import com.ccfraser.muirwik.components.transitions.TransitionDuration
import com.ccfraser.muirwik.components.transitions.TransitionDurationDelegateNullable
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.Props
import styled.StyledHandler


@JsModule("@mui/material/Drawer")
private external val drawerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val drawerComponentType: ComponentType<MDrawerProps> = drawerModule.default

@Suppress("EnumEntryName")
enum class MDrawerAnchor {
    left, top, right, bottom
}

@Suppress("EnumEntryName")
enum class MDrawerVariant {
    permanent, persistent, temporary
}

external interface MDrawerProps : StyledPropsWithCommonAttributes {
    var elevation: Int

    @JsName("ModalProps")
    var modalProps: Props

    var onClose: (Event) -> Unit
    var open: Boolean

    @JsName("PaperProps")
    var paperProps: MPaperProps

    @JsName("SlideProps")
    var slideProps: MSlideProps

}
var MDrawerProps.anchor by EnumPropToString(MDrawerAnchor.values())
var MDrawerProps.transitionDuration by TransitionDurationDelegateNullable()
var MDrawerProps.variant by EnumPropToString(MDrawerVariant.values())

fun RBuilder.mDrawer(
        open: Boolean = false,
        anchor: MDrawerAnchor = MDrawerAnchor.left,
        variant: MDrawerVariant = MDrawerVariant.temporary,
        onClose: ((Event) -> Unit)? = null,
        elevation: Int = 16,
        modalProps: Props? = null,
        paperProps: MPaperProps? = null,
        slideProps: MSlideProps? = null,
        transitionDuration: TransitionDuration? = null,

        className: String? = null,
        handler: StyledHandler<MDrawerProps>
) {
    createStyled(drawerComponentType, className, handler) {
        attrs.anchor = anchor
        attrs.elevation = elevation
        modalProps?.let { attrs.modalProps = it }
        onClose?.let { attrs.onClose = it }
        attrs.open = open
        paperProps?.let { attrs.paperProps = it }
        slideProps?.let { attrs.slideProps = it }
        attrs.variant = variant
        transitionDuration?.let { attrs.transitionDuration = it }
    }
}



