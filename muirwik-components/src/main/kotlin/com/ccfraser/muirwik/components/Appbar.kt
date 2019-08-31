package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/AppBar")
private external val iconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val iconComponent: RComponent<MAppBarProps, RState> = iconModule.default

@Suppress("EnumEntryName")
enum class MAppBarPosition {
    fixed, absolute, sticky, static, relative
}

interface MAppBarProps : StyledPropsWithCommonAttributes
var MAppBarProps.color by EnumPropToString(MColor.values())
var MAppBarProps.position by EnumPropToString(MAppBarPosition.values())

fun RBuilder.mAppBar(
        color: MColor = MColor.primary,
        position: MAppBarPosition = MAppBarPosition.fixed,

        className: String? = null,
        handler: StyledHandler<MAppBarProps>? = null) = createStyled(iconComponent) {

    attrs.color = color
    attrs.position = position

    setStyledPropsAndRunHandler(className, handler)
}

