package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.*
import react.*
import styled.css

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
                accordion {
                    accordionSummary {
                        attrs.expandIcon = buildElement { icon("expand_more") }
                        typography("Accordion Item 1")
                    }
                    accordionDetails {
                        typography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
                accordion {
                    accordionSummary {
                        attrs.expandIcon = buildElement { icon("expand_more") }
                        typography("Accordion Item 2")
                    }
                    accordionDetails {
                        typography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
                accordion {
                    attrs.disabled = true
                    accordionSummary {
                        attrs.expandIcon = buildElement { icon("expand_more") }
                        typography("Accordion Item 3")
                    }
                    accordionDetails {
                        typography(
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
                accordion {
                    attrs.expanded = expandedItem == "panel1"
                    attrs.onChange = { _, expanded -> handleExpanded(expanded, "panel1") }
                    accordionSummary {
                        attrs.expandIcon = buildElement { icon("expand_more") }
                        typography("Accordion Item 1")
                    }
                    accordionDetails {
                        typography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
                accordion {
                    attrs.expanded = expandedItem == "panel2"
                    attrs.onChange = { _, expanded -> handleExpanded(expanded, "panel2") }
                    accordionSummary {
                        attrs.expandIcon = buildElement { icon("expand_more") }
                        typography("Accordion Item 2")
                    }
                    accordionDetails {
                        typography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
                accordion {
                    attrs.expanded = expandedItem == "panel3"
                    attrs.onChange = { _, expanded -> handleExpanded(expanded, "panel3") }
                    accordionSummary {
                        attrs.expandIcon = buildElement { icon("expand_more") }
                        typography("Accordion Item 3")
                    }
                    accordionDetails {
                        typography(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, " +
                                    "sit amet blandit leo lobortis eget."
                        )
                    }
                }
            }
        }
    }
}
