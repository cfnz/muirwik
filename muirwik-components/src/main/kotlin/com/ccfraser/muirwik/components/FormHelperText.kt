package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/FormHelperText")
private external val formHelperTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formHelperTextComponentType: ComponentType<FormHelperTextProps> = formHelperTextModule.default

external interface FormHelperTextProps : StyledProps {
    var component: ElementType
    var disabled: Boolean
    var error: Boolean
    var filled: Boolean
    var focused: Boolean
    var required: Boolean
}
var FormHelperTextProps.margin by EnumPropToStringNullable(LabelMargin.values())
var FormHelperTextProps.variant by EnumPropToString(FormControlVariant.values())

fun RBuilder.formHelperText(caption: String, variant: FormControlVariant = FormControlVariant.standard, handler: StyledHandler<FormHelperTextProps>? = null) {
    createStyled(formHelperTextComponentType, handler) {
        attrs.variant = variant
        childList.add(ReactNode(caption))
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mFormHelperText (
    caption: String,
    disabled: Boolean = false,
    error: Boolean = false,
    filled: Boolean = false,
    focused: Boolean = false,
    required: Boolean = false,
    variant: FormControlVariant = FormControlVariant.standard,
    margin: LabelMargin? = null,
    component: String? = null,
    className: String? = null,
    handler: StyledHandler<FormHelperTextProps>? = null
) {
    createStyled(formHelperTextComponentType, className, handler) {
        component?.let { attrs.component = it }
        attrs.disabled = disabled
        attrs.error = error
        attrs.filled = filled
        attrs.focused = focused
        attrs.margin = margin
        attrs.required = required
        attrs.variant = variant

        childList.add(ReactNode(caption))
    }
}
