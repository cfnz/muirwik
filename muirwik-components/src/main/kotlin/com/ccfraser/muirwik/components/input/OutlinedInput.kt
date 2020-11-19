package com.ccfraser.muirwik.components.input

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/OutlinedInput")
private external val outlinedInputModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val outlinedInputComponent: RComponent<MOutlinedInputProps, RState> = outlinedInputModule.default

external interface MOutlinedInputProps : MInputBaseProps {
    var labelWidth: Number
    var notched: Boolean
}

/**
 * An outlined input control that can be used by itself, note that [com.ccfraser.muirwik.components.mTextField] wraps this
 * and has more functionality.
 */
fun RBuilder.mOutlinedInput(
        value: String? = null,
        required: Boolean? = null,
        disabled: Boolean? = null,
        readOnly: Boolean? = null,
        error: Boolean? = null,
        fullWidth: Boolean = false,
        defaultValue: String? = null,
        placeholder: String? = null,
        labelWidth: Number? = null,
        notched: Boolean? = null,
        autoFocus: Boolean? = null,
        type: InputType = InputType.text,
        id: String? = null,
        name: String? = null,
        multiline: Boolean = false,
        rows: Int? = null,
        rowsMax: Int? = null,
        onChange: ((Event) -> Unit)? = null,

        addAsChild: Boolean = true,
        className: String? = null,

        handler: StyledHandler<MOutlinedInputProps>? = null) = createStyled(outlinedInputComponent, addAsChild) {
    autoFocus?.let{ attrs.autoFocus = it }
    defaultValue?.let { attrs.defaultValue = it }
    disabled?.let { attrs.disabled = it }
    error?.let { attrs.error = it }
    attrs.fullWidth = fullWidth
    id?.let { attrs.id = it }
    labelWidth?.let { attrs.labelWidth = it }
    attrs.multiline = multiline
    name?.let { attrs.name = it }
    notched?.let { attrs.notched = it }
    onChange?.let { attrs.onChange = it }
    placeholder?.let { attrs.placeholder = it }
    readOnly?.let { attrs.readOnly = it }
    required?.let { attrs.required = it }
    rows?.let { attrs.rows = it }
    rowsMax?.let { attrs.rowsMax = it }
    attrs.type = type
    value?.let { attrs.value = it }

    setStyledPropsAndRunHandler(className, handler)
}





