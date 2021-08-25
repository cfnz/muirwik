package com.ccfraser.muirwik.components

import kotlinx.css.flexGrow
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps
import styled.css


@JsModule("@material-ui/core/Toolbar")
private external val toolbarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val toolbarComponentType: ComponentType<MToolbarProps> = toolbarModule.default

@Suppress("EnumEntryName")
enum class ToolbarVariant {
    regular, dense
}

external interface MToolbarProps : StyledProps {
    var disableGutters: Boolean
}
var MToolbarProps.variant by EnumPropToString(ToolbarVariant.values())

fun RBuilder.mToolbar(
    disableGutters: Boolean = false,
    variant: ToolbarVariant = ToolbarVariant.regular,

    className: String? = null,
    handler: StyledHandler<MToolbarProps>? = null
) {
    createStyled(toolbarComponentType, className, handler) {
        attrs.disableGutters = disableGutters
        attrs.variant = variant
    }
}

/**
 * Just a simple title with padding to push any items to the right, and no wrapping
 */
fun RBuilder.mToolbarTitle(text: String) {
    mTypography(text, variant = MTypographyVariant.h6, color = MTypographyColor.inherit, noWrap = true) { css { flexGrow = 1.0 }}
}