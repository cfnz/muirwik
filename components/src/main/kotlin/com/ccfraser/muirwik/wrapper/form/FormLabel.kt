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
    var filled: Boolean
    var focused: Boolean
    var htmlFor: String
    var required: Boolean
}

fun RBuilder.mFormLabel (
        caption: String,
        htmlFor: String? = null,
        required: Boolean? = null,
        disabled: Boolean? = null,
        error: Boolean? = null,
        focused: Boolean? = null,
        filled: Boolean? = null,
        component: String = "label",
        className: String? = null,
        handler: StyledHandler<MFormLabelProps>? = null) = createStyled(formLabelComponent) {
    attrs.component = component
    disabled?.let { attrs.disabled = it }
    error?.let { attrs.error = it }
    filled?.let { attrs.filled = it }
    focused?.let { attrs.focused = it }
    htmlFor?.let { attrs.htmlFor = it }
    required?.let { attrs.required = it }

    childList.add(caption)
    setStyledPropsAndRunHandler(className,  handler)
}
