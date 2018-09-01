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

enum class ToolbarVariant {
    Regular, Dense;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

interface MToolbarProps : StyledProps {
    var disableGutters: Boolean
    var variant: String
}

fun RBuilder.mToolbar(
        disableGutters: Boolean = false,
        variant: ToolbarVariant = ToolbarVariant.Regular,

        className: String? = null,
        handler: StyledHandler<MToolbarProps>? = null) = createStyled(toolbarComponent) {
    attrs.disableGutters = disableGutters
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * Just a simple title with padding to push any items to the right
 */
fun RBuilder.mToolbarTitle(text: String): ReactElement {
    return mTypography(text, variant = MTypographyVariant.Title, color = MTypographyColor.Inherit) { css { flexGrow = 1.0 }}
}