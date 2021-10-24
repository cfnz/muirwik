package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.LinearDimensionDelegate
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinx.css.LinearDimension
import kotlinx.css.px
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Collapse")
private external val collapseModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val collapseComponentType: ComponentType<CollapseProps> = collapseModule.default

enum class CollapseOrientation {
    horizontal, vertical
}

external interface CollapseProps : TransitionProps {
    var addEndListener: () -> Unit
    var component: String
}
var CollapseProps.timeout by TransitionDurationWithAutoDelegate()
var CollapseProps.orientation by EnumPropToString(CollapseOrientation.values())
var CollapseProps.collapsedSize by LinearDimensionDelegate()


fun RBuilder.collapse(show: Boolean = false, handler: StyledHandler<CollapseProps>) {
    createStyled(collapseComponentType, handler) {
        attrs.show = show
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mCollapse(
    show: Boolean = false,
    collapsedSize: LinearDimension = 0.px,
    component: String = "div",
    timeout: TransitionDurationWithAuto? = null,
    className: String? = null,
    handler: StyledHandler<CollapseProps>? = null
) {
    createStyled(collapseComponentType, className, handler) {
        attrs.collapsedSize = collapsedSize
        attrs.component = component
        attrs.show = show
        timeout?.let { attrs.timeout = it }
    }
}

