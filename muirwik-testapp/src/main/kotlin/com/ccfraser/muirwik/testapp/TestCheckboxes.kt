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

@OptIn(ExperimentalJsExport::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class TestCheckboxes : RComponent<RProps, RState>() {
    private var checked1: Boolean = false
    private var checked2: Boolean = false
    private var checked3: Boolean = false
    private var checked4: Boolean = false

    override fun RBuilder.render() {
        styledDiv {
            css { display = Display.flex }
            styledDiv {
                mTypography("Checkboxes")
                mCheckbox(checked = checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                br { }
                mCheckbox(checked = checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                br { }
                mCheckbox(checked = checked3, onChange = { _, _ -> setState { checked3 = !checked3 } }) {
                    attrs.icon = mIcon("clear", addAsChild = false)
                    attrs.checkedIcon = mIcon("done", addAsChild = false)
                }
                br {  }
                mFormControlLabel("As Form Control", control = mCheckbox(checked4, MOptionColor.primary,
                        true, addAsChild = false, onChange = { _, _ -> setState { checked4 = !checked4 } }))
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
