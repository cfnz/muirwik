package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.input.MInputBaseNoOnChangeProps
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@material-ui/core/Select")
private external val selectModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val selectComponentType: ComponentType<MSelectProps> = selectModule.default

/**
 * MSelectProps inherits from MInputBaseNoOnChangeProps rather than MInputProps as MInputProps has
 * the wrong function prototype for onChange. We introduce our own onChange here as well as the
 * displayUnderline prop which was the only thing (at time of writing) added to MInputProps.
 */
external interface MSelectProps : MInputBaseNoOnChangeProps {
    var autoWidth: Boolean
    var disableUnderline: Boolean
    var displayEmpty: Boolean

    @JsName("IconComponent")
    var iconComponent: RComponent<MIconProps, State>?

    var input: ReactElement?
    var margin: String

    @JsName("MenuProps")
    var menuProps: Props?

    var multiple: Boolean
    var native: Boolean

    var onChange: ((event: Event, child: ReactElement?) -> Unit)?
    var onClose: SimpleEvent?
    var onOpen: SimpleEvent?
    var open: Boolean
    var renderValue: ((value: Any) -> ReactElement)?

    @JsName("SelectDisplayProps")
    var selectDisplayProps: Props?

    var variant: String
}

fun RBuilder.mSelect(
    value: Any?,
    open: Boolean? = null,
    error: Boolean? = null,
    disabled: Boolean? = null,
    multiple: Boolean = false,
    variant: MFormControlVariant? = null,
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
    handler: StyledHandler<MSelectProps>? = null
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





