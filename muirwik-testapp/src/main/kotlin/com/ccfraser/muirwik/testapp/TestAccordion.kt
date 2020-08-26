package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.accordion.mAccordion
import com.ccfraser.muirwik.components.accordion.mAccordionDetails
import com.ccfraser.muirwik.components.accordion.mAccordionSummary
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import kotlinx.css.padding
import kotlinx.css.pct
import kotlinx.css.width
import react.*
import styled.css
import styled.styledDiv

class TestAccordion : RComponent<RProps, RState>() {
    private var expandedItem: String? = null

    private fun handleExpanded(expanded: Boolean, panelName: String) {
        setState { expandedItem = if (expanded) panelName else null }
    }

    override fun RBuilder.render() {
        mTypography("Simple Accordion (was ExpansionPanel, but that is Deprecated, replaced with Accordion)")
        styledDiv {
            css {
                padding(2.spacingUnits)
                width = 100.pct
            }
            mAccordion {
                mAccordionSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 1")
                }
                mAccordionDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
            mAccordion {
                mAccordionSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 2")
                }
                mAccordionDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
            mAccordion(disabled = true) {
                mAccordionSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 3")
                }
                mAccordionDetails {
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
            mAccordion(expandedItem == "panel1", onChange = { _, expanded -> handleExpanded(expanded, "panel1")}) {
                mAccordionSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 1")
                }
                mAccordionDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
            mAccordion(expandedItem == "panel2", onChange = { _, expanded -> handleExpanded(expanded, "panel2")}) {
                mAccordionSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 2")
                }
                mAccordionDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
            mAccordion(expandedItem == "panel3", onChange = { _, expanded -> handleExpanded(expanded, "panel3")}) {
                mAccordionSummary(expandIcon = mIcon("expand_more", addAsChild = false)) {
                    mTypography("Expansion Panel 3")
                }
                mAccordionDetails {
                    mTypography("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                            "sit amet blandit leo lobortis eget.")
                }
            }
        }
    }
}

fun RBuilder.testAccordion() = child(TestAccordion::class) {}
