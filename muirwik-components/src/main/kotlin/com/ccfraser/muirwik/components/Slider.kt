package com.ccfraser.muirwik.components

import com.ccfraser.muirwik.components.utils.ElementType
import com.ccfraser.muirwik.components.utils.EnumPropToString
import com.ccfraser.muirwik.components.utils.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.utils.createStyled
import kotlinext.js.Object
import react.ComponentType
import react.Props
import react.RBuilder
import styled.StyledHandler


@JsModule("@mui/material/Slider")
private external val sliderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val sliderComponentType: ComponentType<SliderProps> = sliderModule.default

@Suppress("UnsafeCastFromDynamic")
private val sliderWithRangeComponentType: ComponentType<SliderWithRangeValueProps> = sliderModule.default


@Suppress("EnumEntryName")
enum class SliderOrientation {
    horizontal, vertical
}

@Suppress("EnumEntryName")
enum class SliderColor {
    primary, secondary
}

@Suppress("EnumEntryName")
enum class SliderValueLabelDisplay {
    on, off, auto
}

@Suppress("EnumEntryName")
enum class SliderTrack {
    inverted, normal, off
}

data class SliderMark(val value: Number, val label: String? = null)

external interface BaseSliderProps : StyledPropsWithCommonAttributes {
    var component: ElementType
    var components: dynamic
    var componentsProps: Props
    var disabled: Boolean
    var disabledSwap: Boolean
    var getAriaLabel: (index: Number) -> String
    var getAriaValueText: (value: Number, index: Number) -> String
    var isRtl: Boolean
    var marks: Any //sort this out
    var max: Number
    var min: Number
    var name: String
    var scale: (Number) -> Number
    var step: Number?
    var valueLabelFormat: (value: Number, index: Number) -> String
}
var BaseSliderProps.color by EnumPropToString(SliderColor.values())
var BaseSliderProps.orientation by EnumPropToString(SliderOrientation.values())
var BaseSliderProps.size by EnumPropToString(FormControlSize.values())
var BaseSliderProps.track: SliderTrack
    get() = if (this.asDynamic().track == false) SliderTrack.off else SliderTrack.valueOf(this.asDynamic().track as String)
    set(value) { this.asDynamic().track = if (value == SliderTrack.off) false else value.toString() }
var BaseSliderProps.valueLabelDisplay by EnumPropToString(SliderValueLabelDisplay.values())

external interface SliderProps : BaseSliderProps {
    var defaultValue: Number
    var onChange: (event: Object, value: Number) -> Unit
    var onChangeCommitted: (event: Object, value: Number) -> Unit
    var value: Number
}

external interface SliderWithRangeValueProps : BaseSliderProps {
    var defaultValue: Array<Number>
    var onChange: (event: Object, value: Pair<Number, Number>) -> Unit
    var onChangeCommitted: (event: Object, value: Pair<Number, Number>) -> Unit
    var value: Array<Number>
}

fun RBuilder.slider(
    value: Number? = null,
    min: Number = 0,
    max: Number = 100,
    step: Number? = 1,
    handler: StyledHandler<SliderProps>? = null
) {
    createStyled(sliderComponentType, handler) {
        value?.let { attrs.value = it }
        attrs.min = min
        attrs.max = max
        step?.let { attrs.step = it }
    }
}

fun RBuilder.sliderWithRange(
    value: Array<Number>? = null,
    min: Number = 0,
    max: Number = 100,
    step: Number = 1,
    handler: StyledHandler<SliderWithRangeValueProps>? = null
) {
    createStyled(sliderWithRangeComponentType, handler) {
        value?.let { attrs.value = it }
        attrs.min = min
        attrs.max = max
        attrs.step = step
    }
}

