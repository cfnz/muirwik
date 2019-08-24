package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.MFormControlLabelProps
import com.ccfraser.muirwik.components.form.mFormControlLabel
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@material-ui/core/Switch")
private external val switchDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val switchComponent: RComponent<MSwitchProps, RState> = switchDefault.default

interface MSwitchProps : StyledPropsWithCommonAttributes {
    var checked: Boolean?
    var checkedIcon: ReactElement?
    var color: MOptionColor?
    var disabled: Boolean?
    var disableRipple: Boolean?
    var edge: String?
    var icon: ReactElement?
//    var id: String?
    var inputProps: RProps?
    var onChange: ((Event, Boolean) -> Unit)?
    var required: Boolean?
    var size: String?
    var type: String
    var value: String?
}

@Suppress("EnumEntryName")
enum class MSwitchEdge {
    start, end // We assume if the prop is null, then the default false will be used, so we don't have this as a value
}

@Suppress("EnumEntryName")
enum class MSwitchSize {
    small, medium
}

fun RBuilder.mSwitch(
        checked: Boolean = false,
        color: MOptionColor = MOptionColor.secondary,
        disabled: Boolean = false,
        required: Boolean? = null,
        size: MSwitchSize = MSwitchSize.medium,
        onChange: ((Event, Boolean) -> Unit)? = null,
        id: String? = null,
        inputProps: RProps? = null,
        value: String? = null,
        edge: MSwitchEdge? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MSwitchProps>? = null) = createStyled(switchComponent, addAsChild) {
    attrs.checked = checked
    attrs.color = color
    attrs.disabled = disabled
    edge?.let { attrs.edge = it.toString() }
    attrs.disabled = disabled
    id?.let { attrs.id = id }
    inputProps?.let { attrs.inputProps = inputProps }
    onChange?.let { attrs.onChange = onChange }
    attrs.size = size.toString()
    attrs.type = InputType.checkBox.realValue
    value?.let {attrs.value = value}

    setStyledPropsAndRunHandler(className, handler)
    attrs.asDynamic().color = attrs.color.toString()
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
        size: MSwitchSize = MSwitchSize.medium,
        onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
        id: String? = null,
        inputProps: RProps? = null,
        value: String? = null,
        edge: MSwitchEdge? = null,

        className: String? = null,
        handler: StyledHandler<MFormControlLabelProps>? = null): ReactElement {
    val switch = mSwitch(checked, color, disabled, required, size, onChange, id, inputProps, value, edge, false)

    return mFormControlLabel(label, switch, checked, disabled, value = value, className = className, handler = handler)
}
