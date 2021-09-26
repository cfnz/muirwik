package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.button.MIconButtonSize
import com.ccfraser.muirwik.components.form.MFormControlLabelProps
import com.ccfraser.muirwik.components.form.MFormGroupProps
import com.ccfraser.muirwik.components.form.MLabelPlacement
import com.ccfraser.muirwik.components.form.mFormControlLabel
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@mui/material/Radio")
private external val radioModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val radioComponentType: ComponentType<MRadioProps> = radioModule.default


external interface MRadioProps : StyledPropsWithCommonAttributes {
    var checked: Boolean
    var checkedIcon: ReactElement?
    var disabled: Boolean
    var disableRipple: Boolean
    var icon: ReactElement?
    var inputProps: Props?
    var onChange: ((Event, Boolean) -> Unit)?
    var required: Boolean
    var type: String
    var value: String?
}
var MRadioProps.color by EnumPropToString(MOptionColor.values())
var MRadioProps.size by EnumPropToString(MIconButtonSize.values())

fun RBuilder.mRadio(
    checked: Boolean? = null,
    color: MOptionColor = MOptionColor.secondary,
    disabled: Boolean = false,
    required: Boolean? = null,
    size: MIconButtonSize = MIconButtonSize.medium,
    onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
    id: String? = null,
    inputProps: Props? = null,
    value: String? = null,
    className: String? = null,
    handler: StyledHandler<MRadioProps>? = null
) {
    createStyled(radioComponentType, className, handler) {
        checked?.let { attrs.checked = it }
        attrs.color = color
        attrs.disabled = disabled
        id?.let { attrs.id = it }
        inputProps?.let { attrs.inputProps = it }
        onChange?.let { attrs.onChange = it }
        required?.let { attrs.required = it }
        attrs.size = size
        value?.let { attrs.value = it }
    }
}

/**
 * A label with a radio built in. Note, if you want to style the radio or label separately you will have to use
 * mFormControlLabel and pass in an mRadio.
 */
fun RBuilder.mRadioWithLabel(
    label: String,
    checked: Boolean? = null,
    color: MOptionColor = MOptionColor.secondary,
    disabled: Boolean = false,
    required: Boolean? = null,
    size: MIconButtonSize = MIconButtonSize.medium,
    labelPlacement: MLabelPlacement = MLabelPlacement.end,
    onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
    id: String? = null,
    inputProps: Props? = null,
    value: String? = null,
    className: String? = null,
    handler: StyledHandler<MFormControlLabelProps>? = null
) {
    val radio = buildElement { mRadio(checked, color, disabled, required, size, onChange, id, inputProps, value) }
    mFormControlLabel(label, radio, checked, disabled, value = value, labelPlacement = labelPlacement, className = className, handler = handler)
}


@JsModule("@mui/material/RadioGroup")
private external val radioGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val radioGroupComponentType: ComponentType<MRadioGroupProps> = radioGroupModule.default

external interface MRadioGroupProps : MFormGroupProps {
    var name: String?
    var onChange: ((Event, String) -> Unit)?
    var value: String?
}

fun RBuilder.mRadioGroup(
    value: String? = null,
    row: Boolean = false,
    onChange: ((event: Event, value: String) -> Unit)? = null,
    name: String? = null,

    className: String? = null,
    handler: StyledHandler<MRadioGroupProps>? = null
) {
    createStyled(radioGroupComponentType, className, handler) {
        name?.let { attrs.name = name }
        onChange?.let { attrs.onChange = onChange }
        attrs.row = row
        value?.let { attrs.value = value }
    }
}
