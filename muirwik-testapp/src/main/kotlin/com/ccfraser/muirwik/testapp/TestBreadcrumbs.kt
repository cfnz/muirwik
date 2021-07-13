package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.testapp.TestBreadcrumbs.ComponentStyles.icon
import com.ccfraser.muirwik.testapp.TestBreadcrumbs.ComponentStyles.paper
import kotlinx.css.*
import react.*
import react.dom.br
import styled.StyleSheet
import styled.css


class TestBreadcrumbs : RComponent<RProps, RState>() {

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
        mTypography("Simple Breadcrumbs")
        mPaper {
            css {
                padding(1.spacingUnits, 2.spacingUnits)
                width = LinearDimension.maxContent
            }
            mBreadcrumbs {
                mLink("Material-UI", HRefOptions("#"))
                mLink("Core", HRefOptions("#"))
                mTypography("Breadcrumb")
            }
        }

        br {}

        mTypography("Custom Separator")
        mPaper {
            css(paper)
            mBreadcrumbs(">") {
                mLink("Material-UI", HRefOptions("#"))
                mLink("Core", HRefOptions("#"))
                mTypography("Breadcrumb")
            }
        }
        br {}
        mPaper {
            css(paper)
            mBreadcrumbs("-") {
                mLink("Material-UI", HRefOptions("#"))
                mLink("Core", HRefOptions("#"))
                mTypography("Breadcrumb")
            }
        }
        br {}
        mPaper {
            css(paper)
            mBreadcrumbs(mIcon("navigate_next", fontSize = MIconFontSize.small, addAsChild = false)) {
                mLink("Material-UI", HRefOptions("#"))
                mLink("Core", HRefOptions("#"))
                mTypography("Breadcrumb")
            }
        }

        br {}

        mTypography("Breadcrumbs with Icons")
        mPaper {
            css(paper)
            mBreadcrumbs {
                mLink(hRefOptions = HRefOptions("#")) {
                    mIcon("home", fontSize = MIconFontSize.small) { css(icon) }
                    +"Material-UI"
                }
                mLink(hRefOptions = HRefOptions("#")) {
                    mIcon("whatshot", fontSize = MIconFontSize.small) { css(icon) }
                    +"Core"
                }
                mTypography {
                    mIcon("grain", fontSize = MIconFontSize.small) { css(icon) }
                    +"Breadcrumb"
                }
            }
        }

        br {}

        mTypography("Collapsed Breadcrumbs")
        mPaper {
            css(paper)
            mBreadcrumbs(maxItems = 2) {
                mLink("Home", HRefOptions("#"))
                mLink("Catalog", HRefOptions("#"))
                mLink("Accessories", HRefOptions("#"))
                mLink("New Collection", HRefOptions("#"))
                mTypography("Belts")
            }
        }
    }
}

fun RBuilder.testBreadcrumbs() = child(TestBreadcrumbs::class) {}
