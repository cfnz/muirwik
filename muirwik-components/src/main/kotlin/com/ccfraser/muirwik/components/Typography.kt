package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/Typography")
private external val typographyDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val typographyComponentType: ComponentType<TypographyProps> = typographyDefault.default

@Suppress("EnumEntryName")
enum class TypographyAlign {
    center, inherit, justify, left, right
}

@Suppress("EnumEntryName")
enum class TypographyColor {
    initial, inherit, primary, secondary, textPrimary, textSecondary, error
}

@Suppress("EnumEntryName")
enum class TypographyVariant {
    body1, body2, button, caption, h1, h2, h3, h4, h5, h6, inherit, overline, subtitle1, subtitle2
}

external interface TypographyProps : StyledPropsWithCommonAttributes {
    var component: ElementType
    var gutterBottom: Boolean
    var noWrap: Boolean
    var paragraph: Boolean
    var variantMapping: dynamic
}
var TypographyProps.align by EnumPropToString(TypographyAlign.values())
var TypographyProps.color by EnumPropToString(TypographyColor.values())
var TypographyProps.variant by EnumPropToString(TypographyVariant.values())


fun RBuilder.typography(handler: StyledHandler<TypographyProps>) {
    createStyled(typographyComponentType, handler)
}

fun RBuilder.typography(
    text: String,
    variant: TypographyVariant = TypographyVariant.body1,
    color: TypographyColor = TypographyColor.initial,
    handler: StyledHandler<TypographyProps>? = null
) {
    createStyled(typographyComponentType, handler) {
        attrs.color = color
        attrs.variant = variant

        childList.add(ReactNode(text))
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mTypography(
    text: String? = null,
    variant: TypographyVariant = TypographyVariant.body1,
    color: TypographyColor = TypographyColor.initial,
    align: TypographyAlign = TypographyAlign.left,
    gutterBottom: Boolean = false,
    noWrap: Boolean = false,
    paragraph: Boolean = false,
    component: ElementType? = null,
    className: String? = null,
    handler: StyledHandler<TypographyProps>? = null
) {
    createStyled(typographyComponentType, className, handler) {
        attrs.align = align
        attrs.color = color
        component?.let { attrs.component = it }
        attrs.gutterBottom = gutterBottom
        attrs.noWrap = noWrap
        attrs.paragraph = paragraph
        attrs.variant = variant

        text?.let {childList.add(ReactNode(it))}
    }
}

