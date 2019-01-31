package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.list.MListItemProps
import com.ccfraser.muirwik.components.list.mListItemAvatar
import com.ccfraser.muirwik.components.list.mListItemIcon
import com.ccfraser.muirwik.components.list.mListItemText
import com.ccfraser.muirwik.components.transitions.MTransitionProps
import com.ccfraser.muirwik.components.transitions.TransitionTimeout
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.*
import react.dom.span
import styled.StyledHandler
import styled.StyledProps
import kotlin.reflect.KClass


@JsModule("@material-ui/core/Menu")
private external val menuModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val menuComponent: RComponent<MMenuProps, RState>  = menuModule.default

external interface MMenuProps : StyledProps {
    var anchorEl: Node

    @JsName("MenuListProps")
    var menuListProps: MMenuListProps
    var onClose: (Event) -> Unit
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
}

fun RBuilder.mMenu(
        open: Boolean,
        anchorElement: Node? = null,
        onClose: ((Event) -> Unit)? = null,
        onEnter: (() -> Unit)? = null,
        onEntered: (() -> Unit)? = null,
        onEntering: (() -> Unit)? = null,
        onExit: (() -> Unit)? = null,
        onExited: (() -> Unit)? = null,
        onExiting: (() -> Unit)? = null,
        menuListProps: MMenuListProps? = null,
        popoverClasses: String? = null,
//        transitionComponent: KClass<out RComponent<RProps, RState>>? = null,
        transitionComponent: KClass<out RComponent<MTransitionProps, RState>>? = null,
        transitionDuration: TransitionTimeout? = null,

        className: String? = null,
        handler: StyledHandler<MMenuProps>) = createStyled(menuComponent) {
    anchorElement?.let { attrs.anchorEl = anchorElement }
    menuListProps?.let { attrs.menuListProps = menuListProps }
    onClose?.let { attrs.onClose = onClose }
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

    setStyledPropsAndRunHandler(className, handler)
}


@JsModule("@material-ui/core/MenuItem")
private external val menuItemModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val menuItemComponent: RComponent<MMenuItemProps, RState> = menuItemModule.default

interface MMenuItemProps : MListItemProps {
    // Selected has been moved to ListItemProps
    // var selected: Boolean
    var value: String
}

/**
 * Includes the props from ListItem, this is the more user friendly version, see both versions
 * of mListItem for more info.
 */
fun RBuilder.mMenuItem(
        primaryText: String,
        secondaryText: String? = null,
        iconName: String? = null,
        selected: Boolean = false,
        value: String? = null,
        disabled: Boolean = false,
        key: String? = null,
        href: String? = null,
        onClick: ((Event) -> Unit)? = null,
        divider: Boolean = false,
        useAvatar: Boolean = false,
        compactAvatar: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MMenuItemProps>? = null): ReactElement {
    val e = mMenuItem(selected, button = true, divider = divider, key = key, href = href, value = value,
            disabled = disabled, onClick = onClick, className = className) {

        // NOTE: This code is similar to mListItem... if you make changes here, look there too...
        // TODO: Refactor similar code
        // TODO: Do we really need all of ListItem's props? Note we can still get to them via attrs in the calling code.
        href?.let { attrs.component = "a" }

        val altBuilder = RBuilder()
        if (iconName != null) {
            if (useAvatar) {
                if (compactAvatar) {
                    mListItemAvatar { mAvatar { mIcon(iconName) } }
                } else {
                    mAvatar { mIcon(iconName) }
                }
            } else {
                mListItemIcon(iconName)
            }
        }
        if (secondaryText == null) {
            // Just a simple text child element is all that is required...
            +primaryText
        } else {
            mListItemText(primary = altBuilder.span { +primaryText }, secondary = altBuilder.span { +secondaryText })
        }

        // We don't call setStyledPropsAndRunHandler as this is called in the original mListItem above (but the handler below is not)
        if (handler != null) handler()
    }
    return e
}

/**
 * Includes the props from ListItem
 */
fun RBuilder.mMenuItem(
        selected: Boolean = false,
        button: Boolean = false,
        component: String? = null,
        containerComponent: String = "li",
        value: String? = null,
        disabled: Boolean = false,
        key: String? = null,
        containerProps: RProps? = null,
        dense: Boolean = false,
        disableGutters: Boolean = false,
        divider: Boolean = false,
        href: String? = null,

        onClick: ((Event) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MMenuItemProps>? = null) = createStyled(menuItemComponent) {
    attrs.button = button
    component?.let { attrs.component = it }
    attrs.containerComponent = containerComponent
    containerProps?.let { attrs.containerProps = it }
    attrs.dense = dense
    attrs.disabled = disabled
    attrs.disableGutters = disableGutters
    attrs.divider = divider
    href?.let { attrs.href = it }
    onClick?.let { attrs.onClick = it }
    key?.let { attrs.key = it }
    attrs.selected = selected
    value?.let { attrs.value = it }

    setStyledPropsAndRunHandler(className, handler)
}


@JsModule("@material-ui/core/MenuList")
private external val menuListModule: dynamic
private val menuList: RComponent<MMenuListProps, RState> = menuListModule.default

interface MMenuListProps : StyledProps {
}

/**
 * Includes the props from ListItem
 */
fun RBuilder.mMenuList(
        className: String? = null,
        handler: StyledHandler<MMenuListProps>? = null) = createStyled(menuList) {

    setStyledPropsAndRunHandler(className, handler)
}

