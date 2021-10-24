package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/FormGroup")
private external val formGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formGroupComponentType: ComponentType<FormGroupProps> = formGroupModule.default

/**
 * From material-ui: FormGroup wraps controls such as Checkbox and Switch. It provides compact row layout. For the Radio,
 * you should be using the RadioGroup component instead of this one.
 */
external interface FormGroupProps : StyledProps {
    var row: Boolean
}

fun RBuilder.mFormGroup (handler: StyledHandler<FormGroupProps>) {
    createStyled(formGroupComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mFormGroup (
    row: Boolean = false,
    className: String? = null,
    handler: StyledHandler<FormGroupProps>? = null
) {
    createStyled(formGroupComponentType, className, handler) {
        attrs.row = row
    }
}
