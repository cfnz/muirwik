package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.input.MInputMargin
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Select")
private external val selectModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val selectComponent: RComponent<MSelectProps, RState> = selectModule.default

interface MSelectProps : StyledProps {
    var autoFocus: Boolean
    var autoWidth: Boolean
    var disabled: Boolean
    var displayEmpty: Boolean
    var endAdornment: ReactElement
    var error: Boolean
    var fullWidth: Boolean
    var id: String

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
        disableUnderline: Boolean = false,
        multiple: Boolean = false,
        variant: MFormControlVariant? = null,
        autoWidth: Boolean = false,
        fullWidth: Boolean = false,
        displayEmpty: Boolean = false,
        iconComponent: RComponent<MIconProps, RState>? = null,
        startAdornment: ReactElement? = null,
        endAdornment: ReactElement? = null,
        autoFocus: Boolean? = null,
        id: String? = null,
        margin: MInputMargin? = null,
        input: ReactElement? = null,
        inputProps: RProps? = null,
        menuProps: RProps? = null,
        selectDisplayProps: RProps? = null,
        native: Boolean = false,
        name: String? = null,

        onChange: ((event: Event, child: ReactElement?) -> Unit)? = null,
        onClose: SimpleEvent? = null,
        onOpen: SimpleEvent? = null,
        renderValue: ((value: Any) -> ReactElement)? = null,

        addAsChild: Boolean = true,
        className: String? = null,

        handler: StyledHandler<MSelectProps>? = null) = createStyled(selectComponent, addAsChild) {
    autoFocus?.let { attrs.autoFocus = it }
    attrs.autoWidth = autoWidth
    disabled?.let { attrs.disabled = it }
    attrs.displayEmpty = displayEmpty
    endAdornment?.let { attrs.endAdornment = it }
    error?.let { attrs.error = it }
    attrs.fullWidth = fullWidth
    iconComponent?.let { attrs.iconComponent = it }
    id?.let { attrs.id = it }
    input?.let { attrs.input = it }
    inputProps?.let { attrs.inputProps = it }
    margin?.let { attrs.margin = it.toString().toLowerCase() }
    menuProps?.let { attrs.menuProps = it }
    attrs.multiple = multiple
    attrs.native = native
    name?.let { attrs.name = it }
    onChange?.let { attrs.onChange = it }
    onClose?.let { attrs.onClose = it }
    onOpen?.let { attrs.onOpen = it }
    open?.let { attrs.open = it }
    renderValue?.let { attrs.renderValue = it }
    selectDisplayProps?.let { attrs.selectDisplayProps = it }
    startAdornment?.let { attrs.startAdornment = it }
    value?.let { attrs.value = it }
    variant?.let {attrs.variant = it.toString() }

    setStyledPropsAndRunHandler(className, handler)
}





