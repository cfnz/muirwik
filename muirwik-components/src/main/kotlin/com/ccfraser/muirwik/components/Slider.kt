package com.ccfraser.muirwik.components

import kotlinext.js.Object
import react.RBuilder
import react.RComponent
import react.RState
import react.ReactElement
import styled.StyledHandler


@JsModule("@material-ui/core/Slider")
private external val sliderModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val sliderComponent: RComponent<MSliderProps, RState> = sliderModule.default


@Suppress("EnumEntryName")
enum class MSliderOrientation {
    horizontal, vertical
}

@Suppress("EnumEntryName")
enum class MSliderValueLabelDisplay {
    on, off, auto
}

data class MSliderMark(val value: Number, val label: String? = null)

external interface MSliderProps : StyledPropsWithCommonAttributes {
    var component: String
    var defaultValue: Any
    var disabled: Boolean
    var getAriaValueText: (value: Number, index: Number) -> String
    var marks: Any
    var max: Number
    var min: Number
    var name: String
    var onChange: (event: Object, value: dynamic) -> Unit
    var onChangeCommitted: (event: Object, value: dynamic) -> Unit
    var step: Number?

    @JsName("ThumbComponent")
    var thumbComponent: String

    var value: dynamic

    @JsName("ValueLabelComponent")
    var valueLabelComponent: ReactElement

    var valueLabelFormat: (value: Number, index: Number) -> String
}
var MSliderProps.orientation by EnumPropToString(MSliderOrientation.values())
var MSliderProps.valueLabelDisplay by EnumPropToString(MSliderValueLabelDisplay.values())


fun RBuilder.mSlider(
        value: Number? = null,
        min: Number = 0,
        max: Number = 100,
        step: Number? = 1,
        showMarks: Boolean = false,
        marks: List<MSliderMark> = emptyList(),
        defaultValue: Number? = null,

        orientation: MSliderOrientation = MSliderOrientation.horizontal,
        disabled: Boolean = false,

        label: String? = null,
        labelledBy: String? = null,
        name: String? = null,
        component: String = "span",
        thumbComponent: String = "span",
        valueText: String? = null,

        onChange: ((event: Object, value: Number) -> Unit)? = null,
        onChangeCommitted: ((event: Object, value: Number) -> Unit)? = null,
        getAriaValueText: ((value: Number, index: Number) -> String)? = null,

        valueLabelComponent: ReactElement? = null,
        valueLabelDisplay: MSliderValueLabelDisplay = MSliderValueLabelDisplay.off,
        valueLabelFormat: ((value: Number, index: Number) -> String)? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MSliderProps>? = null) = createStyled(sliderComponent, addAsChild) {

    setCommonAttrs(attrs, min, max, step, showMarks, marks, orientation, disabled, label, labelledBy, name, component,
            thumbComponent, valueText, onChange, onChangeCommitted, getAriaValueText, valueLabelComponent,
            valueLabelDisplay, valueLabelFormat)

    defaultValue?.let { attrs.defaultValue = it }
    value?.let { attrs.value = it }

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mSliderWithRange(
        value: Pair<Number, Number>? = null,
        min: Number = 0,
        max: Number = 100,
        step: Number? = 1,
        showMarks: Boolean = false,
        marks: List<MSliderMark> = emptyList(),
        defaultValue: Pair<Number, Number>? = null,

        orientation: MSliderOrientation = MSliderOrientation.horizontal,
        disabled: Boolean = false,

        label: String? = null,
        labelledBy: String? = null,
        name: String? = null,
        component: String = "span",
        thumbComponent: String = "span",
        valueText: String? = null,

        onChange: ((event: Object, value: Number) -> Unit)? = null,
        onChangeCommitted: ((event: Object, value: Number) -> Unit)? = null,
        getAriaValueText: ((value: Number, index: Number) -> String)? = null,

        valueLabelComponent: ReactElement? = null,
        valueLabelDisplay: MSliderValueLabelDisplay = MSliderValueLabelDisplay.off,
        valueLabelFormat: ((value: Number, index: Number) -> String)? = null,

        addAsChild: Boolean = true,
        className: String? = null,
        handler: StyledHandler<MSliderProps>? = null) = createStyled(sliderComponent, addAsChild) {

    setCommonAttrs(attrs, min, max, step, showMarks, marks, orientation, disabled, label, labelledBy, name, component,
            thumbComponent, valueText, onChange, onChangeCommitted, getAriaValueText, valueLabelComponent,
            valueLabelDisplay, valueLabelFormat)

    defaultValue?.let { attrs.defaultValue = it.toList().toTypedArray() }
    value?.let { attrs.value = it.toList().toTypedArray() }

    setStyledPropsAndRunHandler(className, handler)
}

private fun setCommonAttrs(
        attrs: MSliderProps,
        min: Number,
        max: Number,
        step: Number?,
        showMarks: Boolean,
        marks: List<MSliderMark>,
        orientation: MSliderOrientation,
        disabled: Boolean,
        label: String?,
        labelledBy: String?,
        name: String?,
        component: String,
        thumbComponent: String,
        valueText: String?,
        onChange: ((event: Object, value: Number) -> Unit)?,
        onChangeCommitted: ((event: Object, value: Number) -> Unit)?,
        getAriaValueText: ((value: Number, index: Number) -> String)?,
        valueLabelComponent: ReactElement?,
        valueLabelDisplay: MSliderValueLabelDisplay,
        valueLabelFormat: ((value: Number, index: Number) -> String)?
) {
    attrs.component = component
    attrs.asDynamic()["aria-label"] = label
    attrs.asDynamic()["aria-labelledby"] = labelledBy
    attrs.asDynamic()["aria-valuetext"] = valueText
    attrs.component = component
    attrs.disabled = disabled
    getAriaValueText?.let { attrs.getAriaValueText = it }
    if (showMarks) {
        attrs.marks = if (marks.isEmpty()) true else marks.toTypedArray()
    }
    attrs.max = max
    attrs.min = min
    name?.let { attrs.name = it }
    onChange?.let { attrs.onChange = it }
    onChangeCommitted?.let { attrs.onChangeCommitted = it }
    attrs.orientation = orientation
    attrs.step = step
    attrs.thumbComponent = thumbComponent
    valueLabelComponent?.let { attrs.valueLabelComponent = it }
    attrs.valueLabelDisplay = valueLabelDisplay
    valueLabelFormat?.let { attrs.valueLabelFormat = it }
}
