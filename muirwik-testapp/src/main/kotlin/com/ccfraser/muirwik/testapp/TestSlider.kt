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
            typography("Volume")
            gridContainer(GridSpacing.spacing2) {
                gridItem {
                    icon("volume_down")
                }
                gridItem {
                    attrs.xs = GridSize.cellsTrue
                    slider(value) {
                        attrs.onChange = { _, newValue -> setState { value = newValue } }
                    }
                }
                gridItem {
                    icon("volume_up")
                }
            }
            slider(10) { attrs.disabled = true }
        }
        styledDiv {
            css {
                padding(2.spacingUnits)
                width = 300.px
            }
            typography("Temperature")
            slider {
                attrs.defaultValue = 30
                attrs.valueLabelDisplay = SliderValueLabelDisplay.auto
                attrs.marks = true
                attrs.step = 10
                attrs.min = 10
                attrs.max = 110
                br {}
                br {}
            }
            typography("Small Steps")
            slider(null, -0.0000009, 0.0000009, 0.0000001) {
                attrs.defaultValue = 0.0000005
                attrs.valueLabelDisplay = SliderValueLabelDisplay.auto
                attrs.marks = true
                attrs.getAriaValueText = { v, _ -> "$v°C" }
            }
            br {}
            br {}
            typography("Custom Marks (with one unlabeled)")
            slider {
                attrs.defaultValue = 20
                attrs.valueLabelDisplay = SliderValueLabelDisplay.auto
                attrs.step = 10
                attrs.marks = arrayOf(
                    SliderMark(0, "0°C"),
                    SliderMark(20, "20°C"),
                    SliderMark(37, "37°C"),
                    SliderMark(50),
                    SliderMark(100, "100°C")
                )
            }
            br {}
            br {}
            typography("Restricted Values")
            slider {
                attrs.defaultValue = 20
                attrs.valueLabelDisplay = SliderValueLabelDisplay.auto
                attrs.step = null
                attrs.marks = arrayOf(
                    SliderMark(0, "0°C"),
                    SliderMark(20, "20°C"),
                    SliderMark(37, "37°C"),
                    SliderMark(50),
                    SliderMark(100, "100°C")
                )
            }
            br {}
            br {}
            typography("Always Visible")
            slider {
                attrs.defaultValue = 80
                attrs.valueLabelDisplay = SliderValueLabelDisplay.on
                attrs.marks = true
                attrs.step = 10
            }
            br {}
            br {}
            typography("Temperature Range")
            sliderWithRange {
                attrs.defaultValue = arrayOf(20, 37)
                attrs.valueLabelDisplay = SliderValueLabelDisplay.auto
                attrs.marks = arrayOf(
                    SliderMark(0, "0°C"),
                    SliderMark(100, "100°C")
                )
            }
        }
    }
}
