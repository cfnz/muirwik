package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.*
import com.ccfraser.muirwik.testapp.TestRadioButtons.ComponentStyles.paddingLeft12
import kotlinx.css.*
import react.*
import react.dom.br
import styled.StyleSheet
import styled.css
import styled.styledDiv

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
                mRadioGroup(value = radioValue, onChange = { _, value -> setState { radioValue = value } }) {
                    css { display = Display.inlineFlex }
                    mRadio(value = "a")
                    // The icons are more for a checkbox type control, but for fun, we shall put the star here too
                    mRadio(value = "b") {
                        attrs.icon = buildElement { mIcon("star") }
                    }
                }
                mRadioGroup(value = radioValue, onChange = { _, value -> setState { radioValue = value } }) {
                    css { display = Display.inlineFlex }
                    mFormControlLabel("With Label C", value = "c", control =  buildElement { mRadio() }) { css(paddingLeft12) } // With a label it seems to need a bit of left padding
                    mFormControlLabel("With Label D", value = "d", control =  buildElement { mRadio() }) { css(paddingLeft12) } // With a label it seems to need a bit of left padding
                }
            }
            demoPanel("As Form Controls") {
                css { display = Display.inlineFlex }
                mFormControl(component = MFormControlComponent.fieldSet) {
                    css { display = Display.inlineFlex }
                    mFormLabel("Gender", required = true, component = "legend")
                    mRadioGroup(value = state.gender2Value, onChange = { _, value -> setState { gender2Value = value; println("Value: $value") } }) {
                        mFormControlLabel("Female", value = "female", control = buildElement { mRadio() })
                        mFormControlLabel("Male", value = "male", control = buildElement { mRadio() })
                        mFormControlLabel("Other", value = "other", control = buildElement { mRadio() })
                        mFormControlLabel("Disabled Option", value = "disabled", disabled = true, control =  buildElement { mRadio() })
                    }
                }
                mFormControl(component = MFormControlComponent.fieldSet, required = true, error = true) {
                    css { display = Display.inlineFlex }
                    mFormLabel("Gender2", required = true, component = "legend")
                    mRadioGroup(value = gender1Value, name = "gender1", onChange = { _, value -> setState { gender1Value = value; println("Value: $value") } }) {
                        mFormControlLabel("Male", value = "male", control = buildElement { mRadio() })
                        mFormControlLabel("Female", value = "female", control = buildElement { mRadio() })
                        mFormControlLabel("Other", value = "other", control = buildElement { mRadio() })
                        mFormControlLabel("Disabled Option", value = "disabled", disabled = true, control = buildElement { mRadio() })
                    }
                }
                mFormControl(component = MFormControlComponent.fieldSet, required = true, error = true) {
                    css { display = Display.inlineFlex }
                    mFormLabel("Gender3", required = true, component = "legend")
                    mRadioGroup(value = gender1Value, name = "gender1", onChange = { _, value -> setState { gender1Value = value; println("Value: $value") } }) {
                        // Slightly shorthand, practically does the same as the mFormControlLabel above.
                        mRadioWithLabel("Male", color = MOptionColor.primary, value = "male", labelPlacement = MLabelPlacement.start)
                        mRadioWithLabel("Female", color = MOptionColor.primary, value = "female", labelPlacement = MLabelPlacement.start)
                        mRadioWithLabel("Other", color = MOptionColor.primary, value = "other", labelPlacement = MLabelPlacement.start)
                        mRadioWithLabel("Disabled Option", value = "disabled", disabled = true, labelPlacement = MLabelPlacement.start)
                    }
                    mTypography("Label Placement = start")
                }
            }
            mTypography {
                +"State options... gender1Value by regular var is ${gender1Value}, state.gender2Value is ${state.gender2Value}"
            }

        }
    }
}
