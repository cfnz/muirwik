package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.*
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/Link")
private external val linkModule: dynamic
private val linkComponentType: ComponentType<LinkProps> = linkModule.default

@Suppress("EnumEntryName")
enum class LinkUnderline {
    always, hover, none
}

external interface LinkProps: TypographyProps {
    @JsName("TypographyClasses")
    var typographyClasses: Any
}
var LinkProps.underline by EnumPropToString(LinkUnderline.values())
var LinkProps.hrefOptions by HrefOptionsDelegate()

fun RBuilder.link(handler: StyledHandler<LinkProps>) {
    createStyled(linkComponentType, handler)
}

fun RBuilder.link(
    text: String,
    hrefOptions: HRefOptions,
    underline: LinkUnderline = LinkUnderline.hover,
    handler: StyledHandler<LinkProps>? = null
) {
    createStyled(linkComponentType, handler) {
        attrs.hrefOptions = hrefOptions
        attrs.underline = underline
        +text
    }
}

/**
 * Sets up a link with text and an href anchor which will have a _blank target and rel="noopener"
 */
fun RBuilder.mLink(
    text: String,
    hRef: String,
    underline: LinkUnderline = LinkUnderline.hover,
    handler: StyledHandler<LinkProps>? = null
) {
    link(text, HRefOptions(hRef), underline, handler)
}

/**
 * Allows more styling of link behaviour over the button with an href.
 * The convenience param targetBlank will, if href is used, set target to "_blank" and rel will be set to "nopener" as
 * recommended in https://material-ui.com/style/links/. When targetBlank is true target will not be used.
 */
fun RBuilder.mLink(
    text: String? = null,
    hRefOptions: HRefOptions? = null,
    underline: LinkUnderline = LinkUnderline.hover,
    gutterBottom: Boolean = false,
    noWrap: Boolean = false,
    className: String? = null,
    handler: StyledHandler<LinkProps>? = null
) {
    createStyled(linkComponentType, className, handler) {
        attrs.gutterBottom = gutterBottom
        hRefOptions?.let { attrs.hrefOptions = it }
        attrs.noWrap = noWrap
        attrs.underline = underline
        text?.let {childList.add(ReactNode(it))}
    }
}

/**
 * Sets up a link with text and an href anchor which will have a _blank target and rel="noopener"
 */
fun RBuilder.mLink(
    text: String,
    hRef: String,
    underline: LinkUnderline = LinkUnderline.hover,
    gutterBottom: Boolean = false,
    noWrap: Boolean = false,
    className: String? = null,
    handler: StyledHandler<LinkProps>? = null
) {
    mLink(text, HRefOptions(hRef), underline, gutterBottom, noWrap, className, handler)
}
