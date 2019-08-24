package com.ccfraser.muirwik.components.input

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import org.w3c.dom.events.Event
import react.RProps
import react.RRef
import react.ReactElement


/*
 * Don't think we will be creating these types of controls, so we shall just define the props and
 * inherit from them in the other inputs...
 */
//@JsModule("@material-ui/core/InputBase")
//private external val inputBaseModule: dynamic
//
//@Suppress("UnsafeCastFromDynamic")
//private val inputBaseComponent: RComponent<MInputBaseProps, RState> = inputBaseModule.default

@Suppress("EnumEntryName")
enum class MInputMargin {
    dense, none
}

interface MInputBaseProps : StyledPropsWithCommonAttributes {
    var autoComplete: String
    var autoFocus: Boolean
    var defaultValue: String
    var disabled: Boolean
    var endAdornment: ReactElement
    var error: Boolean
    var fullWidth: Boolean
    var inputComponent: String
    var inputProps: RProps
    var inputRef: RRef
    var margin: MInputMargin
    var multiline: Boolean
    var name: String
    var onChange: (Event) -> Unit
    var placeholder: String
    var readOnly: Boolean
    var required: Boolean
    var rows: Int
    var rowsMax: Int
    var startAdornment: ReactElement
    var type: String
    var value: Any
}

fun MInputBaseProps.redefineInputBaseDefinedProps() {
    if (margin != undefined) this.asDynamic().margin = margin.toString()
}



