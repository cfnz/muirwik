package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.wrapper.*
import kotlinext.js.js
import kotlinx.css.*
import kotlinx.css.properties.BoxShadow
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.style
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import react.dom.button
import react.dom.div
import react.dom.span
import styled.StyleSheet
import styled.css
import styled.styledSpan
import kotlin.browser.window


class TestBadges : RComponent<RProps, RState>() {

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val margin by css {
            margin(2.spacingUnits)
        }
        val padding by css {
            padding(0.px, (currentTheme.spacing.unit * 2).px)
        }
    }

    override fun RBuilder.render() {
        div {
            div {
                mBadge("4", color = MBadgeColor.Primary) {
                    css(ComponentStyles.margin)
                    mIcon("mail")
                }
                mBadge("10", color = MBadgeColor.Secondary) {
                    css(ComponentStyles.margin)
                    mIcon("mail")
                }
                mIconButton("mail") {
                    css(ComponentStyles.margin)
                    mBadge("4", color = MBadgeColor.Primary) {
                        css(ComponentStyles.margin)
                        mIcon("mail")
                    }
                }
            }
            mAppBar(position = MAppBarPosition.Static) {
                css(ComponentStyles.margin)
                mTabs(0) {
                    mTab(label = mBadge("4", color = MBadgeColor.Secondary, addAsChild = false) {
                        css(ComponentStyles.padding)
                        +"Item 1"
                    })
                    mTab(label = RBuilder().span { +"Item 2" })
                    mTab(label = RBuilder().span { +"Item 3" })
                }
            }
            mBadge("4", color = MBadgeColor.Primary) {
                css(ComponentStyles.margin)
                mTypography("Typography") { css(ComponentStyles.padding)}
            }
            mBadge("4", color = MBadgeColor.Primary) {
                css(ComponentStyles.margin)
                mButton("Button", variant = MButtonVariant.Contained)
            }
        }
    }
}

fun RBuilder.testBadges() = child(TestBadges::class) {}
