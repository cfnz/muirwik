package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactNode
import styled.StyledHandler


@JsModule("@mui/material/OutlinedInput")
private external val outlinedInputModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val outlinedInputComponentType: ComponentType<OutlinedInputProps> = outlinedInputModule.default

external interface OutlinedInputProps : InputBaseProps {
    var label: ReactNode
    var notched: Boolean
}

fun RBuilder.outlinedInput(handler: StyledHandler<OutlinedInputProps>) {
    createStyled(outlinedInputComponentType, handler)
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
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
    notched: Boolean? = null,
    autoFocus: Boolean? = null,
    type: InputType = InputType.text,
    id: String? = null,
    name: String? = null,
    multiline: Boolean = false,
    rows: Int? = null,
    maxRows: Int? = null,
    onChange: ((Event) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<OutlinedInputProps>? = null
) {
    createStyled(outlinedInputComponentType, className, handler) {
        autoFocus?.let { attrs.autoFocus = it }
        defaultValue?.let { attrs.defaultValue = it }
        disabled?.let { attrs.disabled = it }
        error?.let { attrs.error = it }
        attrs.fullWidth = fullWidth
        id?.let { attrs.id = it }
        attrs.multiline = multiline
        name?.let { attrs.name = it }
        notched?.let { attrs.notched = it }
        onChange?.let { attrs.onChange = it }
        placeholder?.let { attrs.placeholder = it }
        readOnly?.let { attrs.readOnly = it }
        required?.let { attrs.required = it }
        rows?.let { attrs.rows = it }
        maxRows?.let { attrs.maxRows = it }
        attrs.type = type
        value?.let { attrs.value = it }
    }
}





