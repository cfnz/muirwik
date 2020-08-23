package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Link")
private external val linkModule: dynamic
private val linkComponent: RComponent<MLinkProps, RState> = linkModule.default

@Suppress("EnumEntryName")
enum class MLinkUnderline {
    none, hover, always
}

interface MLinkProps: MTypographyProps {
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
        handler: StyledHandler<MLinkProps>? = null) = createStyled(linkComponent) {
    attrs.gutterBottom = gutterBottom
    hRefOptions?.let { setHRefTargetNoOpener(attrs, it) }
    attrs.noWrap = noWrap
    attrs.underline = underline
    text?.let {childList.add(it)}

    setStyledPropsAndRunHandler(className, handler)
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
        handler: StyledHandler<MLinkProps>? = null) = mLink(text, HRefOptions(hRef), underline, gutterBottom,
            noWrap, className, handler)
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
        handler: StyledHandler<MLinkProps>? = null) = mLink(text, HRefOptions(hRef), underline, gutterBottom,
            noWrap, className, handler)
