package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.span
import styled.StyleSheet
import styled.css


class TestBadges : RComponent<RProps, RState>() {
    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val margin by css {
            margin(2.spacingUnits)
        }
        val padding by css {
            padding(0.px, 2.spacingUnits)
        }
    }

    override fun RBuilder.render() {
        div {
            mBadge(4, color = MBadgeColor.primary) {
                css(ComponentStyles.margin)
                mIcon("mail", color = MIconColor.action)
            }
            mBadge(10, color = MBadgeColor.secondary) {
                css(ComponentStyles.margin)
                mIcon("mail")
            }

            mAppBar(position = MAppBarPosition.static) {
                css(ComponentStyles.margin)
                mTabs(0) {
                    mTab(label = mBadge(4, color = MBadgeColor.secondary, addAsChild = false) {
                        css(ComponentStyles.padding)
                        +"Item 1"
                    })
                    mTab(label = RBuilder().span { +"Item 2" })
                    mTab(label = RBuilder().span { +"Item 3" })
                }
            }
            mBadge(100, color = MBadgeColor.primary) {
                css(ComponentStyles.margin)
                mTypography("Typography") { css(ComponentStyles.padding)}
            }
            mBadge("Hello", color = MBadgeColor.primary) {
                css(ComponentStyles.margin)
                mButton("Button", variant = MButtonVariant.contained)
            }
            mBadge(100, color = MBadgeColor.secondary, variant = MBadgeVariant.dot) {
                css(ComponentStyles.margin)
                mTypography("Typography")
            }
        }
    }
}

fun RBuilder.testBadges() = child(TestBadges::class) {}
