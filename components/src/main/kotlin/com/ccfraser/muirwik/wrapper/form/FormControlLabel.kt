package com.ccfraser.muirwik.wrapper.form

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import styled.StyledHandler
import styled.StyledProps

@JsModule("@material-ui/core/FormControlLabel")
private external val formControlLabelModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formControlLabelComponent: RComponent<MFormControlLabelProps, RState> = formControlLabelModule.default

@Suppress("EnumEntryName")
enum class MLabelPlacement {
    end, start, top, bottom
}

interface MFormControlLabelProps : StyledProps {
    var checked: Boolean
    var control: ReactElement
    var disabled: Boolean
    //    var inputRef	func		Use that property to pass a ref callback to the native input component.
    var label: String
    var labelPlacement: String
    var name: String?
    var onChange: ((Event, Boolean) -> Unit)
    var value: String
}

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
        handler: StyledHandler<MFormControlLabelProps>? = null) = createStyled(formControlLabelComponent) {
    checked?.let { attrs.checked = checked }
    attrs.control = control
    attrs.disabled = disabled
    attrs.label = label
    attrs.labelPlacement = labelPlacement.toString()
    attrs.name = name
    onChange?.let { attrs.onChange = onChange }
    value?.let { attrs.value = value }

    setStyledPropsAndRunHandler(className, handler)
}

