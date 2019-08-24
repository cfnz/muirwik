package com.ccfraser.muirwik.components.form

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/FormHelperText")
private external val formHelperTextModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formHelperTextComponent: RComponent<MFormHelperTextProps, RState> = formHelperTextModule.default

interface MFormHelperTextProps : StyledProps {
    var component: String
    var disabled: Boolean
    var error: Boolean
    var filled: Boolean
    var focused: Boolean
    var margin: MLabelMargin
    var required: Boolean
    var variant: MFormControlVariant
}

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
        handler: StyledHandler<MFormHelperTextProps>? = null) = createStyled(formHelperTextComponent) {
    component?.let { attrs.component = it }
    attrs.disabled = disabled
    attrs.error = error
    attrs.filled = filled
    attrs.focused = focused
    margin?.let { attrs.margin = it }
    attrs.required = required
    attrs.variant = variant

    childList.add(caption)
    setStyledPropsAndRunHandler(className,  handler)
    if (attrs.margin != undefined) attrs.asDynamic().margin = margin.toString()
    attrs.asDynamic().variant = attrs.variant.toString()
}
