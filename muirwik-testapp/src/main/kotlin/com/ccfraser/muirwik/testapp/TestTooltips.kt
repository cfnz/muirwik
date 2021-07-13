package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mFab
import com.ccfraser.muirwik.components.button.mIconButton
import kotlinx.css.*
import react.*
import react.dom.br
import styled.css
import styled.styledDiv

class TestTooltips : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        styledDiv {
            css { height = 100.px }
            mTypography {+"Simple Tooltips"}
            mTooltip("Delete") {
                mIconButton("delete") { css { marginRight = 2.spacingUnits }}
            }
            mTooltip("Add") {
                mFab("add", MColor.primary) { css { marginRight = 2.spacingUnits }}
            }
            mTooltip("No Delay", enterDelay = 0) {
                mButton("No Delay") { css { marginRight = 2.spacingUnits }}
            }
            mTooltip("Long Delay", enterDelay = 750) {
                mButton("Long Delay")
            }
            mTooltip("With Arrow", arrow = true) {
                mButton("With Arrow")
            }
            mTooltip("On the right", TooltipPlacement.right) {
                mButton("On Right")
            }
            br {  }
            br {  }
            mTooltip("FAB") {
                mFab("add", MColor.secondary) {
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
