package com.ccfraser.muirwik.wrapper.input

import com.ccfraser.muirwik.wrapper.createStyled
import com.ccfraser.muirwik.wrapper.setStyledPropsAndRunHandler
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Input")
private external val inputModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val inputComponent: RComponent<MInputProps, RState> = inputModule.default

@Suppress("EnumEntryName")
enum class MInputMargin {
    dense, none
}

interface MInputProps : StyledProps {
    var autoComplete: String
    var autoFocus: Boolean
    var defaultValue: String
    var disabled: Boolean
    var disableUnderline: Boolean
    var endAdornment: ReactElement
    var error: Boolean
    var fullWidth: Boolean
    var id: String
    var inputComponent: String
    var inputProps: RProps
    var inputRef: RRef
    var margin: String
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
    var value: String
}

fun RBuilder.mInput(
        value: String? = null,
        required: Boolean? = null,
        disabled: Boolean? = null,
        readOnly: Boolean? = null,
        error: Boolean? = null,
        fullWidth: Boolean = false,
        defaultValue: String? = null,
        placeholder: String? = null,
        startAdornment: ReactElement? = null,
        endAdornment: ReactElement? = null,
        disableUnderline: Boolean = false,
        autoFocus: Boolean? = null,
        type: InputType = InputType.text,
        id: String? = null,
        margin: MInputMargin? = null,
        autoComplete: String? = null,
        inputComponent: String? = null,
        inputProps: RProps? = null,
        inputRef: RRef? = null,
        multiline: Boolean = false,
        rows: Int? = null,
        rowsMax: Int? = null,
        name: String? = null,
        onChange: ((Event) -> Unit)? = null,

        addAsChild: Boolean = true,
        className: String? = null,

        handler: StyledHandler<MInputProps>? = null) = createStyled(com.ccfraser.muirwik.wrapper.input.inputComponent, addAsChild) {
    autoComplete?.let { attrs.autoComplete = it }
    autoFocus?.let{ attrs.autoFocus = it }
    defaultValue?.let { attrs.defaultValue = it }
    disabled?.let { attrs.disabled = it }
    attrs.disableUnderline = disableUnderline
    endAdornment?.let { attrs.endAdornment = it }
    error?.let { attrs.error = it }
    attrs.fullWidth = fullWidth
    id?.let { attrs.id = it }
    inputComponent?.let { attrs.inputComponent = it }
    inputProps?.let { attrs.inputProps = it }
    inputRef?.let { attrs.inputRef = it }
    margin?.let { attrs.margin = it.toString().toLowerCase() }
    attrs.multiline = multiline
    name?.let { attrs.name = it }
    onChange?.let { attrs.onChange = it }
    placeholder?.let { attrs.placeholder = it }
    readOnly?.let { attrs.readOnly = it }
    required?.let { attrs.required = it }
    rows?.let { attrs.rows = it }
    rowsMax?.let { attrs.rowsMax = it }
    startAdornment?.let { attrs.startAdornment = it }
    attrs.type = type.toString()
    value?.let { attrs.value = it }

    setStyledPropsAndRunHandler(className, handler)
}





