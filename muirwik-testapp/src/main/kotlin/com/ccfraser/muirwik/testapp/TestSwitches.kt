package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.mFormControlLabel
import kotlinx.css.Display
import kotlinx.css.display
import kotlinx.css.marginTop
import kotlinx.css.px
import react.*
import react.dom.br
import react.dom.div
import styled.css
import styled.styledDiv

class TestSwitches : RComponent<Props, State>() {
    var checked1: Boolean = false
    var checked2: Boolean = false
    var checked3: Boolean = false
    var checked4: Boolean = false

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Switches") {
                mSwitch(checked = checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                br { }
                mSwitch(checked = checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                br { }
                // The icons are more for a checkbox type control, but for fun, we shall put the star on the switch
                mSwitch(
                    checked = checked3,
                    onChange = { _, _ -> setState { checked3 = !checked3 } }
                ) {
                    attrs.icon = buildElement {
                        mIcon("star", color = MIconColor.action) {
                            css { marginTop = -(2.px) }
                        }
                    }
                    attrs.checkedIcon = buildElement {
                        mIcon("star") {
                            css { marginTop = -(2.px) }
                        }
                    }
                }
                br {  }
                mFormControlLabel(
                    "As Form Control",
                    control = buildElement {
                        mSwitch(checked4,
                            MOptionColor.primary,
                            onChange = { _, _ -> setState { checked4 = !checked4 } }
                        )
                    }
                )
            }
            demoPanel("Using 'WithLabel'") {
                mSwitchWithLabel("Option 1", checked = checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                br { }
                mSwitchWithLabel("Option 2", checked = checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                br { }
                mSwitchWithLabel("Option 3 - A star?", disabled = true, onChange = { _, _ -> setState { checked3 = !checked3 } })
            }
        }
    }
}
