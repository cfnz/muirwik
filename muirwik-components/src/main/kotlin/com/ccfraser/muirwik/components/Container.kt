package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.utils.BreakpointNullToFalseDelegate
import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Container")
private external val containerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val containerComponentType: ComponentType<ContainerProps> = containerModule.default



external interface ContainerProps : StyledProps {
    var component: ElementType
    var disableGutters: Boolean
    var fixed: Boolean
}
var ContainerProps.maxWidth by BreakpointNullToFalseDelegate()


/**
 * Basic Container layout component.
 * Note: Setting maxWidth to null will disable maxWidth (i.e. pass false to underlying Material UI)
 */
fun RBuilder.container(
    maxWidth: Breakpoint? = Breakpoint.lg,
    fixed: Boolean = false,
    handler: StyledHandler<ContainerProps>? = null
) {
    createStyled(containerComponentType, handler) {
        attrs.fixed = fixed
        attrs.maxWidth = maxWidth
    }
}


