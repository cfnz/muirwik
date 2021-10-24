package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinext.js.Object
import react.ComponentType
import react.FunctionComponent
import react.RBuilder
import react.ReactElement
import styled.StyledHandler

@JsModule("@mui/material/Rating")
private external val ratingModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val ratingComponentType: ComponentType<RatingProps> = ratingModule.default

@Suppress("EnumEntryName")
enum class RatingSize {
    large, medium, small
}

external interface RatingProps : StyledPropsWithCommonAttributes {
    var defaultValue: Number
    var disabled: Boolean
    var emptyIcon: ReactElement
    var emptyLabelText: String
    var getLabelText: (value: Number) -> String
    var highlightSelectedOnly: Boolean
    var icon: ReactElement
    @JsName("IconContainerComponent")
    var iconContainerComponent: FunctionComponent<IconContainerProps>
    var max: Number
    var name: String
    var onChange: (event: Object, newValue: Number) -> Unit
    var onChangeActive: (event: Object, hoverValue: Number) -> Unit
    var precision: Number
    var readOnly: Boolean
    var value: Number?
}
var RatingProps.size by EnumPropToString(RatingSize.values())

external interface IconContainerProps : StyledPropsWithCommonAttributes {
    var value: Int
}

fun RBuilder.rating(
    name: String,
    value: Number? = null,
    max: Number = 5,
    handler: StyledHandler<RatingProps>? = null
) {
    createStyled(ratingComponentType, handler) {
        attrs.max = max
        attrs.name = name
        value?.let { attrs.value = it }
    }
}

@Deprecated("Use the simpler 'non m' version.")
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
    size: RatingSize = RatingSize.medium,
    className: String? = null,
    handler: StyledHandler<RatingProps>? = null
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
