package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.ControlColor
import kotlinx.css.paddingLeft
import react.*
import styled.css

class TestCheckboxes : RComponent<Props, State>() {
    private var checked1: Boolean = false
    private var checked2: Boolean = false
    private var checked3: Boolean = false
    private var checked4: Boolean = false

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Checkboxes") {
                checkbox(checked1) { attrs. onChange = { _, _ -> setState { checked1 = !checked1 } } }
                checkbox(checked2) { attrs. onChange = { _, _ -> setState { checked2 = !checked2 } } }
                checkbox(checked3) { attrs. onChange = { _, _ -> setState { checked3 = !checked3 } }
                    attrs.icon = buildElement { icon("clear") }
                    attrs.checkedIcon = buildElement { icon("done") }
                }
                formControlLabel(
                    "As Form Control",
                    control = buildElement {
                        checkbox(checked4, ControlColor.primary) {
                            attrs.disabled = true
                            attrs.onChange = { _, _ -> setState { checked4 = !checked4 } }
                        }
                    }
                )
            }
            demoPanel("With Labels") {
                css { paddingLeft = 3.spacingUnits }
                formControl {
                    attrs.component = FormControlComponent.fieldSet
                    formLabel("In a FormGroup") { attrs.component = "legend" }
                    mFormGroup {
                        checkboxWithLabel("Option 1", checked1) { attrs.onChange = { _, _ -> setState { checked1 = !checked1 } } }
                        checkboxWithLabel("Option 2", checked2) { attrs.onChange = { _, _ -> setState { checked2 = !checked2 } } }
                        checkboxWithLabel("Option 3", checked3) { attrs.onChange = { _, _ -> setState { checked3 = !checked3 } } }
                    }
                }
            }
        }
    }
}
