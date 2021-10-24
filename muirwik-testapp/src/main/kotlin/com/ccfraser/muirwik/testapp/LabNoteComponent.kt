package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.card
import com.ccfraser.muirwik.components.cardContent
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.components.typography
import com.ccfraser.muirwik.testapp.LabNoteComponentStyles.paddingBottom
import kotlinx.css.*
import react.Props
import react.RBuilder
import react.fc
import styled.StyleSheet
import styled.css


private object LabNoteComponentStyles : StyleSheet("LabNoteComponentStyles", isStatic = true) {
    val paddingBottom by css {
        lastChild {
            paddingBottom = 2.spacingUnits
        }
    }
}

private val labNoteComponent = fc<Props> {
    card {
        cardContent {
            css(paddingBottom)
            typography("Note: ") {
                attrs.noWrap = true
                attrs.component = "span"
                css {
                    fontWeight = FontWeight.bold
                }
            }
            typography("This component is a Material UI lab component") {
                attrs.component = "span"
            }
        }
        css {
            width = LinearDimension.fitContent
        }
        css {
            marginBottom = 2.spacingUnits
        }
    }
}

fun RBuilder.labNoteComponent() {
    child(labNoteComponent)
}
