package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Breakpoint
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Container")
private external val containerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val containerComponent: RComponent<MContainerProps, RState> = containerModule.default

external interface MContainerProps : StyledProps {
    var component: String
    var disableGutters: Boolean
    var fixed: Boolean
    var maxWidth: Any
}

/**
 * Basic Container layout component.
 * Note: Setting maxWidth to null will disable maxWidth (i.e. pass false to underlying Material UI)
 */
fun RBuilder.mContainer(
        maxWidth: Breakpoint? = Breakpoint.lg,
        fixed: Boolean = false,
        disableGutters: Boolean = false,
        component: String = "div",

        className: String? = null,
        handler: StyledHandler<MContainerProps>? = null) = createStyled(containerComponent) {
    attrs.component = component
    attrs.disableGutters = disableGutters
    attrs.fixed = fixed
    attrs.maxWidth = maxWidth?.toString() ?: false

    setStyledPropsAndRunHandler(className, handler)
}

