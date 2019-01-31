package com.ccfraser.muirwik.components

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
    h1, h2, h3, h4, h5, h6, subtitle1, subtitle2, body1, body2, caption, button, overline, srOnly, inherit,
    @Deprecated("Use h1")
    display4,

    @Deprecated("Use h2")
    display3,

    @Deprecated("Use h3")
    display2,

    @Deprecated("Use h4")
    display1,

    @Deprecated("Use h5")
    headline,

    @Deprecated("Use h6")
    title,

    @Deprecated("Use subtitle1")
    subheading
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

