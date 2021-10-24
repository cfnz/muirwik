package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler

@JsModule("@mui/material/FormControlLabel")
private external val formControlLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formControlLabelComponentType: ComponentType<FormControlLabelProps> = formControlLabelModule.default

@Suppress("EnumEntryName")
enum class LabelPlacement {
    end, start, top, bottom
}

external interface FormControlLabelProps : StyledPropsWithCommonAttributes {
    var checked: Boolean
    var control: ReactElement
    var componentProps: Props
    var disabled: Boolean
    var disableTypography: Boolean
    var inputRef: Ref<InputProps>
    var label: String
    var onChange: ((Event, Boolean) -> Unit)
    var value: String
}
var FormControlLabelProps.labelPlacement by EnumPropToString(LabelPlacement.values())

fun RBuilder.formControlLabel (
    label: String,
    value: String,
    control: ReactElement,
    handler: StyledHandler<FormControlLabelProps>? = null
) {
    createStyled(formControlLabelComponentType, handler) {
        attrs.control = control
        attrs.label = label
        attrs.value = value
    }
}

fun RBuilder.formControlLabel (
    label: String,
    control: ReactElement,
    handler: StyledHandler<FormControlLabelProps>? = null
) {
    createStyled(formControlLabelComponentType, handler) {
        attrs.control = control
        attrs.label = label
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mFormControlLabel (
    label: String,
    control: ReactElement,
    checked: Boolean? = null,
    disabled: Boolean = false,
    value: String? = null,
    labelPlacement: LabelPlacement = LabelPlacement.end,
    onChange: ((Event, Boolean) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<FormControlLabelProps>? = null
) {
    createStyled(formControlLabelComponentType, className, handler) {
        checked?.let { attrs.checked = checked }
        attrs.control = control
        attrs.disabled = disabled
        attrs.label = label
        attrs.labelPlacement = labelPlacement
        onChange?.let { attrs.onChange = onChange }
        value?.let { attrs.value = value }
    }
}
