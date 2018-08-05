package com.ccfraser.muirwik.wrapper

import com.ccfraser.muirwik.wrapper.form.MFormControlLabelProps
import com.ccfraser.muirwik.wrapper.form.mFormControlLabel
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Switch")
private external val switchDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val switchComponent: RComponent<MSwitchProps, RState> = switchDefault.default

interface MSwitchProps : StyledProps {
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

fun RBuilder.mSwitch(
        checked: Boolean = false,
        primary: Boolean = true,
        disabled: Boolean = false,
        icon: ReactElement? = null,
        checkedIcon: ReactElement? = null,
        onChange: ((Event, Boolean) -> Unit)? = null,
        disableRipple: Boolean = false,
        id: String? = null,
        inputProps: RProps? = null,
        value: String? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MSwitchProps>? = null) = createStyled(switchComponent, addAsChild) {
    attrs.checked = checked
    checkedIcon?.let { attrs.checkedIcon = checkedIcon }
    attrs.color = if (primary) MColor.Primary.toString() else MColor.Secondary.toString()
    attrs.disabled = disabled
    attrs.disableRipple = disableRipple
    icon?.let { attrs.icon = icon }
    id?.let { attrs.id = id }
    inputProps?.let { attrs.inputProps = inputProps }
    onChange?.let { attrs.onChange = onChange }
    attrs.type = InputType.checkBox.toString().toHyphenCase()
    value?.let {attrs.value = value}

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * A label with switch built in. Note, if you want to style the switch or label separately you will have to use
 * mFormControlLabel and pass in a mSwitch.
 */
fun RBuilder.mSwitchInLabel(
        label: String,
        checked: Boolean = false,
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
    val switch = mSwitch(checked, primary, disabled, icon, checkedIcon, onChange, disableRipple, id,
            inputProps, value, false)

    return mFormControlLabel(label, switch, checked, disabled, value = value, className = className, handler = handler)
}
