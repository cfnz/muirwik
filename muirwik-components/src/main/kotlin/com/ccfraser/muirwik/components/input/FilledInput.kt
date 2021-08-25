package com.ccfraser.muirwik.components.input

import com.ccfraser.muirwik.components.createStyled
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/FilledInput")
private external val filledInputModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val filledInputComponentType: ComponentType<MFilledInputProps> = filledInputModule.default

external interface MFilledInputProps : MInputBaseProps {
    var disableUnderline: Boolean
}

/**
 * A filled input control that can be used by itself, note that [com.ccfraser.muirwik.components.mTextField] wraps this
 * and has more functionality.
 */
fun RBuilder.mFilledInput(
    value: String? = null,
    required: Boolean? = null,
    disabled: Boolean? = null,
    readOnly: Boolean? = null,
    error: Boolean? = null,
    fullWidth: Boolean = false,
    defaultValue: String? = null,
    placeholder: String? = null,
    disableUnderline: Boolean? = null,
    autoFocus: Boolean? = null,
    type: InputType = InputType.text,
    id: String? = null,
    name: String? = null,
    multiline: Boolean = false,
    rows: Int? = null,
    rowsMax: Int? = null,
    onChange: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<MFilledInputProps>? = null
) {
    createStyled(filledInputComponentType, className, handler) {
        autoFocus?.let{ attrs.autoFocus = it }
        defaultValue?.let { attrs.defaultValue = it }
        disabled?.let { attrs.disabled = it }
        disableUnderline?.let { attrs.disableUnderline = it }
        error?.let { attrs.error = it }
        attrs.fullWidth = fullWidth
        id?.let { attrs.id = it }
        attrs.multiline = multiline
        name?.let { attrs.name = it }
        onChange?.let { attrs.onChange = it }
        placeholder?.let { attrs.placeholder = it }
        readOnly?.let { attrs.readOnly = it }
        required?.let { attrs.required = it }
        rows?.let { attrs.rows = it }
        rowsMax?.let { attrs.rowsMax = it }
        attrs.type = type
        value?.let { attrs.value = it }
    }
}





