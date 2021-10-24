package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.*
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import styled.css

class TestTooltips : RComponent<Props, State>() {

    override fun RBuilder.render() {
        demoPanel("Simple Tooltips") {
            tooltip("Delete") {
                iconButton("delete") { css { marginRight = 2.spacingUnits }}
            }
            tooltip("Add") {
                fab("add") { css { marginRight = 2.spacingUnits }}
            }
            tooltip("No Delay") {
                attrs.enterDelay = 0
                button("No Delay") { css { marginRight = 2.spacingUnits }}
            }
            tooltip("Long Delay") {
                attrs.enterDelay = 750
                button("Long Delay")
            }
            tooltip("With Arrow", arrow = true) {
                button("With Arrow")
            }
            tooltip("On the right", TooltipPlacement.right) {
                button("On Right")
            }
            tooltip("FAB") {
                fab("add", FabColor.secondary) {
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
