package com.ccfraser.muirwik.components.transitions

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.LinearDimensionDelegate
import com.ccfraser.muirwik.components.createStyled
import kotlinx.css.LinearDimension
import kotlinx.css.px
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Collapse")
private external val collapseModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val collapseComponentType: ComponentType<MCollapseProps> = collapseModule.default

public enum class MCollapseOrientation {
    horizontal, vertical
}

external interface MCollapseProps : MTransitionProps {
    var addEndListener: () -> Unit
    var component: String
}
var MCollapseProps.timeout by TransitionDurationWithAutoDelegate()
var MCollapseProps.orientation by EnumPropToString(MCollapseOrientation.values())
var MCollapseProps.collapsedSize by LinearDimensionDelegate()


fun RBuilder.mCollapse(
    show: Boolean = false,
    collapsedSize: LinearDimension = 0.px,
    component: String = "div",
    timeout: TransitionDurationWithAuto? = null,
    className: String? = null,
    handler: StyledHandler<MCollapseProps>? = null
) {
    createStyled(collapseComponentType, className, handler) {
        attrs.collapsedSize = collapsedSize
        attrs.component = component
        attrs.show = show
        timeout?.let { attrs.timeout = it }
    }
}

