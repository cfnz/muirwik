package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.Position
import kotlinx.css.px
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
                mButtonFab("add", primary = true) { css { marginRight = 2.spacingUnits }}
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
                mButtonFab("add", color = MColor.secondary) {
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
