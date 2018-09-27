package com.ccfraser.muirwik.wrapper.input

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.form.MFormControlVariant
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
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

interface MInputAdornmentProps : StyledProps {
    var disableTypography: Boolean
    var position: String
    var variant: String
}

fun RBuilder.mInputAdornment(
        position: MInputAdornmentPosition = MInputAdornmentPosition.start,
        disableTypography: Boolean = false,
        variant: MFormControlVariant = MFormControlVariant.standard,

        className: String? = null,

        handler: StyledHandler<MInputAdornmentProps>? = null) = createStyled(inputAdornmentComponent, false) {
    attrs.disableTypography = disableTypography
    attrs.position = position.toString().toLowerCase()
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}





