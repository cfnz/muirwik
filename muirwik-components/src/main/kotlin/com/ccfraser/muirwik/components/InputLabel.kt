package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.createStyled
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/InputLabel")
private external val inputLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val inputLabelComponentType: ComponentType<InputLabelProps> = inputLabelModule.default

external interface InputLabelProps : FormLabelProps {
    var disableAnimation: Boolean
    var shrink: Boolean
}
var InputLabelProps.margin by EnumPropToStringNullable(LabelMargin.values())
var InputLabelProps.variant by EnumPropToString(FormControlVariant.values())

fun RBuilder.inputLabel (
    caption: String,
    htmlFor: String? = null,
    variant: FormControlVariant = FormControlVariant.standard,
    handler: StyledHandler<InputLabelProps>? = null
) {
    createStyled(inputLabelComponentType, handler) {
        htmlFor?.let { attrs.htmlFor = it }
        attrs.variant = variant

        childList.add(ReactNode(caption))
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mInputLabel (
    caption: String,
    htmlFor: String? = null,
    required: Boolean? = null,
    disabled: Boolean? = null,
    error: Boolean? = null,
    focused: Boolean? = null,
    variant: FormControlVariant = FormControlVariant.standard,
    shrink: Boolean? = null,
    disableAnimation: Boolean = false,
    margin: LabelMargin? = null,
    component: String? = null,
    className: String? = null,
    handler: StyledHandler<InputLabelProps>? = null
) {
    createStyled(inputLabelComponentType, className, handler) {
        component?.let { attrs.component = it }
        disabled?.let { attrs.disabled = it }
        attrs.disableAnimation = disableAnimation
        htmlFor?.let { attrs.htmlFor = it }
        error?.let { attrs.error = it }
        focused?.let { attrs.focused = it }
        margin?.let { attrs.margin = it }
        required?.let { attrs.required = it }
        shrink?.let {
            // The input label acts strange if it is set to false, best not to set it
            // TODO: Perhaps look at the Material UI source and make a fix
            if (it) {
                attrs.shrink = it
            }
        }
        attrs.variant = variant

        childList.add(ReactNode(caption))
    }
}
