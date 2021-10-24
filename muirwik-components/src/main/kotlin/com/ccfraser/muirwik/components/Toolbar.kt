package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinx.css.flexGrow
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps
import styled.css


@JsModule("@mui/material/Toolbar")
private external val toolbarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val toolbarComponentType: ComponentType<ToolbarProps> = toolbarModule.default

@Suppress("EnumEntryName")
enum class ToolbarVariant {
    dense, regular
}

external interface ToolbarProps : StyledProps {
    var component: ElementType
    var disableGutters: Boolean
}
var ToolbarProps.variant by EnumPropToString(ToolbarVariant.values())

fun RBuilder.toolbar(
    variant: ToolbarVariant = ToolbarVariant.regular,
    handler: StyledHandler<ToolbarProps>? = null
) {
    createStyled(toolbarComponentType, handler) {
        attrs.variant = variant
    }
}

/**
 * Just a simple title with padding to push any items to the right, and no wrapping
 */
fun RBuilder.toolbarTitle(text: String) {
    typography(text, TypographyVariant.h6, TypographyColor.inherit) {
        css { flexGrow = 1.0 }
        attrs.noWrap = true
    }
}