package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/MenuList")
private external val menuListModule: dynamic
private val menuListType: ComponentType<MenuListProps> = menuListModule.default

external interface MenuListProps : ListProps {
    var autoFocus: Boolean
    var autoFocusItem: Boolean
    var disabledItemsFocusable: Boolean
    var disableListWrap: Boolean
}
var MenuListProps.variant by EnumPropToString(MenuVariant.values())

/**
 * Includes the props from ListItem
 */
fun RBuilder.mMenuList(handler: StyledHandler<MenuListProps>) {
    createStyled(menuListType, handler)
}

