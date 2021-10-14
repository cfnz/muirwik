package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.accordion.mAccordion
import com.ccfraser.muirwik.components.accordion.mAccordionDetails
import com.ccfraser.muirwik.components.accordion.mAccordionSummary
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import kotlinx.css.*
import react.*
import styled.css
import styled.styledDiv

class TestAccordion : RComponent<Props, State>() {
    private var expandedItem: String? = null

    private fun handleExpanded(expanded: Boolean, panelName: String) {
        setState { expandedItem = if (expanded) panelName else null }
    }

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Simple Accordion (was ExpansionPanel, but ExpansionPanel is Deprecated in Material-UI 4.11.0, replaced with Accordion)") {
                css {
                    padding(2.spacingUnits)
                }
                mAccordion {
                    mAccordionSummary(expandIcon = buildElement { mIcon("expand_more") }) {
                        mTypography("Accordion Item 1")
                    }
                    mAccordionDetails {
                        mTypography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
                mAccordion {
                    mAccordionSummary(expandIcon = buildElement { mIcon("expand_more") }) {
                        mTypography("Accordion Item 2")
                    }
                    mAccordionDetails {
                        mTypography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
                mAccordion {
                    attrs.disabled = true
                    mAccordionSummary(expandIcon = buildElement { mIcon("expand_more") }) {
                        mTypography("Accordion Item 3")
                    }
                    mAccordionDetails {
                        mTypography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
            }

            demoPanel("Controlled Accordion Panel") {
                css {
                    padding(2.spacingUnits)
                }
                mAccordion {
                    attrs.expanded = expandedItem == "panel1"
                    attrs.onChange = { _, expanded -> handleExpanded(expanded, "panel1") }
                    mAccordionSummary(expandIcon = buildElement { mIcon("expand_more") }) {
                        mTypography("Accordion Item 1")
                    }
                    mAccordionDetails {
                        mTypography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
                mAccordion {
                    attrs.expanded = expandedItem == "panel2"
                    attrs.onChange = { _, expanded -> handleExpanded(expanded, "panel2") }
                    mAccordionSummary(expandIcon = buildElement { mIcon("expand_more") }) {
                        mTypography("Accordion Item 2")
                    }
                    mAccordionDetails {
                        mTypography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
                mAccordion {
                    attrs.expanded = expandedItem == "panel3"
                    attrs.onChange = { _, expanded -> handleExpanded(expanded, "panel3") }
                    mAccordionSummary(expandIcon = buildElement { mIcon("expand_more") }) {
                        mTypography("Accordion Item 3")
                    }
                    mAccordionDetails {
                        mTypography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
            }
        }
    }
}
