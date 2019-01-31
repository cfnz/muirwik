package com.ccfraser.muirwik.components

import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/LinearProgress")
private external val linearProgressModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val linearProgressComponent: RComponent<MLinearProgressProps, RState> = linearProgressModule.default

@Suppress("EnumEntryName")
enum class MLinearProgressColor {
    primary, secondary
}

@Suppress("EnumEntryName")
enum class MLinearProgressVariant {
    determinate, indeterminate, buffer, query
}

interface MLinearProgressProps : StyledProps {
    var color: String
    var value: Double
    var valueBuffer: Double
    var variant: String
}

fun RBuilder.mLinearProgress(
        value: Double? = null,
        valueBuffer: Double? = null,
        variant: MLinearProgressVariant = MLinearProgressVariant.indeterminate,
        color: MLinearProgressColor = MLinearProgressColor.primary,

        className: String? = null,
        handler: StyledHandler<MLinearProgressProps>? = null) = createStyled(linearProgressComponent) {
    attrs.color = color.toString()
    value?.let { attrs.value = it }
    valueBuffer?.let { attrs.valueBuffer = it }
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

