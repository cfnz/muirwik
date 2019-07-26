package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Link")
private external val linkModule: dynamic
private val linkComponent: RComponent<MLinkProps, RState> = linkModule.default


interface MLinkProps: MTypographyProps {
    var block: Boolean

    @JsName("TypographyClasses")
    var typographyClasses: String

    var underline: String
}

@Suppress("EnumEntryName")
enum class MLinkUnderline {
    none, hover, always
}

/**
 * Allows more styling of link behaviour over the button with an href.
 * The convenience param targetBlank will, if href is used, set target to "_blank" and rel will be set to "nopener" as
 * recommended in https://material-ui.com/style/links/. When targetBlank is true target will not be used.
 */
fun RBuilder.mLink(
        text: String? = null,
        hRefOptions: HRefOptions? = null,
        underline: MLinkUnderline = MLinkUnderline.hover,
        variant: MTypographyVariant = MTypographyVariant.body1,
        color: MTypographyColor = MTypographyColor.primary,
        align: MTypographyAlign = MTypographyAlign.left,
        gutterBottom: Boolean = false,
        noWrap: Boolean = false,
        paragraph: Boolean = false,
        component: String? = null,
        typographyClasses: String? = null,

        className: String? = null,
        handler: StyledHandler<MLinkProps>? = null) = createStyled(linkComponent) {
    attrs.align = align.toString()
    attrs.color = color.toString()
    component?.let { attrs.component = it }
    attrs.gutterBottom = gutterBottom
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    attrs.noWrap = noWrap
    attrs.paragraph = paragraph
    attrs.underline = underline.toString()
    attrs.variant = variant.toString()
    typographyClasses?.let { attrs.typographyClasses = it }
    text?.let {childList.add(it)}

    setStyledPropsAndRunHandler(className, handler)
}

