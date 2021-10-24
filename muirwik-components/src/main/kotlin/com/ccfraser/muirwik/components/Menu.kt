package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.OnEventWithReasonDelegate
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.ComponentType
import react.ElementType
import react.Props
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Menu")
private external val menuModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val menuComponentType: ComponentType<MenuProps> = menuModule.default

@Suppress("EnumEntryName")
enum class MenuOnCloseReason {
    escapeKeyDown, backdropClick, tabKeyDown
}

@Suppress("EnumEntryName")
enum class MenuVariant {
    menu, selectedMenu
}

@Deprecated("Use [MenuVariant]")
typealias MMenuVariant = MenuVariant

external interface MenuProps : PopoverProps {
    var autoFocus: Boolean
    var disableAutoFocusItem: Boolean

    @JsName("MenuListProps")
    var menuListProps: MenuListProps

    @JsName("PopoverClasses")
    var popoverClasses: String
    var value: Any
}
var MenuProps.variant by EnumPropToString(MenuVariant.values())
var MenuProps.onClose by OnEventWithReasonDelegate(MenuOnCloseReason.values())


fun RBuilder.menu(open: Boolean, handler: StyledHandler<MenuProps>) {
    createStyled(menuComponentType, handler) {
        attrs.open = open
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
@Suppress("DEPRECATION")
fun RBuilder.mMenu(
    open: Boolean,
    anchorElement: Node? = null,
    onClose: ((Event, MenuOnCloseReason) -> Unit)? = null,
    autoFocus: Boolean = true,
    disableAutoFocusItem: Boolean = false,
    menuListProps: MenuListProps? = null,
    popoverClasses: String? = null,
    transitionComponent: ElementType<Props>? = null,
    transitionDuration: TransitionDurationWithAuto = AutoTransitionDuration(),
    variant: MMenuVariant = MMenuVariant.selectedMenu,
    className: String? = null,
    handler: StyledHandler<MenuProps>
) {
    createStyled(menuComponentType, className, handler) {
        anchorElement?.let { attrs.anchorEl = anchorElement }
        attrs.autoFocus = autoFocus
        attrs.disableAutoFocusItem = disableAutoFocusItem
        menuListProps?.let { attrs.menuListProps = menuListProps }
        attrs.onClose = onClose
        attrs.open = open
        popoverClasses?.let { attrs.popoverClasses = popoverClasses }
        transitionComponent?.let { attrs.transitionComponent = transitionComponent }
        attrs.transitionDuration = transitionDuration
        attrs.variant = variant
    }
}

