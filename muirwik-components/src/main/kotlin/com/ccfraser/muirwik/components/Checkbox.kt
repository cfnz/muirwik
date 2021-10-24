package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ControlColor
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@mui/material/Checkbox")
private external val checkboxModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val checkboxComponentType: ComponentType<CheckboxProps> = checkboxModule.default

enum class CheckboxSize {
    medium, small
}

external interface CheckboxProps : StyledPropsWithCommonAttributes {
    var checked: Boolean
    var checkedIcon: ReactNode
    var defaultChecked: Boolean
    var disabled: Boolean
    var disableRipple: Boolean
    var icon: ReactNode?
    var indeterminate: Boolean
    var indeterminateIcon: ReactNode
    var inputProps: Props
    val inputRef: Ref<InputType>
    var onChange: ((Event, Boolean) -> Unit)
    var required: Boolean
    var type: String
    var value: String
}
var CheckboxProps.color by EnumPropToString(ControlColor.values())
var CheckboxProps.size by EnumPropToString(CheckboxSize.values())

fun RBuilder.checkbox(
    checked: Boolean = false,
    color: ControlColor = ControlColor.secondary,
    handler: StyledHandler<CheckboxProps>? = null
) {
    createStyled(checkboxComponentType, handler) {
        attrs.checked = checked
        attrs.color = color
    }
}


/**
 * A label with checkbox built in. Note, if you want to style the checkbox or label separately you will have to use
 * formControlLabel and pass in a checkbox.
 */
fun RBuilder.checkboxWithLabel(
    label: String,
    checked: Boolean = false,
    color: ControlColor = ControlColor.secondary,
    handler: StyledHandler<FormControlLabelProps>? = null
) {
    val checkBox = buildElement {
        checkbox(checked, color)
    }

    formControlLabel(label, checkBox, handler)
}

@Deprecated("Use the simpler 'non m' version.")
/**
 * Checkbox without a label. If you want a simple checkbox that is wrapped in a label, use [mCheckboxWithLabel]
 */
fun RBuilder.mCheckbox(
    checked: Boolean = false,
    color: ControlColor = ControlColor.secondary,
    disabled: Boolean = false,
    required: Boolean? = null,
    indeterminate: Boolean = false,
    onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
    id: String? = null,
    inputProps: Props? = null,
    value: String? = null,
    className: String? = null,
    handler: StyledHandler<CheckboxProps>? = null
) {
     createStyled(checkboxComponentType, className, handler) {
         attrs.checked = checked
         attrs.color = color
         attrs.disabled = disabled
         required?.let { attrs.required = it }
         id?.let { attrs.id = id }
         attrs.indeterminate = indeterminate
         inputProps?.let { attrs.inputProps = inputProps }
         onChange?.let { attrs.onChange = onChange }
         attrs.type = InputType.checkBox.realValue
         value?.let { attrs.value = value }
     }
}

@Deprecated("Use the simpler 'non m' version.")
/**
 * A label with checkbox built in. Note, if you want to style the checkbox or label separately you will have to use
 * mFormControlLabel and pass in a mCheckbox.
 */
fun RBuilder.mCheckboxWithLabel(
    label: String,
    checked: Boolean = false,
    color: ControlColor = ControlColor.secondary,
    disabled: Boolean = false,
    required: Boolean? = null,
    indeterminate: Boolean = false,
    labelPlacement: LabelPlacement = LabelPlacement.end,
    onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
    id: String? = null,
    inputProps: Props? = null,
    value: String? = null,
    className: String? = null,
    handler: StyledHandler<FormControlLabelProps>? = null
) {
    val checkBox = buildElement {
        @Suppress("DEPRECATION")
        mCheckbox(checked, color, disabled, required, indeterminate, onChange, id, inputProps, value, className)
    }

    @Suppress("DEPRECATION")
    mFormControlLabel(label, checkBox, checked, disabled, value = value, labelPlacement = labelPlacement, className = className, handler = handler)
}
