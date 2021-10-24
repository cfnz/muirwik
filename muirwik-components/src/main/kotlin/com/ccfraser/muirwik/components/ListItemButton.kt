package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.HrefOptionsDelegate
import com.ccfraser.muirwik.components.utils.createStyled
import com.ccfraser.muirwik.components.utils.toHyphenCase
import react.ComponentType
import react.RBuilder
import react.key
import styled.StyledHandler


@JsModule("@mui/material/ListItemButton")
private external val listItemButtonModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemButtonComponentType: ComponentType<ListItemButtonProps> = listItemButtonModule.default

/**
 * This is for the vertical alignment of list items, for example, if you have an avatar and a long list item
 * (three lines or more for example), then you might want the avatar aligned to the top of the list item
 * rather than being vertically centered - in this case, use flexStart.
 */
@Suppress("EnumEntryName")
enum class ListItemButtonAlignItems {
    flexStart, center;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

external interface ListItemButtonProps : ButtonBaseProps {
    var autoFocus: Boolean
    var dense: Boolean
    var disableGutters: Boolean
    var divider: Boolean
    var selected: Boolean
}
var ListItemButtonProps.alignItems by EnumPropToString(ListItemAlignItems.values())
var ListItemButtonProps.hrefOptions by HrefOptionsDelegate()

fun RBuilder.listItemButton(handler: StyledHandler<ListItemButtonProps>) {
    createStyled(listItemButtonComponentType, handler)
}

/**
 * More user-friendly version (don't usually need to add children as the params here do pretty much all that is required)
 * For item with icon or avatar, use [listItemButtonWithAvatar] or [listItemButtonWithIcon]
 */
fun RBuilder.listItemButton(
    primaryText: String,
    secondaryText: String? = null,
    selected: Boolean = false,
    key: String? = null,
    handler: StyledHandler<ListItemButtonProps>? = null
) {
    createStyled(listItemButtonComponentType, handler) {
        attrs.key = key ?: primaryText
        attrs.selected = selected
        listItemText(primaryText, secondaryText)
    }
}

/**
 * More user-friendly version with icon (don't usually need to add children as the params here do pretty much all that is required)
 */
fun RBuilder.listItemButtonWithIcon(
    iconName: String,
    primaryText: String,
    secondaryText: String? = null,
    selected: Boolean = false,
    key: String? = null,
    useAvatar: Boolean = false,
    handler: StyledHandler<ListItemButtonProps>? = null
) {
    listItemButton {
        attrs.selected = selected
        attrs.key = key ?: primaryText

        if (useAvatar) {
            listItemAvatar { avatar { icon(iconName) } }
        } else {
            listItemIcon(iconName)
        }
        listItemText(primaryText, secondaryText)

        // We don't call setStyledPropsAndRunHandler as this is called in the original listItem above (but the handler below is not)
        if (handler != null) handler()
    }
}

/**
 * More user-friendly version with avatar (don't usually need to add children as the params here do pretty much all that is required)
 * Note, for icon with avatar use [listItemButtonWithIcon]
 */
fun RBuilder.listItemButtonWithAvatar(
    avatarSrc: String,
    primaryText: String,
    secondaryText: String? = null,
    selected: Boolean = false,
    key: String? = null,
    handler: StyledHandler<ListItemButtonProps>? = null
) {
    listItemButton {
        attrs.selected = selected
        attrs.key = key ?: primaryText

        listItemAvatar { avatar(avatarSrc) }
        listItemText(primaryText, secondaryText)

        // We don't call setStyledPropsAndRunHandler as this is called in the original listItem above (but the handler below is not)
        if (handler != null) handler()
    }
}
