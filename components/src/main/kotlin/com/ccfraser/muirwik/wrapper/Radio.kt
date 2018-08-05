package com.ccfraser.muirwik.wrapper

import com.ccfraser.muirwik.wrapper.form.MFormControlLabelProps
import com.ccfraser.muirwik.wrapper.form.mFormControlLabel
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Radio")
private external val radioModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val radioComponent: RComponent<MRadioProps, RState> = radioModule.default

interface MRadioProps : StyledProps {
    var checked: Boolean
    var checkedIcon: ReactElement?
    var color: String
    var disabled: Boolean
    var disableRipple: Boolean
    var icon: ReactElement?
    var id: String?
    var inputProps: RProps?
    var onChange: ((Event, Boolean) -> Unit)?
    var type: String
    var value: String?
}

fun RBuilder.mRadio(
        checked: Boolean? = null,
        primary: Boolean = true,
        disabled: Boolean = false,
        icon: ReactElement? = null,
        checkedIcon: ReactElement? = null,
        onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
        disableRipple: Boolean = false,
        id: String? = null,
        inputProps: RProps? = null,
        value: String? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MRadioProps>? = null) = createStyled(radioComponent, addAsChild) {
    checked?.let { attrs.checked = it }
    checkedIcon?.let { attrs.checkedIcon = it }
    attrs.color = if (primary) MColor.Primary.toString() else MColor.Secondary.toString()
    attrs.disabled = disabled
    attrs.disableRipple = disableRipple
    icon?.let { attrs.icon = it }
    id?.let { attrs.id = it }
    inputProps?.let { attrs.inputProps = it }
    onChange?.let {attrs.onChange = it }
    value?.let { attrs.value = it }

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * A label with a radio built in. Note, if you want to style the radio or label separately you will have to use
 * mFormControlLabel and pass in an mRadio.
 */
fun RBuilder.mRadioInLabel(
        label: String,
        checked: Boolean? = null,
        primary: Boolean = true,
        disabled: Boolean = false,
        icon: ReactElement? = null,
        checkedIcon: ReactElement? = null,
        onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
        disableRipple: Boolean = false,
        id: String? = null,
        inputProps: RProps? = null,
        value: String? = null,

        className: String? = null,
        handler: StyledHandler<MFormControlLabelProps>? = null): ReactElement {
    val radio = mRadio(checked, primary, disabled, icon, checkedIcon, onChange, disableRipple, id, inputProps, value, false)
    return mFormControlLabel(label, radio, checked, disabled, value = value, className = className, handler = handler)
}


@JsModule("@material-ui/core/RadioGroup")
private external val radioGroupModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val radioGroupComponent: RComponent<MRadioGroupProps, RState> = radioGroupModule.default

interface MRadioGroupProps : StyledProps {
    var name: String?
    var onChange: ((Event, String) -> Unit)?
    var value: String?
}

fun RBuilder.mRadioGroup(
        value: String? = null,
        onChange: ((event: Event, value: String) -> Unit)? = null,
        name: String? = null,

        className: String? = null,
        handler: StyledHandler<MRadioGroupProps>? = null) = createStyled(radioGroupComponent) {
    name?.let { attrs.name = name }
    onChange?.let { attrs.onChange = onChange }
    value?.let { attrs.value = value }

    setStyledPropsAndRunHandler(className, handler)
}

