package com.ccfraser.muirwik.components.menu

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.transitions.AutoTransitionDuration
import com.ccfraser.muirwik.components.transitions.TransitionComponent
import com.ccfraser.muirwik.components.transitions.TransitionDurationWithAuto
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/Menu")
private external val menuModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val menuComponentType: ComponentType<MMenuProps> = menuModule.default

@Suppress("EnumEntryName")
enum class MenuOnCloseReason {
    escapeKeyDown, backdropClick, tabKeyDown
}

@Suppress("EnumEntryName")
enum class MMenuVariant {
    menu, selectedMenu
}

external interface MMenuProps : MPopoverProps {
    var autoFocus: Boolean
    var disableAutoFocusItem: Boolean

    @JsName("MenuListProps")
    var menuListProps: MMenuListProps

    @JsName("PopoverClasses")
    var popoverClasses: String
    var value: Any
}
var MMenuProps.variant by EnumPropToString(MMenuVariant.values())
var MMenuProps.onClose by OnClosePropWithReasonDelegate(MenuOnCloseReason.values())


fun RBuilder.mMenu(
    open: Boolean,
    anchorElement: Node? = null,
    onClose: ((Event, MenuOnCloseReason) -> Unit)? = null,
    autoFocus: Boolean = true,
    disableAutoFocusItem: Boolean = false,
    menuListProps: MMenuListProps? = null,
    popoverClasses: String? = null,
    transitionComponent: TransitionComponent? = null,
    transitionDuration: TransitionDurationWithAuto = AutoTransitionDuration(),
    variant: MMenuVariant = MMenuVariant.selectedMenu,
    className: String? = null,
    handler: StyledHandler<MMenuProps>
) {
    createStyled(menuComponentType, className, handler) {
        anchorElement?.let { attrs.anchorEl = anchorElement }
        attrs.autoFocus = autoFocus
        attrs.disableAutoFocusItem = disableAutoFocusItem
        menuListProps?.let { attrs.menuListProps = menuListProps }
        attrs.onClose = onClose
        attrs.open = open
        popoverClasses?.let { attrs.popoverClasses = popoverClasses }
        attrs.transitionComponent = transitionComponent
        attrs.transitionDuration = transitionDuration
        attrs.variant = variant
    }
}

