package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.input.MInputMargin
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/NativeSelect")
private external val nativeSelectModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val nativeSelectComponent: RComponent<MNativeSelectProps, RState> = nativeSelectModule.default

interface MNativeSelectProps : StyledProps {
    var autoFocus: Boolean
    var disabled: Boolean
    var error: Boolean
    var fullWidth: Boolean
    var id: String

    @JsName("IconComponent")
    var iconComponent: RComponent<MIconProps, RState>?

    var input: ReactElement?
    var inputProps: RProps
    var margin: String
    var multiple: Boolean
    var name: String
    var onChange: ((event: Event, child: ReactElement?) -> Unit)?
    var value: Any
    var variant: String
}

/**
 * From the Material-UI documentation: An alternative to <Select native /> with a much smaller bundle size footprint.
 * In other words, the mSelect control can do the same thing.
 */
fun RBuilder.mNativeSelect(
        value: Any?,
        error: Boolean? = null,
        disabled: Boolean? = null,
        multiple: Boolean = false,
        fullWidth: Boolean = false,
        iconComponent: RComponent<MIconProps, RState>? = null,
        autoFocus: Boolean? = null,
        id: String? = null,
        margin: MInputMargin? = null,
        input: ReactElement? = null,
        inputProps: RProps? = null,
        name: String? = null,
        variant: MFormControlVariant = MFormControlVariant.standard,

        onChange: ((event: Event, child: ReactElement?) -> Unit)? = null,

        addAsChild: Boolean = true,
        className: String? = null,

        handler: StyledHandler<MNativeSelectProps>? = null) = createStyled(nativeSelectComponent, addAsChild) {
    autoFocus?.let { attrs.autoFocus = it }
    disabled?.let { attrs.disabled = it }
    error?.let { attrs.error = it }
    attrs.fullWidth = fullWidth
    iconComponent?.let { attrs.iconComponent = it }
    id?.let { attrs.id = it }
    input?.let { attrs.input = it }
    inputProps?.let { attrs.inputProps = it }
    margin?.let { attrs.margin = it.toString().toLowerCase() }
    attrs.multiple = multiple
    name?.let { attrs.name = it }
    onChange?.let { attrs.onChange = it }
    value?.let { attrs.value = it }
    attrs.variant = variant.toString()

    setStyledPropsAndRunHandler(className, handler)
}





