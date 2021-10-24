package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.Props
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Drawer")
private external val drawerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val drawerComponentType: ComponentType<DrawerProps> = drawerModule.default

@Suppress("EnumEntryName")
enum class DrawerAnchor {
    left, top, right, bottom
}

@Suppress("EnumEntryName")
enum class DrawerVariant {
    permanent, persistent, temporary
}

external interface DrawerProps : StyledPropsWithCommonAttributes {
    var elevation: Int
    var hideBackdrop: Boolean

    @JsName("ModalProps")
    var modalProps: Props

    var onClose: (Event) -> Unit
    var open: Boolean

    @JsName("PaperProps")
    var paperProps: PaperProps

    @JsName("SlideProps")
    var slideProps: SlideProps

}
var DrawerProps.anchor by EnumPropToString(DrawerAnchor.values())
var DrawerProps.transitionDuration by TransitionDurationDelegateNullable()
var DrawerProps.variant by EnumPropToString(DrawerVariant.values())

fun RBuilder.drawer(
    open: Boolean = false,
    anchor: DrawerAnchor = DrawerAnchor.left,
    variant: DrawerVariant = DrawerVariant.temporary,
    handler: StyledHandler<DrawerProps>
) {
    createStyled(drawerComponentType, handler) {
        attrs.anchor = anchor
        attrs.open = open
        attrs.variant = variant
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mDrawer(
    open: Boolean = false,
    anchor: DrawerAnchor = DrawerAnchor.left,
    variant: DrawerVariant = DrawerVariant.temporary,
    onClose: ((Event) -> Unit)? = null,
    elevation: Int = 16,
    modalProps: Props? = null,
    paperProps: PaperProps? = null,
    slideProps: SlideProps? = null,
    transitionDuration: TransitionDuration? = null,

    className: String? = null,
    handler: StyledHandler<DrawerProps>
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



