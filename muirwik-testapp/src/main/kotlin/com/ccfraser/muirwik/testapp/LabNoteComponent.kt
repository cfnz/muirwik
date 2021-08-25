package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.testapp.LabNoteComponentStyles.paddingBottom
import kotlinx.css.*
import react.*
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
    mCard {
        mCardContent {
            css(paddingBottom)
            mTypography("Note: ", noWrap = true, component = "span") {
                css {
                    fontWeight = FontWeight.bold
                }
            }
            mTypography("This component is a Material UI lab component", component = "span")
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
