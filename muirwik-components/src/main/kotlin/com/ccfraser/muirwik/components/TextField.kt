package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.*
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledElementBuilder
import styled.StyledHandler


@JsModule("@material-ui/core/TextField")
private external val textFieldDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val textFieldComponent: RComponent<MTextFieldProps, RState> = textFieldDefault.default

@Suppress("EnumEntryName")
enum class MTextFieldColor {
    primary, secondary
}

interface MTextFieldProps : MFormControlProps {
    var autoComplete: String
    var autoFocus: Boolean
    var defaultValue: String

    @JsName("FormHelperTextProps")
    var formHelperTextProps: RProps

    var helperText: String

    @JsName("InputLabelProps")
    var inputLabelProps: RProps

    @JsName("InputProps")
    var inputProps: RProps

    //    var inputRef	func		Use that property to pass a ref callback to the native input component.

    var label: String

    @JsName("inputProps")
    var nativeInputProps: RProps

    var multiline: Boolean
    var name: String
    var onChange: (event: Event) -> Unit
    var placeholder: String
    var rows: Int
    var rowsMax: Int
    var select: Boolean

    @JsName("SelectProps")
    var selectProps: dynamic

    var type: String
    var value: String
}
var MTextFieldProps.color by EnumPropToString(MTextFieldColor.values())

/**
 * From Material-UI: The TextField wrapper component is a complete form control including a label, input and help text.
 * TextField is composed of smaller components ( FormControl, Input, FilledInput, InputLabel, OutlinedInput,
 * and FormHelperText ) that you can leverage directly to significantly customize your form inputs.
 */
fun RBuilder.mTextField(
        label: String,
        value: String? = null,
        helperText: String? = null,
        defaultValue: String? = null,
        placeholder: String? = null,
        variant: MFormControlVariant = MFormControlVariant.standard,
        onChange: ((event: Event) -> Unit)? = null,
        type: InputType = InputType.text,
        required: Boolean = false,
        disabled: Boolean = false,
        error: Boolean = false,
        autoFocus: Boolean = false,
        fullWidth: Boolean = false,
        margin: MFormControlMargin = MFormControlMargin.normal,

        autoComplete: String? = null,
        id: String? = null,
        name: String? = null,

        className: String? = null,
        handler: StyledHandler<MTextFieldProps>? = null) = createStyled(textFieldComponent) {

    setAttributes(this, autoComplete, autoFocus, defaultValue, disabled, error, fullWidth, helperText, id, label, margin,
        false, name, onChange, placeholder, required, null, null, false, type, value, variant)

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mTextFieldMultiLine(
        label: String,
        value: String? = null,
        helperText: String? = null,
        defaultValue: String? = null,
        placeholder: String? = null,
        variant: MFormControlVariant = MFormControlVariant.standard,
        onChange: ((event: Event) -> Unit)? = null,
        required: Boolean = false,
        disabled: Boolean = false,
        error: Boolean = false,
        autoFocus: Boolean = false,
        fullWidth: Boolean = false,

        margin: MFormControlMargin = MFormControlMargin.normal,
        rows: Int? = null,
        rowsMax: Int? = null,

        id: String? = null,
        name: String? = null,

        className: String? = null,
        handler: StyledHandler<MTextFieldProps>? = null) = createStyled(textFieldComponent) {

    setAttributes(this, null, autoFocus, defaultValue, disabled, error, fullWidth, helperText, id, label, margin,
        true, name, onChange, placeholder, required, rows, rowsMax, false, InputType.text, value, variant)

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * I don't know why there is a text field select when there is a select... but here you go...
 */
fun RBuilder.mTextFieldSelect(
        label: String,
        value: String? = null,
        helperText: String? = null,
        defaultValue: String? = null,
        placeholder: String? = null,
        variant: MFormControlVariant = MFormControlVariant.standard,
        onChange: ((event: Event) -> Unit)? = null,
        required: Boolean = false,
        disabled: Boolean = false,
        error: Boolean = false,
        autoFocus: Boolean = false,
        fullWidth: Boolean = false,

        margin: MFormControlMargin = MFormControlMargin.normal,

        autoComplete: String? = null,
        id: String? = null,
        name: String? = null,

        className: String? = null,
        handler: StyledHandler<MTextFieldProps>? = null) = createStyled(textFieldComponent) {

    setAttributes(this, autoComplete, autoFocus, defaultValue, disabled, error, fullWidth, helperText, id, label, margin,
        false, name, onChange, placeholder, required, null, null, true, InputType.text, value, variant)

    setStyledPropsAndRunHandler(className, handler)
}

private fun setAttributes(
    textField: StyledElementBuilder<MTextFieldProps>,
    autoComplete: String?,
    autoFocus: Boolean,
    defaultValue: String?,
    disabled: Boolean,
    error: Boolean,
    fullWidth: Boolean,
    helperText: String?,
    id: String?,
    label: String,
    margin: MFormControlMargin,
    multiline: Boolean,
    name: String?,
    onChange: ((event: Event) -> Unit)?,
    placeholder: String?,
    required: Boolean,
    rows: Int?,
    rowsMax: Int?,
    select: Boolean,
    type: InputType,
    value: String?,
    variant: MFormControlVariant
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
    rowsMax?.let { textField.attrs.rowsMax = it }
    textField.attrs.select = select
    textField.attrs.type = type.realValue
    value?.let { textField.attrs.value = it }
    textField.attrs.variant = variant
}