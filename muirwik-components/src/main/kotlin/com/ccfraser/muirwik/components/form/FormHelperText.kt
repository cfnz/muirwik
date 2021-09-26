package com.ccfraser.muirwik.components.form

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.EnumPropToStringNullable
import com.ccfraser.muirwik.components.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler
import styled.StyledProps


@JsModule("@mui/material/FormHelperText")
private external val formHelperTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formHelperTextComponentType: ComponentType<MFormHelperTextProps> = formHelperTextModule.default

external interface MFormHelperTextProps : StyledProps {
    var component: String
    var disabled: Boolean
    var error: Boolean
    var filled: Boolean
    var focused: Boolean
    var required: Boolean
}
var MFormHelperTextProps.margin by EnumPropToStringNullable(MLabelMargin.values())
var MFormHelperTextProps.variant by EnumPropToString(MFormControlVariant.values())

fun RBuilder.mFormHelperText (
    caption: String,
    disabled: Boolean = false,
    error: Boolean = false,
    filled: Boolean = false,
    focused: Boolean = false,
    required: Boolean = false,
    variant: MFormControlVariant = MFormControlVariant.standard,
    margin: MLabelMargin? = null,
    component: String? = null,
    className: String? = null,
    handler: StyledHandler<MFormHelperTextProps>? = null
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
