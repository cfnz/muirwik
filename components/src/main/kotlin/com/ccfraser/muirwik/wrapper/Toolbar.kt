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

interface MToolbarProps : StyledProps {
    var disableGutters: Boolean
}

fun RBuilder.mToolbar(
        disableGutters: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MToolbarProps>? = null) = createStyled(toolbarComponent) {
    attrs.disableGutters = disableGutters

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * Just a simple title with padding to push any items to the right
 */
fun RBuilder.mToolbarTitle(text: String): ReactElement {
    return mTypography(text, variant = MTypographyVariant.Title, color = MTypographyColor.Inherit) { css { flexGrow = 1.0 }}
}