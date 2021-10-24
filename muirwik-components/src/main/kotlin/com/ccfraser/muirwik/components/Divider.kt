package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/Divider")
private external val dividerModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val dividerComponentType: ComponentType<DividerProps> = dividerModule.default

@Suppress("EnumEntryName")
enum class DividerOrientation {
    horizontal, vertical
}
@Suppress("EnumEntryName")
enum class DividerTextAlign {
    center, left, right
}

@Suppress("EnumEntryName")
enum class DividerVariant {
    fullWidth, inset, middle
}

external interface DividerProps : StyledProps {
    var absolute: Boolean
    var component: ElementType
    var flexItem: Boolean
    var light: Boolean
}

var DividerProps.orientation by EnumPropToString(DividerOrientation.values())
var DividerProps.textAlign by EnumPropToString(DividerTextAlign.values())
var DividerProps.variant by EnumPropToString(DividerVariant.values())

fun RBuilder.divider(
    orientation: DividerOrientation = DividerOrientation.horizontal,
    variant: DividerVariant = DividerVariant.fullWidth,
    handler: StyledHandler<DividerProps>? = null
) {
    createStyled(dividerComponentType, handler) {
        attrs.orientation = orientation
        attrs.variant = variant
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.divider(
    orientation: DividerOrientation = DividerOrientation.horizontal,
    variant: DividerVariant = DividerVariant.fullWidth,
    light: Boolean = false,
    handler: StyledHandler<DividerProps>? = null
) = createStyled(dividerComponentType, handler) {
    attrs.light = light
    attrs.orientation = orientation
    attrs.variant = variant
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mDivider(
    variant: DividerVariant = DividerVariant.fullWidth,
    light: Boolean = false,
    absolute: Boolean = false,
    orientation: DividerOrientation = DividerOrientation.horizontal,
    component: String = "hr",
    className: String? = null,
    handler: StyledHandler<DividerProps>? = null
) = createStyled(dividerComponentType, className, handler) {
    attrs.absolute = absolute
    attrs.component = component
    attrs.light = light
    attrs.orientation = orientation
    attrs.variant = variant
}
