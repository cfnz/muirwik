package com.ccfraser.muirwik.wrapper.input

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@material-ui/core/FilledInput")
private external val filledInputModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val filledInputComponent: RComponent<MFilledInputProps, RState> = filledInputModule.default

interface MFilledInputProps : MInputBaseProps {
    // No extra props
}

fun RBuilder.mFilledInput(
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

        handler: StyledHandler<MFilledInputProps>? = null) = createStyled(filledInputComponent, addAsChild) {
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
    margin?.let { attrs.margin = it.toString().toLowerCase() }
    attrs.multiline = multiline
    name?.let { attrs.name = it }
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





