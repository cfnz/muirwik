package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mFab
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.mTooltip
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import styled.css
import styled.styledDiv

class TestTooltips : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        styledDiv {
            css { height = 100.px }
            mTypography {+"Simple Tooltips"}
            mTooltip("Delete", id = "tooltip-icon") {
                mIconButton("delete") { css { marginRight = 2.spacingUnits }}
            }
            mTooltip("Add", id = "tooltip-fab") {
                mFab("add", primary = true) { css { marginRight = 2.spacingUnits }}
            }
            mTooltip("No Delay", id = "tooltip-icon", enterDelay = 0) {
                mButton("No Delay") { css { marginRight = 2.spacingUnits }}
            }
            mTooltip("Long Delay", id = "tooltip-icon", enterDelay = 750) {
                mButton("Long Delay")
            }
            br {  }
            br {  }
            mTooltip("FAB") {
                mFab("add", color = MColor.secondary) {
                    css {
                        position = Position.absolute
                        bottom = 2.spacingUnits
                        right = 3.spacingUnits
                    }
                }
            }
        }
    }
}

fun RBuilder.testTooltips() = child(TestTooltips::class) {}
