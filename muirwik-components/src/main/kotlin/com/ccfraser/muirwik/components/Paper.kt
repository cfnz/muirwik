package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Paper")
private external val paperModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val paperComponent: RComponent<MPaperProps, RState> = paperModule.default

@Suppress("EnumEntryName")
enum class MPaperVariant {
    elevation, outlined
}

external interface MPaperProps : StyledPropsWithCommonAttributes {
    var component: String
    var elevation: Int
    var square: Boolean
}
var MPaperProps.variant by EnumPropToString(MPaperVariant.values())

fun RBuilder.mPaper(
        component: String = "div",
        elevation: Int = 2,
        square: Boolean = false,
        variant: MPaperVariant = MPaperVariant.elevation,

        className: String? = null,
        handler: StyledHandler<MPaperProps>? = null) = createStyled(paperComponent) {
    attrs.component = component
    attrs.elevation = elevation
    attrs.square = square
    attrs.variant = variant

    setStyledPropsAndRunHandler(className, handler)
}


