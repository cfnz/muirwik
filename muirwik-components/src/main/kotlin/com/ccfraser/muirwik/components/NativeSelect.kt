package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.createStyled
import org.w3c.dom.events.Event
import react.ComponentType
import react.RBuilder
import react.ReactElement
import styled.StyledHandler

@JsModule("@mui/material/NativeSelect")
private external val nativeSelectModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val nativeSelectComponentType: ComponentType<NativeSelectProps> = nativeSelectModule.default

external interface NativeSelectProps : InputProps {
    @JsName("IconComponent")
    var iconComponent: ReactElement
    var input: ReactElement?
    var multiple: Boolean
}
var NativeSelectProps.variant by EnumPropToString(FormControlVariant.values())


/**
 * From the Material-UI documentation: An alternative to <Select native /> with a much smaller bundle size footprint.
 * In other words, the mSelect control can do the same thing.
 */
fun RBuilder.mNativeSelect(
    value: Any?,
    error: Boolean? = null,
    disabled: Boolean? = null,
    multiple: Boolean = false,
    iconComponent: ReactElement,
    autoFocus: Boolean? = null,
    id: String? = null,
    name: String? = null,
    variant: FormControlVariant = FormControlVariant.standard,
    onChange: ((event: Event?) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<NativeSelectProps>? = null
) {
    createStyled(nativeSelectComponentType, className, handler) {
        autoFocus?.let { attrs.autoFocus = it }
        disabled?.let { attrs.disabled = it }
        error?.let { attrs.error = it }
        attrs.iconComponent = iconComponent
        id?.let { attrs.id = it }
        attrs.multiple = multiple
        name?.let { attrs.name = it }
        onChange?.let { attrs.onChange = it }
        value?.let { attrs.value = it }
        attrs.variant = variant
    }
}





