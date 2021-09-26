package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.button.MIconButtonSize
import com.ccfraser.muirwik.components.form.MFormControlLabelProps
import com.ccfraser.muirwik.components.form.mFormControlLabel
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@mui/material/Switch")
private external val switchDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val switchComponentType: ComponentType<MSwitchProps> = switchDefault.default

@Suppress("EnumEntryName")
enum class MSwitchEdge {
    start, end // We assume if the prop is null, then the default false will be used, so we don't have this as a value
}

external interface MSwitchProps : StyledPropsWithCommonAttributes {
    var checked: Boolean
    var checkedIcon: ReactElement
    var disabled: Boolean
    var disableRipple: Boolean
    var icon: ReactElement
    var inputProps: Props
    var onChange: (Event, Boolean) -> Unit
    var required: Boolean
    var type: String
    var value: String
}
var MSwitchProps.color by EnumPropToString(MOptionColor.values())
var MSwitchProps.edge by EnumPropToString(MSwitchEdge.values())
var MSwitchProps.size by EnumPropToString(MIconButtonSize.values())

fun RBuilder.mSwitch(
    checked: Boolean = false,
    color: MOptionColor = MOptionColor.secondary,
    disabled: Boolean = false,
    required: Boolean? = null,
    size: MIconButtonSize = MIconButtonSize.medium,
    onChange: ((Event, Boolean) -> Unit)? = null,
    id: String? = null,
    inputProps: Props? = null,
    value: String? = null,
    edge: MSwitchEdge? = null,
    className: String? = null,
    handler: StyledHandler<MSwitchProps>? = null
) {
    createStyled(switchComponentType, className, handler) {
        attrs.checked = checked
        attrs.color = color
        attrs.disabled = disabled
        required?.let { attrs.required = it }
        edge?.let { attrs.edge = it }
        attrs.disabled = disabled
        id?.let { attrs.id = id }
        inputProps?.let { attrs.inputProps = inputProps }
        onChange?.let { attrs.onChange = onChange }
        attrs.size = size
        attrs.type = InputType.checkBox.realValue
        value?.let {attrs.value = value}
    }
}

/**
 * A label with switch built in. Note, if you want to style the switch or label separately you will have to use
 * mFormControlLabel and pass in a mSwitch.
 */
fun RBuilder.mSwitchWithLabel(
    label: String,
    checked: Boolean = false,
    color: MOptionColor = MOptionColor.secondary,
    disabled: Boolean = false,
    required: Boolean? = null,
    size: MIconButtonSize = MIconButtonSize.medium,
    onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
    id: String? = null,
    inputProps: Props? = null,
    value: String? = null,
    edge: MSwitchEdge? = null,
    className: String? = null,
    handler: StyledHandler<MFormControlLabelProps>? = null
) {
    val switch = buildElements { mSwitch(checked, color, disabled, required, size, onChange, id, inputProps, value, edge) }
    mFormControlLabel(label, switch, checked, disabled, value = value, className = className, handler = handler)
}
