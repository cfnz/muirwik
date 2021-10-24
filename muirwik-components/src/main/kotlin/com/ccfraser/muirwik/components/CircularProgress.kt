package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.LinearDimensionDelegate
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinx.css.LinearDimension
import kotlinx.css.px
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/CircularProgress")
private external val circularProgressModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val circularProgressComponentType: ComponentType<CircularProgressProps> = circularProgressModule.default

@Suppress("EnumEntryName")
enum class CircularProgressColor {
    inherit, primary, secondary, error, info, success, warning
}

@Suppress("EnumEntryName")
enum class CircularProgressVariant {
    determinate, indeterminate, static
}

external interface CircularProgressProps : StyledProps {
    var disableShrink: Boolean
    var thickness: Double
    var value: Double
}
var CircularProgressProps.color by EnumPropToString(CircularProgressColor.values())
var CircularProgressProps.variant by EnumPropToString(CircularProgressVariant.values())
var CircularProgressProps.size by LinearDimensionDelegate()

fun RBuilder.circularProgress(handler: StyledHandler<CircularProgressProps>) {
    createStyled(circularProgressComponentType, handler)
}

fun RBuilder.circularProgress(
    value: Double? = null,
    color: CircularProgressColor = CircularProgressColor.primary,
    variant: CircularProgressVariant = CircularProgressVariant.indeterminate,
    size: LinearDimension = 40.px,
    handler: StyledHandler<CircularProgressProps>? = null
) {
    createStyled(circularProgressComponentType, handler) {
        attrs.color = color
        attrs.size = size
        attrs.variant = variant
        value?.let { attrs.value = it }
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mCircularProgress(
    value: Double? = null,
    variant: CircularProgressVariant = CircularProgressVariant.indeterminate,
    size: LinearDimension = 40.px,
    color: CircularProgressColor = CircularProgressColor.primary,
    thickness: Double = 3.6,
    disableShrink: Boolean = false,
    className: String? = null,
    handler: StyledHandler<CircularProgressProps>? = null
) {
    createStyled(circularProgressComponentType, className, handler) {
        attrs.color = color
        attrs.disableShrink = disableShrink
        attrs.size = size
        attrs.thickness = thickness
        value?.let { attrs.value = it }
        attrs.variant = variant
    }
}
