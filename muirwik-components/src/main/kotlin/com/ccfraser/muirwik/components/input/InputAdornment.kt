package com.ccfraser.muirwik.components.input

import com.ccfraser.muirwik.components.EnumPropToStringNullable
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/InputAdornment")
private external val inputAdornmentDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val inputAdornmentComponent: RComponent<MInputAdornmentProps, RState> = inputAdornmentDefault.default

@Suppress("EnumEntryName")
enum class MInputAdornmentPosition {
    start, end
}

external interface MInputAdornmentProps : StyledProps {
    var disablePointerEvents: Boolean
    var disableTypography: Boolean
}
var MInputAdornmentProps.position by EnumPropToStringNullable(MInputAdornmentPosition.values())
var MInputAdornmentProps.variant by EnumPropToStringNullable(MFormControlVariant.values())

fun RBuilder.mInputAdornment(
        position: MInputAdornmentPosition = MInputAdornmentPosition.start,
        disablePointerEvents: Boolean = false,
        disableTypography: Boolean = false,
        variant: MFormControlVariant? = null,

        className: String? = null,

        handler: StyledHandler<MInputAdornmentProps>? = null) = createStyled(inputAdornmentComponent, false) {
    attrs.disablePointerEvents = disablePointerEvents
    attrs.disableTypography = disableTypography
    attrs.position = position
    attrs.variant = variant

    setStyledPropsAndRunHandler(className, handler)
}





