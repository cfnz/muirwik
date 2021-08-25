package com.ccfraser.muirwik.components.form

import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/FormGroup")
private external val formGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formGroupComponentType: ComponentType<MFormGroupProps> = formGroupModule.default

/**
 * From material-ui: FormGroup wraps controls such as Checkbox and Switch. It provides compact row layout. For the Radio,
 * you should be using the RadioGroup component instead of this one.
 */
external interface MFormGroupProps : StyledProps {
    var row: Boolean
}

fun RBuilder.mFormGroup (
    row: Boolean = false,
    className: String? = null,
    handler: StyledHandler<MFormGroupProps>? = null
) {
    createStyled(formGroupComponentType, className, handler) {
        attrs.row = row
    }
}
