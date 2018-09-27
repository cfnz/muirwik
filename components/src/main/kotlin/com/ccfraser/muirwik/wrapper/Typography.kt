package com.ccfraser.muirwik.wrapper

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Typography")
private external val typographyDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val typographyComponent: RComponent<MTypographyProps, RState> = typographyDefault.default

@Suppress("EnumEntryName")
enum class MTypographyAlign {
    inherit, left, center, right, justify
}

@Suppress("EnumEntryName")
enum class MTypographyColor {
    inherit, primary, textSecondary, secondary, error, default
}

@Suppress("EnumEntryName")
enum class MTypographyVariant {
    display4, display3, display2, display1, headline, title, subheading, body2, body1, caption, button, srOnly, inherit
}

interface MTypographyProps : StyledProps {
    var align: String
    var color: String
    var component: String
    var gutterBottom: Boolean
    var noWrap: Boolean
    var paragraph: Boolean
    var variant: String
}

fun RBuilder.mTypography(
        text: String? = null,
        variant: MTypographyVariant = MTypographyVariant.body1,
        color: MTypographyColor = MTypographyColor.default,
        align: MTypographyAlign = MTypographyAlign.left,
        gutterBottom: Boolean = false,
        noWrap: Boolean = false,
        paragraph: Boolean = false,
        component: String? = null,

        className: String? = null,
        handler: StyledHandler<MTypographyProps>? = null) = createStyled(typographyComponent) {
    attrs.align = align.toString()
    attrs.color = color.toString()
    component?.let { attrs.component = it }
    attrs.gutterBottom = gutterBottom
    attrs.noWrap = noWrap
    attrs.paragraph = paragraph
    attrs.variant = variant.toString()

    text?.let {childList.add(it)}

    setStyledPropsAndRunHandler(className, handler)
}

