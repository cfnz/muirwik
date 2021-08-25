package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.padding
import kotlinx.css.px
import kotlinx.css.width
import react.*
import react.dom.br
import styled.css
import styled.styledDiv

class TestSlider : RComponent<Props, State>() {
    private var value: Number = 10

    override fun RBuilder.render() {
        styledDiv {
            css {
                padding(2.spacingUnits)
                width = 200.px
            }
            mTypography("Volume")
            mGridContainer(MGridSpacing.spacing2) {
                mGridItem {
                    mIcon("volume_down")
                }
                mGridItem(xs = MGridSize.cellsTrue) {
                    mSlider(value, onChange = { _, newValue -> setState { value = newValue } })
                }
                mGridItem {
                    mIcon("volume_up")
                }
            }
            mSlider(10, disabled = true)
        }
        styledDiv {
            css {
                padding(2.spacingUnits)
                width = 300.px
            }
            mTypography("Temperature")
            mSlider(defaultValue = 30, valueLabelDisplay = MSliderValueLabelDisplay.auto, showMarks = true,
                    step = 10, min = 10, max = 110)
            br {}
            br {}
            mTypography("Small Steps")
            mSlider(defaultValue = 0.0000005, valueLabelDisplay = MSliderValueLabelDisplay.auto, showMarks = true,
                    step = 0.0000001, min = -.0000009, max = 0.0000009, getAriaValueText = { v, _ -> "$v°C" })
            br {}
            br {}
            mTypography("Custom Marks (with one unlabeled)")
            mSlider(defaultValue = 20, valueLabelDisplay = MSliderValueLabelDisplay.auto, showMarks = true,
                    step = 10, marks = listOf(
                    MSliderMark(0, "0°C"),
                    MSliderMark(20, "20°C"),
                    MSliderMark(37, "37°C"),
                    MSliderMark(50),
                    MSliderMark(100, "100°C")
            ))
            br {}
            br {}
            mTypography("Restricted Values")
            mSlider(defaultValue = 20, valueLabelDisplay = MSliderValueLabelDisplay.auto, showMarks = true,
                    step = null, marks = listOf(
                    MSliderMark(0, "0°C"),
                    MSliderMark(20, "20°C"),
                    MSliderMark(37, "37°C"),
                    MSliderMark(50),
                    MSliderMark(100, "100°C")
            ))
            br {}
            br {}
            mTypography("Always Visible")
            mSlider(defaultValue = 80, valueLabelDisplay = MSliderValueLabelDisplay.on, showMarks = true, step = 10)
            br {}
            br {}
            mTypography("Temperature Range")
            mSliderWithRange(defaultValue = Pair(20, 37), valueLabelDisplay = MSliderValueLabelDisplay.auto,
                    showMarks = true, marks = listOf(
                    MSliderMark(0, "0°C"),
                    MSliderMark(100, "100°C")
            ))
        }
    }
}
