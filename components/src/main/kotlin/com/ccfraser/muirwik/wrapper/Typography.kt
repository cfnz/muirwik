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

enum class MTypographyAlign {
    Inherit, Left, Center, Right, Justify;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class MTypographyColor {
    Inherit, Primary, TextSecondary, Secondary, Error, Default;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

enum class MTypographyVariant {
    Display4, Display3, Display2, Display1, HeadLine, Title, Subheading, Body2, Body1, Caption, Button;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MTypographyProps : StyledProps {
    var align: String
    var color: String
    var gutterBottom: Boolean
    var noWrap: Boolean
    var paragraph: Boolean
    var variant: String
}

fun RBuilder.mTypography(
        text: String? = null,
        variant: MTypographyVariant = MTypographyVariant.Body1,
        color: MTypographyColor = MTypographyColor.Default,
        align: MTypographyAlign = MTypographyAlign.Left,
        gutterBottom: Boolean = false,
        noWrap: Boolean = false,
        paragraph: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MTypographyProps>? = null) = createStyled(typographyComponent) {
    attrs.align = align.toString()
    attrs.color = color.toString()
    attrs.gutterBottom = gutterBottom
    attrs.noWrap = noWrap
    attrs.paragraph = paragraph
    attrs.variant = variant.toString()

    text?.let {childList.add(it)}

    setStyledPropsAndRunHandler(className, handler)
}

