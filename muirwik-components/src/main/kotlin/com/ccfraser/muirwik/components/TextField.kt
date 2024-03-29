package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinx.html.InputType
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.ComponentType
import react.Props
import react.RBuilder
import react.Ref
import styled.StyledElementBuilder
import styled.StyledHandler


@JsModule("@mui/material/TextField")
private external val textFieldDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val textFieldComponentType: ComponentType<TextFieldProps> = textFieldDefault.default

external interface TextFieldProps : FormControlProps {
    var autoComplete: String
    var autoFocus: Boolean
    var defaultValue: String

    @JsName("FormHelperTextProps")
    var formHelperTextProps: Props

    var helperText: String

    @JsName("InputLabelProps")
    var inputLabelProps: Props

    @JsName("InputProps")
    var inputProps: Props

    var inputRef: Ref<Node>

    var label: String

    @JsName("inputProps")
    var nativeInputProps: Props

    var maxRows: Int
    var minRows: Int
    var multiline: Boolean
    var name: String
    var onChange: (event: Event) -> Unit
    var placeholder: String
    var rows: Int
    var select: Boolean

    @JsName("SelectProps")
    var selectProps: dynamic

    var type: String
    var value: String
}
var TextFieldProps.color by EnumPropToString(FormControlColor.values())


/**
 * From Material-UI: The TextField wrapper component is a complete form control including a label, input and help text.
 * TextField is composed of smaller components ( FormControl, Input, FilledInput, InputLabel, OutlinedInput,
 * and FormHelperText ) that you can leverage directly to significantly customize your form inputs.
 */
fun RBuilder.textField(
    label: String,
    value: String? = null,
    helperText: String? = null,
    defaultValue: String? = null,
    placeholder: String? = null,
    color: FormControlColor = FormControlColor.primary,
    variant: FormControlVariant = FormControlVariant.standard,
    handler: StyledHandler<TextFieldProps>? = null
) {
    createStyled(textFieldComponentType, handler) {
        attrs.color = color
        defaultValue?.let { attrs.defaultValue = it }
        helperText?.let { attrs.helperText = it }
        attrs.label = label
        placeholder?.let { attrs.placeholder = it }
        value?.let { attrs.value = it }
        attrs.variant = variant
    }
}


/**
 * From Material-UI: The TextField wrapper component is a complete form control including a label, input and help text.
 * TextField is composed of smaller components ( FormControl, Input, FilledInput, InputLabel, OutlinedInput,
 * and FormHelperText ) that you can leverage directly to significantly customize your form inputs.
 */
@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mTextField(
    label: String,
    value: String? = null,
    helperText: String? = null,
    defaultValue: String? = null,
    placeholder: String? = null,
    variant: FormControlVariant = FormControlVariant.standard,
    onChange: ((event: Event) -> Unit)? = null,
    type: InputType = InputType.text,
    required: Boolean = false,
    disabled: Boolean = false,
    error: Boolean = false,
    autoFocus: Boolean = false,
    fullWidth: Boolean = false,
    margin: FormControlMargin = FormControlMargin.normal,
    autoComplete: String? = null,
    id: String? = null,
    name: String? = null,
    className: String? = null,
    handler: StyledHandler<TextFieldProps>? = null
) {
    createStyled(textFieldComponentType, className, handler) {
        setAttributes(this, autoComplete, autoFocus, defaultValue, disabled, error, fullWidth, helperText, id, label, margin,
            false, name, onChange, placeholder, required, null, null, false, type, value, variant)
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mTextFieldMultiLine(
    label: String,
    value: String? = null,
    helperText: String? = null,
    defaultValue: String? = null,
    placeholder: String? = null,
    variant: FormControlVariant = FormControlVariant.standard,
    onChange: ((event: Event) -> Unit)? = null,
    required: Boolean = false,
    disabled: Boolean = false,
    error: Boolean = false,
    autoFocus: Boolean = false,
    fullWidth: Boolean = false,
    margin: FormControlMargin = FormControlMargin.normal,
    rows: Int? = null,
    maxRows: Int? = null,
    id: String? = null,
    name: String? = null,
    className: String? = null,
    handler: StyledHandler<TextFieldProps>? = null
) {
    createStyled(textFieldComponentType, className, handler) {
        setAttributes(this, null, autoFocus, defaultValue, disabled, error, fullWidth, helperText, id, label, margin,
            true, name, onChange, placeholder, required, rows, maxRows, false, InputType.text, value, variant)
    }
}

/**
 * I don't know why there is a text field select when there is a select... but here you go...
 */
@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mTextFieldSelect(
    label: String,
    value: String? = null,
    helperText: String? = null,
    defaultValue: String? = null,
    placeholder: String? = null,
    variant: FormControlVariant = FormControlVariant.standard,
    onChange: ((event: Event) -> Unit)? = null,
    required: Boolean = false,
    disabled: Boolean = false,
    error: Boolean = false,
    autoFocus: Boolean = false,
    fullWidth: Boolean = false,
    margin: FormControlMargin = FormControlMargin.normal,
    autoComplete: String? = null,
    id: String? = null,
    name: String? = null,
    className: String? = null,
    handler: StyledHandler<TextFieldProps>? = null
) {
    createStyled(textFieldComponentType, className, handler) {
        setAttributes(this, autoComplete, autoFocus, defaultValue, disabled, error, fullWidth, helperText, id, label, margin,
            false, name, onChange, placeholder, required, null, null, true, InputType.text, value, variant)
    }
}

private fun setAttributes(
    textField: StyledElementBuilder<TextFieldProps>,
    autoComplete: String?,
    autoFocus: Boolean,
    defaultValue: String?,
    disabled: Boolean,
    error: Boolean,
    fullWidth: Boolean,
    helperText: String?,
    id: String?,
    label: String,
    margin: FormControlMargin,
    multiline: Boolean,
    name: String?,
    onChange: ((event: Event) -> Unit)?,
    placeholder: String?,
    required: Boolean,
    rows: Int?,
    maxRows: Int?,
    select: Boolean,
    type: InputType,
    value: String?,
    variant: FormControlVariant
) {
    autoComplete?.let { textField.attrs.autoComplete = it }
    textField.attrs.autoFocus = autoFocus
    defaultValue?.let { textField.attrs.defaultValue = it }
    textField.attrs.disabled = disabled
    textField.attrs.error = error
    textField.attrs.fullWidth = fullWidth
    helperText?.let { textField.attrs.helperText = it }
    id?.let { textField.attrs.id = it }
    textField.attrs.label = label
    textField.attrs.margin = margin
    textField.attrs.multiline = multiline
    name?.let { textField.attrs.name = it }
    onChange?.let { textField.attrs.onChange = it }
    placeholder?.let { textField.attrs.placeholder = it }
    textField.attrs.required = required
    rows?.let { textField.attrs.rows = it }
    maxRows?.let { textField.attrs.maxRows = it }
    textField.attrs.select = select
    textField.attrs.type = type.realValue
    value?.let { textField.attrs.value = it }
    textField.attrs.variant = variant
}