package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/FormLabel")
private external val formLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formLabelComponentType: ComponentType<FormLabelProps> = formLabelModule.default

external interface FormLabelProps : StyledPropsWithCommonAttributes {
    var component: ElementType
    var disabled: Boolean
    var error: Boolean
    var filled: Boolean
    var focused: Boolean
    var htmlFor: String
    var required: Boolean
}
var FormLabelProps.color by EnumPropToString(FormControlColor.values())

fun RBuilder.formLabel (caption: String, htmlFor: String? = null, handler: StyledHandler<FormLabelProps>? = null) {
    createStyled(formLabelComponentType, handler) {
        htmlFor?.let { attrs.htmlFor = it }
        childList.add(ReactNode(caption))
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
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
    handler: StyledHandler<FormLabelProps>? = null
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
