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

class TestRadioButtons : RComponent<RProps, TestRadioButtons.MyTestState>() {
    private var radioValue: String = "a"
    private var gender1Value: String = "female"

    class MyTestState(var gender2Value: String) : RState

    init {
        state = MyTestState("male")
    }

    object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val paddingLeft12 by css {
            paddingLeft = 12.px
        }
    }

    override fun RBuilder.render() {
        // For building things that we don't want to render now (e.g. the component will render it later), we need another builder
        // Update: Now you can actually pass in addAsChild as false instead.
        val altBuilder = RBuilder()

        mTypography("Radio options and groups")
        styledDiv {
            css { display = Display.inlineFlex; marginTop = 16.px }
            mRadioGroup(value = radioValue, onChange = { _, value -> setState { radioValue = value } }) {
                css { display = Display.inlineFlex }
                mRadio(value = "a")
                // The icons are more for a checkbox type control, but for fun, we shall put the star here too
                mRadio(value = "b", icon = altBuilder.mIcon("star"))
            }
            mRadioGroup(value = radioValue, onChange = { _, value -> setState { radioValue = value } }) {
                css { display = Display.inlineFlex }
                mFormControlLabel("With Label C", value = "c", control = altBuilder.mRadio()) { css(paddingLeft12) } // With a label it seems to need a bit of left padding
                mFormControlLabel("With Label D", value = "d", control = altBuilder.mRadio()) { css(paddingLeft12) } // With a label it seems to need a bit of left padding
            }
        }
        br {}
        styledDiv {
            css { display = Display.inlineFlex; marginTop = 16.px }
            mFormControl(component = MFormControlComponent.fieldSet) {
                css { display = Display.inlineFlex }
                mFormLabel("Gender", required = true, component = "legend")
                mRadioGroup(value = state.gender2Value, onChange = { _, value -> setState { gender2Value = value; println("Value: $value") } }) {
                    mFormControlLabel("Female", value = "female", control = altBuilder.mRadio())
                    mFormControlLabel("Male", value = "male", control = altBuilder.mRadio())
                    mFormControlLabel("Other", value = "other", control = altBuilder.mRadio())
                    mFormControlLabel("Disabled Option", value = "disabled", disabled = true, control = altBuilder.mRadio())
                }
            }
            mFormControl(component = MFormControlComponent.fieldSet, required = true, error = true) {
                css { display = Display.inlineFlex }
                mFormLabel("Gender2", required = true, component = "legend")
                mRadioGroup(value = gender1Value, name = "gender1", onChange = { _, value -> setState { gender1Value = value; println("Value: $value") } }) {
                    mFormControlLabel("Male", value = "male", control = altBuilder.mRadio(primary = false))
                    mFormControlLabel("Female", value = "female", control = altBuilder.mRadio(primary = false))
                    mFormControlLabel("Other", value = "other", control = altBuilder.mRadio(primary = false))
                    mFormControlLabel("Disabled Option", value = "disabled", disabled = true, control = altBuilder.mRadio())
                }
            }
            mFormControl(component = MFormControlComponent.fieldSet, required = true, error = true) {
                css { display = Display.inlineFlex }
                mFormLabel("Gender3", required = true, component = "legend")
                mRadioGroup(value = gender1Value, name = "gender1", onChange = { _, value -> setState { gender1Value = value; println("Value: $value") } }) {
                    // Slightly shorthand, practically does the same as the mFormControlLabel above.
                    mRadioWithLabel("Male", value = "male", labelPlacement = MLabelPlacement.start)
                    mRadioWithLabel("Female", value = "female", labelPlacement = MLabelPlacement.start)
                    mRadioWithLabel("Other", value = "other", labelPlacement = MLabelPlacement.start)
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

fun RBuilder.testRadioButtons() = child(TestRadioButtons::class) {}
