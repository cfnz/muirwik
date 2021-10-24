package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/RadioGroup")
private external val radioGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val radioGroupComponentType: ComponentType<RadioGroupProps> = radioGroupModule.default

external interface RadioGroupProps : FormGroupProps {
    var defaultValue: String
    var name: String
    var onChange: ((Event, String) -> Unit)
    var value: String
}

fun RBuilder.radioGroup(value: String? = null, name: String? = null, handler: StyledHandler<RadioGroupProps>? = null) {
    createStyled(radioGroupComponentType, handler) {
        name?.let { attrs.name = name }
        value?.let { attrs.value = value }
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mRadioGroup(
    value: String? = null,
    row: Boolean = false,
    onChange: ((event: Event, value: String) -> Unit)? = null,
    name: String? = null,

    className: String? = null,
    handler: StyledHandler<RadioGroupProps>? = null
) {
    createStyled(radioGroupComponentType, className, handler) {
        name?.let { attrs.name = name }
        onChange?.let { attrs.onChange = onChange }
        attrs.row = row
        value?.let { attrs.value = value }
    }
}
