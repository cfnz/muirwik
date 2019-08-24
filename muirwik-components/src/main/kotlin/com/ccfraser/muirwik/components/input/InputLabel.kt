package com.ccfraser.muirwik.components.input

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.MFormLabelProps
import com.ccfraser.muirwik.components.form.MLabelMargin
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/InputLabel")
private external val inputLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val inputLabelComponent: RComponent<MInputLabelProps, RState> = inputLabelModule.default

interface MInputLabelProps : MFormLabelProps {
    var disableAnimation: Boolean

    var margin: MLabelMargin
    var shrink: Boolean
    var variant: MFormControlVariant
}

private fun MInputLabelProps.redefineTypedProps() {
    if (margin != undefined) this.asDynamic().margin = margin.toString()
    this.asDynamic().variant = variant.toString()
}

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
        handler: StyledHandler<MInputLabelProps>? = null) = createStyled(inputLabelComponent) {
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

    childList.add(caption)
    setStyledPropsAndRunHandler(className,  handler)
    attrs.redefineTypedProps()
}
