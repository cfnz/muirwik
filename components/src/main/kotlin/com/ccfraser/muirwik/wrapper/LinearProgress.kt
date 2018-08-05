package com.ccfraser.muirwik.wrapper

import kotlinx.css.LinearDimension
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/LinearProgress")
private external val linearProgressModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val linearProgressComponent: RComponent<MLinearProgressProps, RState> = linearProgressModule.default

enum class MLinearProgressColor {
    Primary, Secondary;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class MLinearProgressVariant {
    Determinate, Indeterminate, Buffer, Query;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
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
        variant: MLinearProgressVariant = MLinearProgressVariant.Indeterminate,
        color: MLinearProgressColor = MLinearProgressColor.Primary,

        className: String? = null,
        handler: StyledHandler<MLinearProgressProps>? = null) = createStyled(linearProgressComponent) {
    attrs.color = color.toString()
    value?.let { attrs.value = it }
    valueBuffer?.let { attrs.valueBuffer = it }
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}

