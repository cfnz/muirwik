package com.ccfraser.muirwik.components

import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/Link")
private external val linkModule: dynamic
private val linkComponentType: ComponentType<MLinkProps> = linkModule.default

@Suppress("EnumEntryName")
enum class MLinkUnderline {
    none, hover, always
}

external interface MLinkProps: MTypographyProps {
    var block: Boolean

    @JsName("TypographyClasses")
    var typographyClasses: String
}
var MLinkProps.underline by EnumPropToString(MLinkUnderline.values())


/**
 * Allows more styling of link behaviour over the button with an href.
 * The convenience param targetBlank will, if href is used, set target to "_blank" and rel will be set to "nopener" as
 * recommended in https://material-ui.com/style/links/. When targetBlank is true target will not be used.
 */
fun RBuilder.mLink(
    text: String? = null,
    hRefOptions: HRefOptions? = null,
    underline: MLinkUnderline = MLinkUnderline.hover,
    gutterBottom: Boolean = false,
    noWrap: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MLinkProps>? = null
) {
    createStyled(linkComponentType, className, handler) {
        attrs.gutterBottom = gutterBottom
        hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
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
    underline: MLinkUnderline = MLinkUnderline.hover,
    gutterBottom: Boolean = false,
    noWrap: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MLinkProps>? = null
) {
    mLink(text, HRefOptions(hRef), underline, gutterBottom, noWrap, className, handler)
}
