package com.ccfraser.muirwik.components.input

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/Input")
private external val inputModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val inputComponent: RComponent<MInputProps, RState> = inputModule.default

interface MInputProps : MInputBaseProps {
    var disableUnderline: Boolean
}

fun RBuilder.mInput(
        value: Any? = null,
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

        addAsChild: Boolean = true,
        className: String? = null,

        handler: StyledHandler<MInputProps>? = null) = createStyled(com.ccfraser.muirwik.components.input.inputComponent, addAsChild) {
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
    attrs.type = type.toString()
    value?.let { attrs.value = it }

    setStyledPropsAndRunHandler(className, handler)
    attrs.redefineInputBaseDefinedProps()
}





