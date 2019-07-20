package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.*
import com.ccfraser.muirwik.testapp.TestOptionControls.ComponentStyles.paddingLeft12
import kotlinx.css.Display
import kotlinx.css.px
import react.*
import react.dom.br
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestOptionControls : RComponent<RProps, TestOptionControls.MyTestState>() {
    var checked1: Boolean = false
    var checked2: Boolean = false
    var checked3: Boolean = false
    var checked4: Boolean = false
    var radioValue: String = "a"
    var gender1Value: String = "female"

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

        styledDiv {
            css { display = Display.flex }
            styledDiv {
                mTypography("Checkboxes")
                mCheckbox(checked = checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                br { }
                mCheckbox(checked = checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                br { }
                val uncheckedIcon = altBuilder.mIcon("clear")
                val checkedIcon = altBuilder.mIcon("done")
                mCheckbox(checked = checked3, onChange = { _, _ -> setState { checked3 = !checked3 } }, icon = uncheckedIcon, checkedIcon = checkedIcon)
                br {  }
                val s = altBuilder.mCheckbox(checked4,false, true, onChange = { _, _ -> setState { checked4 = !checked4 } })
                mFormControlLabel("As Form Control", control = s)
            }
            div {
                mTypography("Switches")
                mSwitch(checked = checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                br { }
                mSwitch(checked = checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                br { }
                // The icons are more for a checkbox type control, but for fun, we shall put the star on the switch
                mSwitch(checked = checked3, onChange = { _, _ -> setState { checked3 = !checked3 } },
                        icon = mIcon("star", addAsChild = false),
                        checkedIcon = mIcon("star", addAsChild = false))
                br {  }
                val s = altBuilder.mSwitch(checked4,false, true, onChange = { _, _ -> setState { checked4 = !checked4 } })
                mFormControlLabel("As Form Control", control = s)
            }
            div {
                mTypography("Using 'WithLabel'")
                mSwitchInLabel("Option 1", checked = checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                br { }
                mSwitchInLabel("Option 2", checked = checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                br { }
                mSwitchInLabel("Option 3", disabled = true, onChange = { _, _ -> setState { checked3 = !checked3 } })
            }
            styledDiv {
                css { paddingLeft = 3.spacingUnits }
                mFormControl(component = MFormControlComponent.fieldSet) {
                    mFormLabel("In a FormGroup", component = "legend")
                    mFormGroup {
                        mCheckboxInLabel("Option 1", checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                        mCheckboxInLabel("Option 2", checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                        mCheckboxInLabel("Option 3", checked3, onChange = { _, _ -> setState { checked3 = !checked3 } })
                    }
                }
            }
        }
        br { }
        mTypography("Radio options and groups")
        styledDiv {
            css { display = Display.flex; marginTop = 16.px }
            mRadioGroup(value = radioValue, onChange = { _, value -> setState { radioValue = value } }) {
                css { display = Display.inlineFlex }
                mRadio(value = "a")
                // The icons are more for a checkbox type control, but for fun, we shall put the star here too
                mRadio(value = "b", icon = altBuilder.mIcon("star"))
                mFormControlLabel("My Label C", value = "c", control = altBuilder.mRadio()) { css(paddingLeft12) } // With a label it seems to need a bit of left padding
                mFormControlLabel("My Label D", value = "d", control = altBuilder.mRadio()) { css(paddingLeft12) } // With a label it seems to need a bit of left padding
            }
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
                    mRadioInLabel("Male", value = "male", labelPlacement = MLabelPlacement.start)
                    mRadioInLabel("Female", value = "female", labelPlacement = MLabelPlacement.start)
                    mRadioInLabel("Other", value = "other", labelPlacement = MLabelPlacement.start)
                    mRadioInLabel("Disabled Option", value = "disabled", disabled = true, labelPlacement = MLabelPlacement.start)
                }
                mTypography("Label Placement = start")
            }
        }
        mTypography {
            +"State options... gender1Value by regular var is ${gender1Value}, state.gender2Value is ${state.gender2Value}"
        }
    }
}

fun RBuilder.testOptionControls() = child(TestOptionControls::class) {}
