package com.ccfraser.muirwik.components.menu

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import com.ccfraser.muirwik.components.transitions.MTransitionProps
import com.ccfraser.muirwik.components.transitions.TransitionTimeout
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import kotlin.reflect.KClass


@JsModule("@material-ui/core/Menu")
private external val menuModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val menuComponent: RComponent<MMenuProps, RState>  = menuModule.default

@Suppress("EnumEntryName")
enum class MenuOnCloseReason {
    escapeKeyDown, backdropClick, tabKeyDown
}

enum class MMenuVariant {
    menu, selectedMenu
}

interface MMenuProps : StyledPropsWithCommonAttributes {
    var anchorEl: Node
    var autoFocus: Boolean

    @JsName("MenuListProps")
    var menuListProps: MMenuListProps
    var onClose: (Event, String) -> Unit
    var onEnter: () -> Unit
    var onEntered: () -> Unit
    var onEntering: () -> Unit
    var onExit: () -> Unit
    var onExited: () -> Unit
    var onExiting: () -> Unit
    var open: Boolean

    @JsName("PopoverClasses")
    var popoverClasses: String

    @JsName("TransitionComponent")
    var transitionComponent: dynamic
    var transitionDuration: dynamic
    var value: Any
    var variant: MMenuVariant
}

fun RBuilder.mMenu(
        open: Boolean,
        anchorElement: Node? = null,
        onClose: ((Event, MenuOnCloseReason) -> Unit)? = null,
        onEnter: (() -> Unit)? = null,
        onEntered: (() -> Unit)? = null,
        onEntering: (() -> Unit)? = null,
        onExit: (() -> Unit)? = null,
        onExited: (() -> Unit)? = null,
        onExiting: (() -> Unit)? = null,
        autoFocus: Boolean = true,
        menuListProps: MMenuListProps? = null,
        popoverClasses: String? = null,
//        transitionComponent: KClass<out RComponent<RProps, RState>>? = null,
        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,
        transitionDuration: TransitionTimeout? = null,
        variant: MMenuVariant = MMenuVariant.selectedMenu,

        className: String? = null,
        handler: StyledHandler<MMenuProps>) = createStyled(menuComponent) {
    anchorElement?.let { attrs.anchorEl = anchorElement }
    attrs.autoFocus = autoFocus
    menuListProps?.let { attrs.menuListProps = menuListProps }
    onClose?.let { attrs.onClose = { event, string -> it(event, MenuOnCloseReason.valueOf(string)) }}
    onEnter?.let { attrs.onEnter = onEnter }
    onEntered?.let { attrs.onEntered = onEntered }
    onEntering?.let { attrs.onEntering = onEntering }
    onExit?.let { attrs.onExit = onExit }
    onExited?.let { attrs.onExited = onExited }
    onExiting?.let { attrs.onExiting = onExiting }
    attrs.open = open
    popoverClasses?.let { attrs.popoverClasses = popoverClasses }
    transitionComponent?.let { attrs.transitionComponent = transitionComponent.js }
    transitionDuration?.let { attrs.transitionDuration = it.value() }
    attrs.variant = variant

    setStyledPropsAndRunHandler(className, handler)
    attrs.asDynamic().variant = variant.toString()
}

