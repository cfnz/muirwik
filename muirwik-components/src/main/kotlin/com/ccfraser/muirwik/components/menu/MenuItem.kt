package com.ccfraser.muirwik.components.menu

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.list.MListItemProps
import com.ccfraser.muirwik.components.list.mListItemAvatar
import com.ccfraser.muirwik.components.list.mListItemIcon
import com.ccfraser.muirwik.components.list.mListItemText
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@mui/material/MenuItem")
private external val menuItemModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val menuItemComponentType: ComponentType<MMenuItemProps> = menuItemModule.default

external interface MMenuItemProps : MListItemProps {
    // Selected has been moved to ListItemProps
    // var selected: Boolean
    var value: String
}

/**
 * Includes the props from ListItem, this is the more user friendly version.
 * For item with icon or avatar, use [mMenuItemWithIcon] or [mMenuItemWithAvatar]
 */
fun RBuilder.mMenuItem(
    primaryText: String,
    secondaryText: String? = null,
    selected: Boolean = false,
    key: String? = null,
    value: String? = null,
    divider: Boolean = false,
    disabled: Boolean = false,
    hRefOptions: HRefOptions? = null,
    onClick: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<MMenuItemProps>? = null
) {
    mMenuItem(selected, button = true, key = key, value = value, divider = divider, disabled = disabled,
           hRefOptions = hRefOptions, onClick = onClick, className = className) {

        hRefOptions?.let { attrs.component = "a" }

        if (secondaryText == null) {
            // Just a simple text child element is all that is required...
            +primaryText
        } else {
            mListItemText(primaryText, secondaryText)
        }

        // We don't call setStyledPropsAndRunHandler as this is called in the original mListItem above (but the handler below is not)
        if (handler != null) handler()
    }
}

fun RBuilder.mMenuItemWithIcon(
    iconName: String,
    primaryText: String,
    secondaryText: String? = null,
    selected: Boolean = false,
    key: String? = null,
    value: String? = null,
    divider: Boolean = false,
    disabled: Boolean = false,
    useAvatar: Boolean = false,
    hRefOptions: HRefOptions? = null,
    onClick: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<MMenuItemProps>? = null
) {
    mMenuItem(selected, button = true, key = key, value = value, divider = divider, disabled = disabled,
            hRefOptions = hRefOptions, onClick = onClick, className = className) {

        hRefOptions?.let { attrs.component = "a" }

        if (useAvatar) {
            mListItemAvatar { mAvatar { mIcon(iconName) } }
        } else {
            mListItemIcon(iconName)
        }
        if (secondaryText == null) {
            // Just a simple text child element is all that is required...
            +primaryText
        } else {
            mListItemText(primaryText, secondaryText)
        }

        // We don't call setStyledPropsAndRunHandler as this is called in the original mListItem above (but the handler below is not)
        if (handler != null) handler()
    }
}


fun RBuilder.mMenuItemWithAvatar(
    avatarSrc: String,
    primaryText: String,
    secondaryText: String? = null,
    selected: Boolean = false,
    key: String? = null,
    value: String? = null,
    divider: Boolean = false,
    disabled: Boolean = false,
    hRefOptions: HRefOptions? = null,
    onClick: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<MMenuItemProps>? = null
) {
    mMenuItem(selected, button = true, key = key, value = value, divider = divider, disabled = disabled,
            hRefOptions = hRefOptions, onClick = onClick, className = className) {

        hRefOptions?.let { attrs.component = "a" }
        mListItemAvatar { mAvatar(avatarSrc) }

        if (secondaryText == null) {
            // Just a simple text child element is all that is required...
            +primaryText
        } else {
            mListItemText(primaryText, secondaryText)
        }

        // We don't call setStyledPropsAndRunHandler as this is called in the original mListItem above (but the handler below is not)
        if (handler != null) handler()
    }
}

/**
 * Includes the props from ListItem, this is the full version
 */
fun RBuilder.mMenuItem(
    selected: Boolean = false,
    button: Boolean = false,
    component: String? = null,
    containerComponent: String = "li",
    key: String? = null,
    value: String? = null,
    divider: Boolean = false,
    disabled: Boolean = false,
    hRefOptions: HRefOptions? = null,
    containerProps: Props? = null,
    dense: Boolean = false,
    disableGutters: Boolean = false,
    onClick: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<MMenuItemProps>? = null
) {
    createStyled(menuItemComponentType, className, handler) {
        attrs.button = button
        component?.let { attrs.component = it }
        attrs.containerComponent = containerComponent
        containerProps?.let { attrs.containerProps = it }
        attrs.dense = dense
        attrs.disabled = disabled
        attrs.disableGutters = disableGutters
        attrs.divider = divider
        hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
        onClick?.let { attrs.onClick = it }
        key?.let { attrs.key = it }
        attrs.selected = selected
        value?.let { attrs.value = it }
    }
}


