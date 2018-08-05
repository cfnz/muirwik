package com.ccfraser.muirwik.wrapper.form

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/FormLabel")
private external val formLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formLabelComponent: RComponent<MFormLabelProps, RState> = formLabelModule.default

interface MFormLabelProps : StyledProps {
    var component: String
    var disabled: Boolean
    var error: Boolean
    var focused: Boolean
    var required: Boolean
}

fun RBuilder.mFormLabel (
        caption: String,
        component: String = "label",
        disabled: Boolean = false,
        error: Boolean = false,
        focused: Boolean = false,
        required: Boolean = false,

        className: String? = null,
        handler: StyledHandler<MFormLabelProps>? = null) = createStyled(formLabelComponent) {
    attrs.component = component
    attrs.disabled = disabled
    attrs.error = error
    attrs.focused = focused
    attrs.required = required

    childList.add(caption)
    setStyledPropsAndRunHandler(className,  handler)
}
