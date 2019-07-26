package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.MFormControlLabelProps
import com.ccfraser.muirwik.components.form.MLabelPlacement
import com.ccfraser.muirwik.components.form.mFormControlLabel
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Checkbox")
private external val checkboxModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val checkboxComponent : RComponent<MCheckboxProps, RState> = checkboxModule.default

interface MCheckboxProps : StyledProps {
    var checked: Boolean
    var checkedIcon: ReactElement
    var color: String
    var disabled: Boolean
    var disableRipple: Boolean
    var icon: ReactElement?
    var id: String?
    var indeterminate: Boolean
    var indeterminateIcon: ReactElement
    var inputProps: RProps?
    var onChange: ((Event, Boolean) -> Unit)
    var type: String
    var value: String
}

/**
 * Checkbox without a label. If you want a simple checkbox that is wrapped in a label, use [mCheckboxWithLabel]
 */
fun RBuilder.mCheckbox(
        checked: Boolean = false,
        primary: Boolean = true,
        disabled: Boolean = false,
        indeterminate: Boolean = false,
        icon: ReactElement? = null,
        checkedIcon: ReactElement? = null,
        indeterminateIcon: ReactElement? = null,
        onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
        disableRipple: Boolean = false,
        id: String? = null,
        inputProps: RProps? = null,
        value: String? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MCheckboxProps>? = null) = createStyled(checkboxComponent, addAsChild) {
    attrs.checked = checked
    checkedIcon?.let { attrs.checkedIcon = checkedIcon }
    attrs.color = if (primary) MColor.primary.toString() else MColor.secondary.toString()
    attrs.disabled = disabled
    attrs.disableRipple = disableRipple
    icon?.let { attrs.icon = icon }
    id?.let { attrs.id = id }
    attrs.indeterminate = indeterminate
    indeterminateIcon?.let { attrs.indeterminateIcon = indeterminateIcon}
    inputProps?.let { attrs.inputProps = inputProps }
    onChange?.let { attrs.onChange = onChange }
    attrs.type = InputType.checkBox.realValue
    value?.let {attrs.value = value}

    setStyledPropsAndRunHandler(className, handler)
}

/**
 * A label with checkbox built in. Note, if you want to style the checkbox or label separately you will have to use
 * mFormControlLabel and pass in a mCheckbox.
 */
fun RBuilder.mCheckboxWithLabel(
        label: String,
        checked: Boolean = false,
        primary: Boolean = true,
        disabled: Boolean = false,
        indeterminate: Boolean = false,
        icon: ReactElement? = null,
        checkedIcon: ReactElement? = null,
        indeterminateIcon: ReactElement? = null,
        labelPlacement: MLabelPlacement = MLabelPlacement.end,
        onChange: ((event: Event, checked: Boolean) -> Unit)? = null,
        disableRipple: Boolean = false,
        id: String? = null,
        inputProps: RProps? = null,
        value: String? = null,

        className: String? = null,
        handler: StyledHandler<MFormControlLabelProps>? = null): ReactElement {
    val checkBox = mCheckbox(checked, primary, disabled, indeterminate, icon, checkedIcon, indeterminateIcon, onChange,
            disableRipple, id, inputProps, value, false)

    return mFormControlLabel(label, checkBox, checked, disabled, value = value, labelPlacement = labelPlacement, className = className, handler = handler)
}
