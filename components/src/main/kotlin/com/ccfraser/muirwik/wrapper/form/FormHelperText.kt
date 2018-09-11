package com.ccfraser.muirwik.wrapper.form

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
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
    var margin: String
    var required: Boolean
}

fun RBuilder.mFormHelperText (
        caption: String,
        disabled: Boolean = false,
        error: Boolean = false,
        filled: Boolean = false,
        focused: Boolean = false,
        required: Boolean = false,
        margin: MLabelMargin? = null,
        component: String? = null,

        className: String? = null,
        handler: StyledHandler<MFormHelperTextProps>? = null) = createStyled(formHelperTextComponent) {
    component?.let { attrs.component = it }
    attrs.disabled = disabled
    attrs.error = error
    attrs.filled = filled
    attrs.focused = focused
    margin?.let { attrs.margin = it.toString() }
    attrs.required = required

    childList.add(caption)
    setStyledPropsAndRunHandler(className,  handler)
}
