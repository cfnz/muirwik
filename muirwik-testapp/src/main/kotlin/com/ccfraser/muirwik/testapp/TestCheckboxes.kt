package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.*
import kotlinx.css.Display
import kotlinx.css.display
import kotlinx.css.paddingLeft
import react.*
import react.dom.br
import styled.css
import styled.styledDiv

class TestCheckboxes : RComponent<RProps, RState>() {
    private var checked1: Boolean = false
    private var checked2: Boolean = false
    private var checked3: Boolean = false
    private var checked4: Boolean = false

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
            styledDiv {
                css { paddingLeft = 3.spacingUnits }
                mFormControl(component = MFormControlComponent.fieldSet) {
                    mFormLabel("In a FormGroup", component = "legend")
                    mFormGroup {
                        mCheckboxWithLabel("Option 1", checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                        mCheckboxWithLabel("Option 2", checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                        mCheckboxWithLabel("Option 3", checked3, onChange = { _, _ -> setState { checked3 = !checked3 } })
                    }
                }
            }
        }
    }
}

fun RBuilder.testCheckboxes() = child(TestCheckboxes::class) {}
