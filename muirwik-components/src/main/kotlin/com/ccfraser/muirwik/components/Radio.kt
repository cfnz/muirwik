package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ControlColor
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinx.html.INPUT
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@mui/material/Radio")
private external val radioModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val radioComponentType: ComponentType<RadioProps> = radioModule.default


external interface RadioProps : StyledPropsWithCommonAttributes {
    var checked: Boolean
    var checkedIcon: ReactElement
    var disabled: Boolean
    var disableRipple: Boolean
    var icon: ReactElement
    var inputProps: Props
    var inputRef: Ref<INPUT>
    var name: String
    var onChange: ((Event, Boolean) -> Unit)
    var required: Boolean
    var value: String
}
var RadioProps.color by EnumPropToString(ControlColor.values())
var RadioProps.size by EnumPropToString(MIconButtonSize.values())

fun RBuilder.radio(
    checked: Boolean? = null,
    color: ControlColor = ControlColor.secondary,
    value: String? = null,
    handler: StyledHandler<RadioProps>? = null
) {
    createStyled(radioComponentType, handler) {
        checked?.let { attrs.checked = it }
        attrs.color = color
        value?.let { attrs.value = it }
    }
}

/**
 * A label with a radio built in. Note, if you want to style the radio or label separately you will have to use
 * mFormControlLabel and pass in an mRadio.
 */
fun RBuilder.radioWithLabel(
    label: String,
    checked: Boolean? = null,
    value: String? = null,
    color: ControlColor = ControlColor.secondary,
    handler: StyledHandler<FormControlLabelProps>? = null
) {
    val radio = buildElement { radio(checked, color, value) }
    if (value == null) {
        formControlLabel(label, radio, handler = handler)
    } else {
        formControlLabel(label, value, radio, handler = handler)
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mRadio(
    checked: Boolean? = null,
    color: ControlColor = ControlColor.secondary,
    disabled: Boolean = false,
    required: Boolean? = null,
    size: MIconButtonSize = MIconButtonSize.medium,
    onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
    id: String? = null,
    inputProps: Props? = null,
    value: String? = null,
    className: String? = null,
    handler: StyledHandler<RadioProps>? = null
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

@Deprecated("Use the simpler 'non m' version.")
@Suppress("DEPRECATION")
/**
 * A label with a radio built in. Note, if you want to style the radio or label separately you will have to use
 * mFormControlLabel and pass in an mRadio.
 */
fun RBuilder.mRadioWithLabel(
    label: String,
    checked: Boolean? = null,
    color: ControlColor = ControlColor.secondary,
    disabled: Boolean = false,
    required: Boolean? = null,
    size: MIconButtonSize = MIconButtonSize.medium,
    labelPlacement: LabelPlacement = LabelPlacement.end,
    onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
    id: String? = null,
    inputProps: Props? = null,
    value: String? = null,
    className: String? = null,
    handler: StyledHandler<FormControlLabelProps>? = null
) {
    val radio = buildElement { mRadio(checked, color, disabled, required, size, onChange, id, inputProps, value) }
    mFormControlLabel(label, radio, checked, disabled, value = value, labelPlacement = labelPlacement, className = className, handler = handler)
}
