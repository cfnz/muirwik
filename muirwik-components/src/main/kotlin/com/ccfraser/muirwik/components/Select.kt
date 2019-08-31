package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.MFormControlVariant
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@material-ui/core/Select")
private external val selectModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val selectComponent: RComponent<MSelectProps, RState> = selectModule.default

interface MSelectProps : StyledPropsWithCommonAttributes {
    var autoFocus: Boolean
    var autoWidth: Boolean
    var disabled: Boolean
    var displayEmpty: Boolean
    var endAdornment: ReactElement
    var error: Boolean
    var fullWidth: Boolean

    @JsName("IconComponent")
    var iconComponent: RComponent<MIconProps, RState>?

    var input: ReactElement?
    var inputProps: RProps
    var margin: String

    @JsName("MenuProps")
    var menuProps: RProps?

    var multiple: Boolean
    var name: String
    var native: Boolean
    var onChange: ((event: Event, child: ReactElement?) -> Unit)?
    var onClose: SimpleEvent?
    var onOpen: SimpleEvent?
    var open: Boolean
    var renderValue: ((value: Any) -> ReactElement)?

    @JsName("SelectDisplayProps")
    var selectDisplayProps: RProps?

    var startAdornment: ReactElement
    var value: Any
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

        addAsChild: Boolean = true,
        className: String? = null,

        handler: StyledHandler<MSelectProps>? = null) = createStyled(selectComponent, addAsChild) {
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

    setStyledPropsAndRunHandler(className, handler)
}





