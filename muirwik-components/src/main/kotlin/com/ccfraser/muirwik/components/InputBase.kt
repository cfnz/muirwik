package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.EnumPropToStringNullable
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import kotlinx.html.INPUT
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.Props
import react.ReactNode
import react.Ref


@Suppress("EnumEntryName")
enum class InputMargin {
    dense, none
}

/*
 * We have created an extra layer of input props here as Kotlin doesn't allow changing of function prototypes
 * in inherited interfaces, and since Material UI sometimes changes the onChange function parameters
 * we have created a new base prop for those to inherit from and inherit MInputBaseProps from this as well.
 */
external interface InputBaseNoOnChangeProps : StyledPropsWithCommonAttributes {
    var autoComplete: String
    var autoFocus: Boolean
    var components: ElementType
    var componentProps: Props
    var defaultValue: String
    var disabled: Boolean
    var endAdornment: ReactNode
    var error: Boolean
    var fullWidth: Boolean
    var inputComponent: ElementType
    var inputProps: Props
    var inputRef: Ref<INPUT>
    var maxRows: Int
    var minRows: Int
    var multiline: Boolean
    var name: String
    var placeholder: String
    var readOnly: Boolean
    var required: Boolean
    var rows: Int
    var startAdornment: ReactNode

    @JsName("type")
    var rawType: String

    var value: Any
}
var InputBaseNoOnChangeProps.color by EnumPropToString(FormControlColor.values())
var InputBaseNoOnChangeProps.size by EnumPropToString(FormControlSize.values())
var InputBaseNoOnChangeProps.margin by EnumPropToStringNullable(InputMargin.values())
var InputBaseNoOnChangeProps.type: InputType
    get() = InputType.values().first { it.realValue == rawType }
    set(value) { rawType = value.realValue}

external interface InputBaseProps : InputBaseNoOnChangeProps {
    var onChange: (Event) -> Unit
}



