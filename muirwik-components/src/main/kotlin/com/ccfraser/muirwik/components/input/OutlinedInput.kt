package com.ccfraser.muirwik.components.input

import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@material-ui/core/OutlinedInput")
private external val outlinedInputModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val outlinedInputComponent: RComponent<MOutlinedInputProps, RState> = outlinedInputModule.default

interface MOutlinedInputProps : MInputBaseProps {
    var labelWidth: Number
    var notched: Boolean
}

fun RBuilder.mOutlinedInput(
        value: String? = null,
        required: Boolean? = null,
        disabled: Boolean? = null,
        readOnly: Boolean? = null,
        error: Boolean? = null,
        fullWidth: Boolean = false,
        defaultValue: String? = null,
        placeholder: String? = null,
        startAdornment: ReactElement? = null,
        endAdornment: ReactElement? = null,
        labelWidth: Number? = null,
        notched: Boolean? = null,
        autoFocus: Boolean? = null,
        type: InputType = InputType.text,
        id: String? = null,
        margin: MInputMargin? = null,
        autoComplete: String? = null,
        inputComponent: String? = null,
        inputProps: RProps? = null,
        inputRef: RRef? = null,
        multiline: Boolean = false,
        rows: Int? = null,
        rowsMax: Int? = null,
        name: String? = null,
        onChange: ((Event) -> Unit)? = null,

        addAsChild: Boolean = true,
        className: String? = null,

        handler: StyledHandler<MOutlinedInputProps>? = null) = createStyled(outlinedInputComponent, addAsChild) {
    autoComplete?.let { attrs.autoComplete = it }
    autoFocus?.let{ attrs.autoFocus = it }
    defaultValue?.let { attrs.defaultValue = it }
    disabled?.let { attrs.disabled = it }
    endAdornment?.let { attrs.endAdornment = it }
    error?.let { attrs.error = it }
    attrs.fullWidth = fullWidth
    id?.let { attrs.id = it }
    inputComponent?.let { attrs.inputComponent = it }
    inputProps?.let { attrs.inputProps = it }
    inputRef?.let { attrs.inputRef = it }
    labelWidth?.let { attrs.labelWidth = it }
    margin?.let { attrs.margin = it.toString().toLowerCase() }
    attrs.multiline = multiline
    name?.let { attrs.name = it }
    notched?.let { attrs.notched = it }
    onChange?.let { attrs.onChange = it }
    placeholder?.let { attrs.placeholder = it }
    readOnly?.let { attrs.readOnly = it }
    required?.let { attrs.required = it }
    rows?.let { attrs.rows = it }
    rowsMax?.let { attrs.rowsMax = it }
    startAdornment?.let { attrs.startAdornment = it }
    attrs.type = type.toString()
    value?.let { attrs.value = it }

    setStyledPropsAndRunHandler(className, handler)
}





