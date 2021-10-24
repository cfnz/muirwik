package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.ControlColor
import com.ccfraser.muirwik.testapp.TestRadioButtons.ComponentStyles.paddingLeft12
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css

external interface TestRadioButtonsState : State {
    var gender2Value: String
}

class TestRadioButtons : RComponent<Props, TestRadioButtonsState>() {
    private var radioValue: String = "a"
    private var gender1Value: String = "female"

    init {
        state.gender2Value = "male"
    }

    object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val paddingLeft12 by css {
            paddingLeft = 12.px
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Radio options and groups") {
                css { display = Display.inlineFlex; marginTop = 16.px }
                radioGroup(radioValue) {
                    attrs.onChange = { _, value -> setState { radioValue = value } }
                    css { display = Display.inlineFlex }
                    radio(value = "a")
                    // The icons are more for a checkbox type control, but for fun, we shall put the star here too
                    radio(value = "b") {
                        attrs.icon = buildElement { icon("star") }
                    }
                }
                radioGroup(radioValue) {
                    attrs.onChange = { _, value -> setState { radioValue = value } }
                    css { display = Display.inlineFlex }
                    formControlLabel("With Label C", "c", buildElement { radio() }) { css(paddingLeft12) } // With a label it seems to need a bit of left padding
                    formControlLabel("With Label D", "d", buildElement { radio() }) { css(paddingLeft12) } // With a label it seems to need a bit of left padding
                }
            }
            demoPanel("As Form Controls") {
                css { display = Display.inlineFlex }
                formControl {
                    css { display = Display.inlineFlex }
                    attrs.component = FormControlComponent.fieldSet
                    formLabel("Gender") { attrs.required = true; attrs.component = "legend" }
                    radioGroup(state.gender2Value) {
                        attrs.onChange = { _, value -> setState { gender2Value = value; println("Value: $value") } }
                        formControlLabel("Female", "female", buildElement { radio() })
                        formControlLabel("Male", "male", buildElement { radio() })
                        formControlLabel("Other", "other", buildElement { radio() })
                        formControlLabel("Disabled Option", "disabled", buildElement { radio() }) { attrs.disabled = true }
                    }
                }
                formControl {
                    css { display = Display.inlineFlex }
                    attrs.component = FormControlComponent.fieldSet
                    attrs.required = true
                    attrs.error = true
                    formLabel("Gender2") { attrs.required = true; attrs.component = "legend" }
                    radioGroup(gender1Value, name = "gender1") {
                        attrs.onChange = { _, value -> setState { gender1Value = value; println("Value: $value") } }
                        formControlLabel("Male", "male", buildElement { radio() })
                        formControlLabel("Female", "female", buildElement { radio() })
                        formControlLabel("Other", "other", buildElement { radio() })
                        formControlLabel("Disabled Option", "disabled", buildElement { radio() }) { attrs.disabled = true }
                    }
                }
                formControl {
                    attrs.component = FormControlComponent.fieldSet
                    attrs.required = true
                    attrs.error = true
                    css { display = Display.inlineFlex }
                    formLabel("Gender3") { attrs.required = true; attrs.component = "legend" }
                    radioGroup(gender1Value, name = "gender1") {
                        attrs.onChange = { _, value -> setState { gender1Value = value; println("Value: $value") } }
                        // Slightly shorthand, practically does the same as the mFormControlLabel above.
                        radioWithLabel("Male", color = ControlColor.primary, value = "male") { attrs.labelPlacement = LabelPlacement.start }
                        radioWithLabel("Female", color = ControlColor.primary, value = "female") { attrs.labelPlacement = LabelPlacement.start }
                        radioWithLabel("Other", color = ControlColor.primary, value = "other") { attrs.labelPlacement = LabelPlacement.start }
                        radioWithLabel("Disabled Option", value = "disabled") { attrs.disabled = true; attrs.labelPlacement = LabelPlacement.start }
                    }
                    typography("Label Placement = start")
                }
            }
            typography {
                +"State options... gender1Value by regular var is ${gender1Value}, state.gender2Value is ${state.gender2Value}"
            }

        }
    }
}
