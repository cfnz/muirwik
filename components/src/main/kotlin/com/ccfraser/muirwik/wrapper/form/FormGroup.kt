package com.ccfraser.muirwik.wrapper.form

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/FormGroup")
private external val formGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formGroupComponent: RComponent<MFormGroupProps, RState> = formGroupModule.default

/**
 * From material-ui: FormGroup wraps controls such as Checkbox and Switch. It provides compact row layout. For the Radio,
 * you should be using the RadioGroup component instead of this one.
 */
interface MFormGroupProps : StyledProps {
    var row: Boolean
}

fun RBuilder.mFormGroup (
        row: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MFormGroupProps>? = null) = createStyled(formGroupComponent) {
    attrs.row = row

    setStyledPropsAndRunHandler(className,  handler)
}
