package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.button.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.ComponentType
import react.RBuilder
import styled.StyledHandler

@JsModule("@material-ui/core/ListItemIcon")
private external val listItemIconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemIconComponentType: ComponentType<MButtonBaseProps> = listItemIconModule.default

fun RBuilder.mListItemIcon(
        iconName: String? = null,
        className: String? = null,
        handler: StyledHandler<MButtonBaseProps>? = null) = createStyled(listItemIconComponentType) {
    iconName?.let { mIcon(iconName) }
    setStyledPropsAndRunHandler(className, handler)
}
