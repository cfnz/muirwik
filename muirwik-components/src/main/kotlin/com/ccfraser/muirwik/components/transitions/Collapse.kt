package com.ccfraser.muirwik.components.transitions

import com.ccfraser.muirwik.components.createStyled
import kotlinx.css.LinearDimension
import kotlinx.css.px
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/Collapse")
private external val collapseModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val collapseComponentType: ComponentType<MCollapseProps> = collapseModule.default

external interface MCollapseProps : MTransitionProps {
    var collapsedHeight: String
    var component: String
}
var MCollapseProps.timeout by TransitionDurationWithAutoDelegate()

fun RBuilder.mCollapse(
        show: Boolean = false,
        collapsedHeight: LinearDimension = 0.px,
        component: String = "div",
        timeout: TransitionDurationWithAuto? = null,
        className: String? = null,
        handler: StyledHandler<MCollapseProps>? = null
) {
    createStyled(collapseComponentType, className, handler) {
        attrs.collapsedHeight = collapsedHeight.toString()
        attrs.component = component
        attrs.show = show
        timeout?.let { attrs.timeout = it }
    }
}

