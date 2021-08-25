package com.ccfraser.muirwik.components.lab

import com.ccfraser.muirwik.components.EnumPropToString
import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import kotlinext.js.Object
import react.ComponentType
import react.FunctionComponent
import react.RBuilder
import react.ReactElement
import styled.StyledHandler

@JsModule("@material-ui/lab/Rating")
private external val ratingModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val ratingComponentType: ComponentType<MRatingProps> = ratingModule.default

@Suppress("EnumEntryName")
enum class MRatingSize {
    large, medium, small
}

external interface MRatingProps : StyledPropsWithCommonAttributes {
    var defaultValue: Number
    var disabled: Boolean
    var emptyIcon: ReactElement
    var emptyLabelText: String
    var getLabelText: (value: Number) -> String
    var icon: ReactElement
    @JsName("IconContainerComponent")
    var iconContainerComponent: FunctionComponent<MIconContainerProps>
    var max: Number
    var name: String
    var onChange: (event: Object, newValue: Number) -> Unit
    var onChangeActive: (event: Object, hoverValue: Number) -> Unit
    var precision: Number
    var readOnly: Boolean
    var value: Number?
}
var MRatingProps.size by EnumPropToString(MRatingSize.values())

external interface MIconContainerProps : StyledPropsWithCommonAttributes {
    var value: Int
}

fun RBuilder.mRating(
    name: String,
    value: Number? = null,
    max: Number = 5,
    precision: Number = 1,
    onChange: ((event: Object, newValue: Number) -> Unit)? = null,
    defaultValue: Number? = null,
    readOnly: Boolean = false,
    disabled: Boolean = false,
    icon: ReactElement? = null,
    emptyIcon: ReactElement? = null,
    emptyLabelText: String = "Empty",
    size: MRatingSize = MRatingSize.medium,
    className: String? = null,
    handler: StyledHandler<MRatingProps>? = null
) {
    createStyled(ratingComponentType, className, handler) {
        defaultValue?.let { attrs.defaultValue = it }
        attrs.disabled = disabled
        emptyIcon?.let { attrs.emptyIcon = it }
        attrs.emptyLabelText = emptyLabelText
        icon?.let { attrs.icon = icon }
        attrs.max = max
        attrs.name = name
        onChange?.let { attrs.onChange = it }
        attrs.precision = precision
        attrs.readOnly = readOnly
        attrs.size = size
        value?.let { attrs.value = it }
    }
}
