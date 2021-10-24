package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/LinearProgress")
private external val linearProgressModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val linearProgressComponentType: ComponentType<LinearProgressProps> = linearProgressModule.default

@Suppress("EnumEntryName")
enum class LinearProgressColor {
    inherit, primary, secondary
}

@Suppress("EnumEntryName")
enum class LinearProgressVariant {
    buffer, determinate, indeterminate, query
}

external interface LinearProgressProps : StyledProps {
    var value: Double
    var valueBuffer: Double
}
var LinearProgressProps.color by EnumPropToString(LinearProgressColor.values())
var LinearProgressProps.variant by EnumPropToString(LinearProgressVariant.values())


fun RBuilder.linearProgress(
    value: Double? = null,
    color: LinearProgressColor = LinearProgressColor.primary,
    variant: LinearProgressVariant = LinearProgressVariant.indeterminate,
    handler: StyledHandler<LinearProgressProps>? = null
) {
    createStyled(linearProgressComponentType, handler) {
        attrs.color = color
        value?.let { attrs.value = it }
        attrs.variant = variant
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mLinearProgress(
    value: Double? = null,
    valueBuffer: Double? = null,
    variant: LinearProgressVariant = LinearProgressVariant.indeterminate,
    color: LinearProgressColor = LinearProgressColor.primary,

    className: String? = null,
    handler: StyledHandler<LinearProgressProps>? = null
) {
    createStyled(linearProgressComponentType, className, handler) {
        attrs.color = color
        value?.let { attrs.value = it }
        valueBuffer?.let { attrs.valueBuffer = it }
        attrs.variant = variant
    }
}

