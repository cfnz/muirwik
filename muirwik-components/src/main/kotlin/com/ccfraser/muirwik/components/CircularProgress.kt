package com.ccfraser.muirwik.components

import kotlinx.css.LinearDimension
import kotlinx.css.px
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/CircularProgress")
private external val circularProgressModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val circularProgressComponentType: ComponentType<MCircularProgressProps> = circularProgressModule.default

@Suppress("EnumEntryName")
enum class MCircularProgressColor {
    primary, secondary, inherit
}

@Suppress("EnumEntryName")
enum class MCircularProgressVariant {
    determinate, indeterminate, static
}

external interface MCircularProgressProps : StyledProps {
    var disableShrink: Boolean
    var thickness: Double
    var value: Double
}
var MCircularProgressProps.color by EnumPropToString(MCircularProgressColor.values())
var MCircularProgressProps.variant by EnumPropToString(MCircularProgressVariant.values())
var MCircularProgressProps.size: LinearDimension
    get() = LinearDimension(this.asDynamic().size as String)
    set(value) {
        this.asDynamic().size = value.toString()
    }

fun RBuilder.mCircularProgress(
    value: Double? = null,
    variant: MCircularProgressVariant = MCircularProgressVariant.indeterminate,
    size: LinearDimension = 40.px,
    color: MCircularProgressColor = MCircularProgressColor.primary,
    thickness: Double = 3.6,
    disableShrink: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MCircularProgressProps>? = null
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
