package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.*
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
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
    autoComplete?.let { attrs.autoComplete = it }
    attrs.autoFocus = autoFocus
    defaultValue?.let { attrs.defaultValue = defaultValue }
    attrs.disabled = disabled
    attrs.error = error
    attrs.fullWidth = fullWidth
    helperText?.let { attrs.helperText = helperText }
    id?.let { attrs.id = it }
    attrs.label = label
    attrs.margin = margin
    attrs.multiline = false
    name?.let { attrs.name = it }
    onChange?.let { attrs.onChange = onChange }
    placeholder?.let { attrs.placeholder = placeholder }
    attrs.required = required
    attrs.select = false
    attrs.type = type.toString()
    value?.let { attrs.value = value }
    attrs.variant = variant

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
    attrs.autoFocus = autoFocus
    defaultValue?.let { attrs.defaultValue = defaultValue }
    attrs.disabled = disabled
    attrs.error = error
    attrs.fullWidth = fullWidth
    helperText?.let { attrs.helperText = helperText }
    id?.let { attrs.id = it }
    attrs.label = label
    attrs.margin = margin
    attrs.multiline = true
    name?.let { attrs.name = it }
    onChange?.let { attrs.onChange = onChange }
    placeholder?.let { attrs.placeholder = placeholder }
    attrs.required = required
    rows?.let { attrs.rows = rows }
    rowsMax?.let { attrs.rowsMax = rowsMax }
    attrs.select = false
    attrs.type = InputType.text.toString()
    value?.let { attrs.value = value }
    attrs.variant = variant

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
    autoComplete?.let { attrs.autoComplete = it }
    attrs.autoFocus = autoFocus
    defaultValue?.let { attrs.defaultValue = defaultValue }
    attrs.disabled = disabled
    attrs.error = error
    attrs.fullWidth = fullWidth
    helperText?.let { attrs.helperText = helperText }
    id?.let { attrs.id = it }
    attrs.label = label
    attrs.margin = margin
    attrs.multiline = true
    name?.let { attrs.name = it }
    onChange?.let { attrs.onChange = onChange }
    placeholder?.let { attrs.placeholder = placeholder }
    attrs.required = required
    attrs.select = true
    attrs.type = InputType.text.toString()
    value?.let { attrs.value = value }
    attrs.variant = variant

    setStyledPropsAndRunHandler(className, handler)
}

