package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Paper")
private external val paperModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val paperComponentType: ComponentType<PaperProps> = paperModule.default

@Suppress("EnumEntryName")
enum class PaperVariant {
    elevation, outlined
}

external interface PaperProps : StyledPropsWithCommonAttributes {
    var component: ElementType
    var elevation: Int
    var square: Boolean
}
var PaperProps.variant by EnumPropToString(PaperVariant.values())

fun RBuilder.paper(
    elevation: Int = 2,
    variant: PaperVariant = PaperVariant.elevation,
    square: Boolean = false,
    handler: StyledHandler<PaperProps>? = null
) {
    createStyled(paperComponentType, handler) {
        if (variant == PaperVariant.elevation) {
            attrs.elevation = elevation
        }
        attrs.square = square
        attrs.variant = variant
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mPaper(
    component: String = "div",
    elevation: Int = 2,
    square: Boolean = false,
    variant: PaperVariant = PaperVariant.elevation,
    className: String? = null,
    handler: StyledHandler<PaperProps>? = null
) {
    createStyled(paperComponentType, className, handler) {
        attrs.component = component
        if (variant == PaperVariant.elevation) {
            attrs.elevation = elevation
        }
        attrs.square = square
        attrs.variant = variant
    }
}


