package com.ccfraser.muirwik.components.menu

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.list.MListProps
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler


@JsModule("@mui/material/MenuList")
private external val menuListModule: dynamic
private val menuListType: ComponentType<MMenuListProps> = menuListModule.default

external interface MMenuListProps : MListProps {
    var autoFocus: Boolean
    var autoFocusItem: Boolean
    var disabledItemsFocusable: Boolean
    var disableListWrap: Boolean
}

/**
 * Includes the props from ListItem
 */
fun RBuilder.mMenuList(
    dense: Boolean = false,
    disablePadding: Boolean = false,
    subheader: ReactElement? = null,
    component: String = "ul",
    disableListWrap: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MMenuListProps>? = null
) {
    createStyled(menuListType, className, handler) {
        attrs.component = component
        attrs.dense = dense
        attrs.disableListWrap = disableListWrap
        attrs.disablePadding = disablePadding
        subheader?.let { attrs.subheader = subheader }
    }
}

