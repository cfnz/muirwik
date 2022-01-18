package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.HRefOptions
import com.ccfraser.muirwik.testapp.TestBreadcrumbs.ComponentStyles.icon
import com.ccfraser.muirwik.testapp.TestBreadcrumbs.ComponentStyles.paper
import kotlinx.css.*
import react.*
import react.dom.br
import styled.StyleSheet
import styled.css


class TestBreadcrumbs : RComponent<Props, State>() {

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val paper by css {
            padding(1.spacingUnits, 2.spacingUnits)
            width = LinearDimension.maxContent
        }
        val icon by css {
            marginRight = 1.spacingUnits / 2
            paddingTop = 3.px
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Simple Breadcrumbs") {
                paper {
                    css {
                        padding(1.spacingUnits, 2.spacingUnits)
                        width = LinearDimension.maxContent
                    }
                    breadcrumbs {
                        link("Material-UI", HRefOptions("#"))
                        link("Core", HRefOptions("#"))
                        typography("Breadcrumb")
                    }
                }
            }
            demoPanel("Custom Separator") {
                paper {
                    css(paper)
                    breadcrumbs(">") {
                        link("Material-UI", HRefOptions("#"))
                        link("Core", HRefOptions("#"))
                        typography("Breadcrumb")
                    }
                }
                br {}
                paper {
                    css(paper)
                    breadcrumbs("-") {
                        link("Material-UI", HRefOptions("#"))
                        link("Core", HRefOptions("#"))
                        typography("Breadcrumb")
                    }
                }
                br {}
                paper {
                    css(paper)
                    breadcrumbs {
                        attrs.separator = buildElement { icon("navigate_next") { attrs.fontSize = IconFontSize.small} }
                        link("Material-UI", HRefOptions("#"))
                        link("Core", HRefOptions("#"))
                        typography("Breadcrumb")
                    }
                }
            }
            demoPanel("Breadcrumbs with Icons") {
                paper {
                    css(paper)
                    breadcrumbs {
                        link {
                            attrs.hrefOptions = HRefOptions("#")
                            icon("home") {
                                attrs.fontSize = IconFontSize.small
                                css(icon)
                            }
                            +"Material-UI"
                        }
                        link {
                            attrs.hrefOptions = HRefOptions("#")
                            icon("whatshot") {
                                attrs.fontSize = IconFontSize.small
                                css(icon)
                            }
                            +"Core"
                        }
                        typography {
                            icon("grain") {
                                attrs.fontSize = IconFontSize.small
                                css(icon)
                            }
                            +"Breadcrumb"
                        }
                    }
                }
            }
            demoPanel("Collapsed Breadcrumbs") {
                paper {
                    css(paper)
                    breadcrumbs {
                        attrs.maxItems = 2
                        link("Home", HRefOptions("#"))
                        link("Catalog", HRefOptions("#"))
                        link("Accessories", HRefOptions("#"))
                        link("New Collection", HRefOptions("#"))
                        typography("Belts")
                    }
                }
            }
        }
    }
}
