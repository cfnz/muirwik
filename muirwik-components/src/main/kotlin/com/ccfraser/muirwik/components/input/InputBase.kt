package com.ccfraser.muirwik.components.input

import com.ccfraser.muirwik.components.EnumPropToStringNullable
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import kotlinx.html.INPUT
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.Props
import react.ReactElement
import react.Ref


@Suppress("EnumEntryName")
enum class MInputMargin {
    dense, none
}

/*
 * We have created an extra layer of input props here as Kotlin doesn't allow changing of function prototypes
 * in inherited interfaces, and since Material UI sometimes changes the onChange function parameters
 * we have created a new base prop for those to inherit from and inherit MInputBaseProps from this as well.
 */
external interface MInputBaseNoOnChangeProps : StyledPropsWithCommonAttributes {
    var autoComplete: String
    var autoFocus: Boolean
    var defaultValue: String
    var disabled: Boolean
    var endAdornment: ReactElement
    var error: Boolean
    var fullWidth: Boolean
    var inputComponent: String
    var inputProps: Props
    var inputRef: Ref<INPUT>
    var multiline: Boolean
    var name: String
    var placeholder: String
    var readOnly: Boolean
    var required: Boolean
    var rows: Int
    var rowsMax: Int
    var rowsMin: Int
    var startAdornment: ReactElement

    @JsName("type")
    var rawType: String

    var value: Any
}

external interface MInputBaseProps : MInputBaseNoOnChangeProps {
    var onChange: (Event) -> Unit
}

var MInputBaseProps.margin by EnumPropToStringNullable(MInputMargin.values())
var MInputBaseProps.type: InputType
    get() = InputType.values().first { it.realValue == rawType }
    set(value) { rawType = value.realValue}



