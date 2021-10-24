package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/InputAdornment")
private external val inputAdornmentDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val inputAdornmentComponentType: ComponentType<InputAdornmentProps> = inputAdornmentDefault.default

@Suppress("EnumEntryName")
enum class InputAdornmentPosition {
    start, end
}

external interface InputAdornmentProps : StyledProps {
    var disablePointerEvents: Boolean
    var disableTypography: Boolean
}
var InputAdornmentProps.position by EnumPropToStringNullable(InputAdornmentPosition.values())
var InputAdornmentProps.variant by EnumPropToStringNullable(FormControlVariant.values())

fun RBuilder.inputAdornment(
    text: String? = null,
    position: InputAdornmentPosition = InputAdornmentPosition.start,
    handler: StyledHandler<InputAdornmentProps>? = null
) {
    createStyled(inputAdornmentComponentType, handler) {
        attrs.position = position
        text?.let { child(ReactNode(it)) }
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mInputAdornment(
    position: InputAdornmentPosition = InputAdornmentPosition.start,
    disablePointerEvents: Boolean = false,
    disableTypography: Boolean = false,
    variant: FormControlVariant? = null,
    className: String? = null,
    handler: StyledHandler<InputAdornmentProps>? = null
) {
    createStyled(inputAdornmentComponentType, className, handler) {
        attrs.disablePointerEvents = disablePointerEvents
        attrs.disableTypography = disableTypography
        attrs.position = position
        attrs.variant = variant
    }
}





