package com.ccfraser.muirwik.components.list

import com.ccfraser.muirwik.components.MButtonBaseProps
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler

@JsModule("@material-ui/core/ListItemIcon")
private external val listItemIconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val listItemIconComponent: RComponent<MButtonBaseProps, RState> = listItemIconModule.default

fun RBuilder.mListItemIcon(
        iconName: String? = null,
        className: String? = null,
        handler: StyledHandler<MButtonBaseProps>? = null) = createStyled(listItemIconComponent) {
    iconName?.let { mIcon(iconName) }
    setStyledPropsAndRunHandler(className, handler)
}
