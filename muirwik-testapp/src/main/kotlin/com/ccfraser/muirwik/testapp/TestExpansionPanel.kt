package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.expansionpanel.mExpansionPanel
import com.ccfraser.muirwik.components.expansionpanel.mExpansionPanelDetails
import com.ccfraser.muirwik.components.expansionpanel.mExpansionPanelSummary
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import kotlinx.css.padding
import kotlinx.css.pct
import kotlinx.css.width
import react.*
import styled.css
import styled.styledDiv

class TestExpansionPanel : RComponent<RProps, RState>() {
    private var expandedItem: String? = null

    private fun handleExpanded(expanded: Boolean, panelName: String) {
        setState { expandedItem = if (expanded) panelName else null }
    }

    override fun RBuilder.render() {
        mTypography("Simple Expansion Panel")
        styledDiv {
            css {
                padding(2.spacingUnits)
                width = 100.pct
            }
            mExpansionPanel {
                mExpansionPanelSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 1")
                }
                mExpansionPanelDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
            mExpansionPanel {
                mExpansionPanelSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 2")
                }
                mExpansionPanelDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
            mExpansionPanel(disabled = true) {
                mExpansionPanelSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 3")
                }
                mExpansionPanelDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
        }

        mTypography("Controlled Accordion Panel")
        styledDiv {
            css {
                padding(2.spacingUnits)
                width = 100.pct
            }
            mExpansionPanel(expandedItem == "panel1", onChange = { _, expanded -> handleExpanded(expanded, "panel1")}) {
                mExpansionPanelSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 1")
                }
                mExpansionPanelDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
            mExpansionPanel(expandedItem == "panel2", onChange = { _, expanded -> handleExpanded(expanded, "panel2")}) {
                mExpansionPanelSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 2")
                }
                mExpansionPanelDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
            mExpansionPanel(expandedItem == "panel3", onChange = { _, expanded -> handleExpanded(expanded, "panel3")}) {
                mExpansionPanelSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 3")
                }
                mExpansionPanelDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
        }
    }
}

fun RBuilder.testExpansionPanel() = child(TestExpansionPanel::class) {}
