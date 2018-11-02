package com.ccfraser.muirwik.wrapper

import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps
import styled.css


@JsModule("@material-ui/core/Toolbar")
private external val toolbarModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val toolbarComponent: RComponent<MToolbarProps, RState> = toolbarModule.default

@Suppress("EnumEntryName")
enum class ToolbarVariant {
    regular, dense
}

interface MToolbarProps : StyledProps {
    var disableGutters: Boolean
    var variant: String
}

fun RBuilder.mToolbar(
        disableGutters: Boolean = false,
        variant: ToolbarVariant = ToolbarVariant.regular,

        className: String? = null,
        handler: StyledHandler<MToolbarProps>? = null) = createStyled(toolbarComponent) {
    attrs.disableGutters = disableGutters
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * Just a simple title with padding to push any items to the right, and no wrapping
 */
fun RBuilder.mToolbarTitle(text: String): ReactElement {
    return mTypography(text, variant = MTypographyVariant.h6, color = MTypographyColor.inherit, noWrap = true) { css { flexGrow = 1.0 }}
}