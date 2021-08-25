package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.MFormControlLabelProps
import com.ccfraser.muirwik.components.form.MLabelPlacement
import com.ccfraser.muirwik.components.form.mFormControlLabel
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@material-ui/core/Checkbox")
private external val checkboxModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val checkboxComponentType: ComponentType<MCheckboxProps> = checkboxModule.default


external interface MCheckboxProps : StyledPropsWithCommonAttributes {
    var checked: Boolean
    var checkedIcon: ReactElement
    var disabled: Boolean
    var disableRipple: Boolean
    var icon: ReactElement?
    var indeterminate: Boolean
    var indeterminateIcon: ReactElement
    var inputProps: Props?
    var onChange: ((Event, Boolean) -> Unit)
    var required: Boolean
    var type: String
    var value: String
}
var MCheckboxProps.color by EnumPropToString(MOptionColor.values())

/**
 * Checkbox without a label. If you want a simple checkbox that is wrapped in a label, use [mCheckboxWithLabel]
 */
fun RBuilder.mCheckbox(
        checked: Boolean = false,
        color: MOptionColor = MOptionColor.secondary,
        disabled: Boolean = false,
        required: Boolean? = null,
        indeterminate: Boolean = false,
        onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
        id: String? = null,
        inputProps: Props? = null,
        value: String? = null,
        className: String? = null,
        handler: StyledHandler<MCheckboxProps>? = null
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

/**
 * A label with checkbox built in. Note, if you want to style the checkbox or label separately you will have to use
 * mFormControlLabel and pass in a mCheckbox.
 */
fun RBuilder.mCheckboxWithLabel(
    label: String,
    checked: Boolean = false,
    color: MOptionColor = MOptionColor.secondary,
    disabled: Boolean = false,
    required: Boolean? = null,
    indeterminate: Boolean = false,
    labelPlacement: MLabelPlacement = MLabelPlacement.end,
    onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
    id: String? = null,
    inputProps: Props? = null,
    value: String? = null,
    className: String? = null,
    handler: StyledHandler<MFormControlLabelProps>? = null
) {
    val checkBox = buildElement {
        mCheckbox(checked, color, disabled, required, indeterminate, onChange, id, inputProps, value, className)
    }

    mFormControlLabel(label, checkBox, checked, disabled, value = value, labelPlacement = labelPlacement, className = className, handler = handler)
}
