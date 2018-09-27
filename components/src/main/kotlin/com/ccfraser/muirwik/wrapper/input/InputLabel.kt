package com.ccfraser.muirwik.wrapper.input

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.form.MFormControlVariant
import com.ccfraser.muirwik.wrapper.form.MFormLabelProps
import com.ccfraser.muirwik.wrapper.form.MLabelMargin
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
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

    @JsName("FormLabelClasses")
    var formLabelClasses: Any

    var margin: String
    var shrink: Boolean
    var variant: String
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
        formLabelClasses: Any? = null,

        className: String? = null,
        handler: StyledHandler<MInputLabelProps>? = null) = createStyled(inputLabelComponent) {
    component?.let { attrs.component = it }
    disabled?.let { attrs.disabled = it }
    attrs.disableAnimation = disableAnimation
    formLabelClasses?.let { attrs.formLabelClasses = it }
    htmlFor?.let { attrs.htmlFor = it }
    error?.let { attrs.error = it }
    focused?.let { attrs.focused = it }
    margin?.let { attrs.margin = it.toString() }
    required?.let { attrs.required = it }
    shrink?.let {
        // The input label acts strange if it is set to false, best not to set it
        // TODO: Perhaps look at the Material UI source and make a fix
        if (it) {
            attrs.shrink = it
        }
    }
    attrs.variant = variant.toString()

    childList.add(caption)
    setStyledPropsAndRunHandler(className,  handler)
}
