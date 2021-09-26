package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.input.MInputMargin
import org.w3c.dom.events.Event
import react.*
import styled.StyledHandler


@JsModule("@mui/material/NativeSelect")
private external val nativeSelectModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val nativeSelectComponentType: ComponentType<MNativeSelectProps> = nativeSelectModule.default

external interface MNativeSelectProps : StyledPropsWithCommonAttributes {
    var autoFocus: Boolean
    var disabled: Boolean
    var error: Boolean
    var fullWidth: Boolean

    @JsName("IconComponent")
    var iconComponent: RComponent<MIconProps, State>?

    var input: ReactElement?
    var inputProps: Props
    var multiple: Boolean
    var name: String
    var onChange: ((event: Event, child: ReactElement?) -> Unit)?
    var value: Any
}
var MNativeSelectProps.margin by EnumPropToStringNullable(MInputMargin.values())
var MNativeSelectProps.variant by EnumPropToString(MFormControlVariant.values())


/**
 * From the Material-UI documentation: An alternative to <Select native /> with a much smaller bundle size footprint.
 * In other words, the mSelect control can do the same thing.
 */
fun RBuilder.mNativeSelect(
    value: Any?,
    error: Boolean? = null,
    disabled: Boolean? = null,
    multiple: Boolean = false,
    iconComponent: RComponent<MIconProps, State>? = null,
    autoFocus: Boolean? = null,
    id: String? = null,
    name: String? = null,
    variant: MFormControlVariant = MFormControlVariant.standard,
    onChange: ((event: Event, child: ReactElement?) -> Unit)? = null,
    className: String? = null,
    handler: StyledHandler<MNativeSelectProps>? = null
) {
    createStyled(nativeSelectComponentType, className, handler) {
        autoFocus?.let { attrs.autoFocus = it }
        disabled?.let { attrs.disabled = it }
        error?.let { attrs.error = it }
        iconComponent?.let { attrs.iconComponent = it }
        id?.let { attrs.id = it }
        attrs.multiple = multiple
        name?.let { attrs.name = it }
        onChange?.let { attrs.onChange = it }
        value?.let { attrs.value = it }
        attrs.variant = variant
    }
}





