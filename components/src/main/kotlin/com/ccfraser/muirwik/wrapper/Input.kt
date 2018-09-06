package com.ccfraser.muirwik.wrapper

import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Input")
private external val inputDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val inputComp: RComponent<MInputProps, RState> = inputDefault.default

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
    var rows: Int
    var rowsMax: Int
    var startAdornment: ReactElement
    var type: String
    var value: Any
}

fun RBuilder.mInput(
        value: Any? = null,
        autoFocus: Boolean = false,
        type: InputType = InputType.text,
        error: Boolean = false,
        disabled: Boolean? = null,
        disableUnderline: Boolean = false,
        fullWidth: Boolean = false,
        defaultValue: String? = null,
        placeholder: String? = null,
        startAdornment: ReactElement? = null,
        endAdornment: ReactElement? = null,
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

        handler: StyledHandler<MInputProps>? = null) = createStyled(inputComp, addAsChild) {
    autoComplete?.let { attrs.autoComplete = it }
    attrs.autoFocus = autoFocus
    defaultValue?.let { attrs.defaultValue = it }
    disabled?.let { attrs.disabled = it }
    attrs.disableUnderline = disableUnderline
    endAdornment?.let { attrs.endAdornment = it }
    attrs.error = error
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
    rows?.let { attrs.rows = it }
    rowsMax?.let { attrs.rowsMax = it }
    startAdornment?.let { attrs.startAdornment = it }
    attrs.type = type.toString()
    value?.let { attrs.value = it }

    setStyledPropsAndRunHandler(className, handler)
}





