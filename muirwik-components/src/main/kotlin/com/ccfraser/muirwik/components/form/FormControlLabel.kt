package com.ccfraser.muirwik.components.form

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.setStyledPropsAndRunHandler
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler

@JsModule("@material-ui/core/FormControlLabel")
private external val formControlLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formControlLabelComponentType: ComponentType<MFormControlLabelProps> = formControlLabelModule.default

@Suppress("EnumEntryName")
enum class MLabelPlacement {
    end, start, top, bottom
}

external interface MFormControlLabelProps : StyledPropsWithCommonAttributes {
    var checked: Boolean
    var control: ReactElement
    var disabled: Boolean
    //    var inputRef	func		Use that property to pass a ref callback to the native input component.
    var label: String
    var name: String?
    var onChange: ((Event, Boolean) -> Unit)
    var value: String
}
var MFormControlLabelProps.labelPlacement by EnumPropToString(MLabelPlacement.values())

fun RBuilder.mFormControlLabel (
        label: String,
        control: ReactElement,
        checked: Boolean? = null,
        disabled: Boolean = false,
        value: String? = null,
        name: String? = null,
        labelPlacement: MLabelPlacement = MLabelPlacement.end,
        onChange: ((Event, Boolean) -> Unit)? = null,

        className: String? = null,
        handler: StyledHandler<MFormControlLabelProps>? = null) = createStyled(formControlLabelComponentType) {
    checked?.let { attrs.checked = checked }
    attrs.control = control
    attrs.disabled = disabled
    attrs.label = label
    attrs.labelPlacement = labelPlacement
    attrs.name = name
    onChange?.let { attrs.onChange = onChange }
    value?.let { attrs.value = value }

    setStyledPropsAndRunHandler(className, handler)
}
