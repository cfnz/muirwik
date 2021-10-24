package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.key
import styled.StyledHandler


@JsModule("@mui/material/MenuItem")
private external val menuItemModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val menuItemComponentType: ComponentType<MenuItemProps> = menuItemModule.default

external interface MenuItemProps : ButtonBaseProps {
    var autoFocus: Boolean
    var dense: Boolean
    var disableGutters: Boolean
    var divider: Boolean
    var selected: Boolean
    var value: Any
}

fun RBuilder.menuItem(text: String? = null, selected: Boolean = false, key: String? = null, value: Any? = null, handler: StyledHandler<MenuItemProps>? = null) {
    createStyled(menuItemComponentType, handler) {
        attrs.selected = selected
        if (key == null) {
            text?.let { attrs.key = it }
        } else {
            attrs.key = key
        }
        value?.let { attrs.value = it }
        text?.let { +it }
    }
}

fun RBuilder.menuItemWithIcon(
    iconName: String,
    text: String,
    selected: Boolean = false,
    key: String? = null,
    useAvatar: Boolean = false,
    handler: StyledHandler<MenuItemProps>? = null
) {
    createStyled(menuItemComponentType, handler) {
        attrs.selected = selected
        if (key == null) {
            attrs.key = text
        } else {
            attrs.key = key
        }

        if (useAvatar) {
            listItemAvatar { avatar { icon(iconName) } }
        } else {
            listItemIcon(iconName)
        }

        +text
    }
}

fun RBuilder.menuItemWithAvatar(
    avatarSrc: String,
    text: String,
    selected: Boolean = false,
    key: String? = null,
    handler: StyledHandler<MenuItemProps>? = null
) {
    createStyled(menuItemComponentType, handler) {
        listItemAvatar { avatar(avatarSrc) }
        attrs.selected = selected
        attrs.key = key ?: text

        +text
    }
}
