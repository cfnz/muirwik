package com.ccfraser.muirwik.wrapper.list

import com.ccfraser.muirwik.wrapper.MButtonBaseProps
import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.mIcon
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
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
