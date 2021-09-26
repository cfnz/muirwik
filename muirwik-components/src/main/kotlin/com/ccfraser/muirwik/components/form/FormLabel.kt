package com.ccfraser.muirwik.components.form

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/FormLabel")
private external val formLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formLabelComponentType: ComponentType<MFormLabelProps> = formLabelModule.default

external interface MFormLabelProps : StyledPropsWithCommonAttributes {
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
    handler: StyledHandler<MFormLabelProps>? = null
) {
    createStyled(formLabelComponentType, className, handler) {
        attrs.component = component
        disabled?.let { attrs.disabled = it }
        error?.let { attrs.error = it }
        filled?.let { attrs.filled = it }
        focused?.let { attrs.focused = it }
        htmlFor?.let { attrs.htmlFor = it }
        required?.let { attrs.required = it }

        childList.add(ReactNode(caption))
    }
}
