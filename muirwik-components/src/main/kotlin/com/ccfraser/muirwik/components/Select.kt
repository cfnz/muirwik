package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.SimpleEvent
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@mui/material/Select")
private external val selectModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val selectComponentType: ComponentType<SelectProps> = selectModule.default

/**
 * MSelectProps inherits from MInputBaseNoOnChangeProps rather than MInputProps as MInputProps has
 * the wrong function prototype for onChange. We introduce our own onChange here as well as the
 * displayUnderline prop which was the only thing (at time of writing) added to MInputProps.
 */
external interface SelectProps : InputBaseNoOnChangeProps {
    // From OutlinedInputProps
    var label: ReactNode
    var notched: Boolean


    var autoWidth: Boolean
    var defaultOpen: Boolean
    var displayEmpty: Boolean


    @JsName("IconComponent")
    var iconComponent: RComponent<IconProps, State>?

    var input: ReactElement?

    var labelId: String

    @JsName("MenuProps")
    var menuProps: Props?

    var multiple: Boolean
    var native: Boolean

    var onChange: ((event: Event, child: ReactElement?) -> Unit)
    var onClose: SimpleEvent?
    var onOpen: SimpleEvent?
    var open: Boolean
    var renderValue: ((value: Any) -> ReactElement)?

    @JsName("SelectDisplayProps")
    var selectDisplayProps: Props?

    var variant: String
}


fun RBuilder.select(
    open: Boolean,
    value: Any? = null,
    color: FormControlColor = FormControlColor.primary,
    variant: FormControlVariant? = null,
    handler: StyledHandler<SelectProps>? = null
) {
    createStyled(selectComponentType, handler) {
        attrs.open = open
        value?.let { attrs.value = it }
        attrs.color = color
        variant?.let {attrs.variant = it.toString() }
    }
}

fun RBuilder.select(
    value: Any? = null,
    color: FormControlColor = FormControlColor.primary,
    variant: FormControlVariant? = null,
    handler: StyledHandler<SelectProps>? = null
) {
    createStyled(selectComponentType, handler) {
        value?.let { attrs.value = it }
        attrs.color = color
        variant?.let {attrs.variant = it.toString() }
    }
}

@Deprecated("Use the simpler 'non m' version.")
fun RBuilder.mSelect(
    value: Any?,
    open: Boolean? = null,
    error: Boolean? = null,
    disabled: Boolean? = null,
    multiple: Boolean = false,
    variant: FormControlVariant? = null,
    autoWidth: Boolean = false,
    fullWidth: Boolean = false,
    displayEmpty: Boolean = false,
    autoFocus: Boolean? = null,
    id: String? = null,
    name: String? = null,
    input: ReactElement? = null,
    native: Boolean = false,
    onChange: ((event: Event, child: ReactElement?) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<SelectProps>? = null
) {
    createStyled(selectComponentType, className, handler) {
        autoFocus?.let { attrs.autoFocus = it }
        attrs.autoWidth = autoWidth
        disabled?.let { attrs.disabled = it }
        attrs.displayEmpty = displayEmpty
        error?.let { attrs.error = it }
        attrs.fullWidth = fullWidth
        id?.let { attrs.id = it }
        input?.let { attrs.input = it }
        attrs.multiple = multiple
        attrs.native = native
        name?.let { attrs.name = it }
        onChange?.let { attrs.onChange = it }
        open?.let { attrs.open = it }
        value?.let { attrs.value = it }
        variant?.let {attrs.variant = it.toString() }
    }
}





