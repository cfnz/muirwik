package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import com.ccfraser.muirwik.components.utils.toHyphenCase
import react.ComponentType
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/FormControl")
private external val formControlModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formControlComponentType: ComponentType<FormControlProps> = formControlModule.default

@Suppress("EnumEntryName")
enum class FormControlVariant {
    filled, outlined, standard
}

@Suppress("EnumEntryName")
enum class FormControlColor {
    primary, secondary, error, info, success, warning
}

@Suppress("EnumEntryName")
enum class FormControlSize {
    medium, small
}

/**
 * Div and FieldSet seem to be the most used values for this prop, so we shall enum these... if you need something
 * else, you will have to use the attrs directly.
 */
@Suppress("EnumEntryName")
enum class FormControlComponent {
    div, fieldSet;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

@Suppress("EnumEntryName")
enum class FormControlMargin {
    dense, none, normal
}

external interface FormControlProps : StyledPropsWithCommonAttributes {
    var disabled: Boolean
    var error: Boolean
    var focused: Boolean
    var fullWidth: Boolean
    var hiddenLabel: Boolean
    var required: Boolean
}
var FormControlProps.color by EnumPropToString(FormControlColor.values())
var FormControlProps.margin by EnumPropToString(FormControlMargin.values())
var FormControlProps.component by EnumPropToString(FormControlComponent.values())
var FormControlProps.size by EnumPropToString(FormControlSize.values())
var FormControlProps.variant by EnumPropToString(FormControlVariant.values())


fun RBuilder.formControl(variant: FormControlVariant = FormControlVariant.standard, handler: StyledHandler<FormControlProps>) {
    createStyled(formControlComponentType, handler) {
        attrs.variant = variant
    }
}

@Deprecated("Use the simpler version with attrs (params will mainly be used for required attributes).")
fun RBuilder.mFormControl(
    component: FormControlComponent = FormControlComponent.div,
    disabled: Boolean = false,
    error: Boolean = false,
    fullWidth: Boolean = false,
    margin: FormControlMargin = FormControlMargin.none,
    required: Boolean = false,
    variant: FormControlVariant = FormControlVariant.standard,
    hiddenLabel: Boolean = false,
    className: String? = null,
    handler: StyledHandler<FormControlProps>? = null
) {
    createStyled(formControlComponentType, className, handler) {
        attrs.component = component
        attrs.disabled = disabled
        attrs.error = error
        attrs.fullWidth = fullWidth
        attrs.hiddenLabel = hiddenLabel
        attrs.margin = margin
        attrs.required = required
        attrs.variant = variant
    }
}
