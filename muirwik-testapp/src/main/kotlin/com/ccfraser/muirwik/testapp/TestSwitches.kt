package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.ControlColor
import kotlinx.css.marginTop
import kotlinx.css.px
import react.*
import react.dom.br
import styled.css

class TestSwitches : RComponent<Props, State>() {
    private var checked1: Boolean = false
    private var checked2: Boolean = false
    private var checked3: Boolean = false
    private var checked4: Boolean = false

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Switches") {
                switch(checked1) { attrs.onChange = { _, _ -> setState { checked1 = !checked1 } } }
                br { }
                switch(checked2) { attrs.onChange = { _, _ -> setState { checked2 = !checked2 } } }
                br { }
                // The icons are more for a checkbox type control, but for fun, we shall put the star on the switch
                switch(checked3) {
                    attrs.onChange = { _, _ -> setState { checked3 = !checked3 } }
                    attrs.icon = buildElement {
                        icon("star", color = IconColor.action) {
                            css { marginTop = -(2.px) }
                        }
                    }
                    attrs.checkedIcon = buildElement {
                        icon("star") {
                            css { marginTop = -(2.px) }
                        }
                    }
                }
                br {  }
                formControlLabel("As Form Control", buildElement {
                    switch(checked4, ControlColor.primary) { attrs.onChange = { _, _ -> setState { checked4 = !checked4 } } }
                })
            }
            demoPanel("Using 'WithLabel'") {
                switchWithLabel("Option 1", checked = checked1) { attrs.onChange = { _, _ -> setState { checked1 = !checked1 } } }
                br { }
                switchWithLabel("Option 2", checked = checked2) { attrs.onChange = { _, _ -> setState { checked2 = !checked2 } } }
                br { }
                switchWithLabel("Option 3 - A star?") {
                    attrs.disabled = true
                    attrs.onChange = { _, _ -> setState { checked3 = !checked3 } }
                }
            }
        }
    }
}
