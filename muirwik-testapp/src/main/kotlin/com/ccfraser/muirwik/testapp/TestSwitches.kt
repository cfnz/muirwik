package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.mFormControlLabel
import kotlinx.css.Display
import kotlinx.css.display
import react.*
import react.dom.br
import react.dom.div
import styled.css
import styled.styledDiv

class TestSwitches : RComponent<RProps, RState>() {
    var checked1: Boolean = false
    var checked2: Boolean = false
    var checked3: Boolean = false
    var checked4: Boolean = false

    override fun RBuilder.render() {
        styledDiv {
            css { display = Display.flex }
            div {
                mTypography("Switches")
                mSwitch(checked = checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                br { }
                mSwitch(checked = checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                br { }
                // The icons are more for a checkbox type control, but for fun, we shall put the star on the switch
                mSwitch(checked = checked3, onChange = { _, _ -> setState { checked3 = !checked3 } }) {
                        attrs.icon = mIcon("star", addAsChild = false)
                        attrs.checkedIcon = mIcon("star", addAsChild = false)
                }
                br {  }
                mFormControlLabel("As Form Control", control = mSwitch(checked4, MOptionColor.primary,
                        addAsChild = false, onChange = { _, _ -> setState { checked4 = !checked4 } }))
            }
            div {
                mTypography("Using 'WithLabel'")
                mSwitchWithLabel("Option 1", checked = checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                br { }
                mSwitchWithLabel("Option 2", checked = checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                br { }
                mSwitchWithLabel("Option 3 - A star?", disabled = true, onChange = { _, _ -> setState { checked3 = !checked3 } })
            }
        }
    }
}

fun RBuilder.testSwitches() = child(TestSwitches::class) {}
