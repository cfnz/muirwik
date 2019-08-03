package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.form.mFormControlLabel
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mSwitch
import com.ccfraser.muirwik.components.mSwitchWithLabel
import com.ccfraser.muirwik.components.mTypography
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
        // For building things that we don't want to render now (e.g. the component will render it later), we need another builder
        // Update: Now you can actually pass in addAsChild as false instead.
        val altBuilder = RBuilder()

        styledDiv {
            css { display = Display.flex }
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
                mSwitchWithLabel("Option 1", checked = checked1, onChange = { _, _ -> setState { checked1 = !checked1 } })
                br { }
                mSwitchWithLabel("Option 2", checked = checked2, onChange = { _, _ -> setState { checked2 = !checked2 } })
                br { }
                mSwitchWithLabel("Option 3", disabled = true, onChange = { _, _ -> setState { checked3 = !checked3 } })
            }
        }
    }
}

fun RBuilder.testSwitches() = child(TestSwitches::class) {}
