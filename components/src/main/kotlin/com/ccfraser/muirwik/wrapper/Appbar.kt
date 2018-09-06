package com.ccfraser.muirwik.wrapper

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/AppBar")
private external val iconModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val iconComponent: RComponent<MAppBarProps, RState> = iconModule.default

@Suppress("EnumEntryName")
enum class MAppBarPosition {
    fixed, absolute, sticky, static
}

interface MAppBarProps : StyledProps {
    var color: String
    var position: String
}

fun RBuilder.mAppBar(
        color: MColor = MColor.primary,
        position: MAppBarPosition = MAppBarPosition.fixed,

        className: String? = null,
        handler: StyledHandler<MAppBarProps>? = null) = createStyled(iconComponent) {

    attrs.color = color.toString()
    attrs.position = position.toString()

    setStyledPropsAndRunHandler(className, handler)
}

