package com.ccfraser.muirwik.components.input

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.EnumPropToStringNullable
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.MFormLabelProps
import com.ccfraser.muirwik.components.form.MLabelMargin
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/InputLabel")
private external val inputLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val inputLabelComponentType: ComponentType<MInputLabelProps> = inputLabelModule.default

external interface MInputLabelProps : MFormLabelProps {
    var disableAnimation: Boolean
    var shrink: Boolean
}
var MInputLabelProps.margin by EnumPropToStringNullable(MLabelMargin.values())
var MInputLabelProps.variant by EnumPropToString(MFormControlVariant.values())


fun RBuilder.mInputLabel (
    caption: String,
    htmlFor: String? = null,
    required: Boolean? = null,
    disabled: Boolean? = null,
    error: Boolean? = null,
    focused: Boolean? = null,
    variant: MFormControlVariant = MFormControlVariant.standard,
    shrink: Boolean? = null,
    disableAnimation: Boolean = false,
    margin: MLabelMargin? = null,
    component: String? = null,
    className: String? = null,
    handler: StyledHandler<MInputLabelProps>? = null
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
