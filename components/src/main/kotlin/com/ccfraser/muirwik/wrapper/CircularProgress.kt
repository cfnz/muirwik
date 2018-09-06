package com.ccfraser.muirwik.wrapper

import kotlinx.css.LinearDimension
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/CircularProgress")
private external val circularProgressModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val circularProgressComponent: RComponent<MCircularProgressProps, RState> = circularProgressModule.default

@Suppress("EnumEntryName")
enum class MCircularProgressColor {
    primary, secondary, inherit
}

@Suppress("EnumEntryName")
enum class MCircularProgressVariant {
    determinate, indeterminate, static
}

interface MCircularProgressProps : StyledProps {
    var color: String
    var size: String
    var thickness: Double
    var value: Double
    var variant: String
}

fun RBuilder.mCircularProgress(
        value: Double? = null,
        variant: MCircularProgressVariant = MCircularProgressVariant.indeterminate,
        size: LinearDimension = 40.px,
        color: MCircularProgressColor = MCircularProgressColor.primary,
        thickness: Double = 3.6,

        className: String? = null,
        handler: StyledHandler<MCircularProgressProps>? = null) = createStyled(circularProgressComponent) {
    attrs.color = color.toString()
    attrs.size = size.toString()
    attrs.thickness = thickness
    value?.let { attrs.value = it }
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

