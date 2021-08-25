package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.form.mFormLabel
import kotlinx.css.*
import react.*
import react.dom.div
import react.dom.span
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestBadges : RComponent<Props, State>() {
    var anchorOriginHorizontal = MBadgeAnchorOriginHorizontal.left
    var anchorOriginVertical = MBadgeAnchorOriginVertical.top

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
                    mTab(label = buildElement { mBadge(4, color = MBadgeColor.secondary) {
                        css(ComponentStyles.padding)
                        +"Item 1"
                    }})
                    mTab("Item 2")
                    mTab("Item 3")
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
            mBadgeDot(MBadgeColor.secondary) {
                css(ComponentStyles.margin)
                mTypography("Dot badge")
            }

            mTypography("Overlap Prop") {
                css(ComponentStyles.margin)
            }

            themeContext.Consumer { theme ->
                mBadgeDot(MBadgeColor.secondary) {
                    css(ComponentStyles.margin)
                    styledDiv {
                        css {
                            backgroundColor = Color(theme.palette.primary.main)
                            width = 40.px
                            height = 40.px
                        }
                    }
                }
                mBadgeDot(MBadgeColor.secondary) {
                    css(ComponentStyles.margin)
                    attrs.overlap = MBadgeOverlap.circle
                    styledDiv {
                        css {
                            backgroundColor = Color(theme.palette.primary.main)
                            width = 40.px
                            height = 40.px
                            borderRadius = 50.pct
                        }
                    }
                }
            }

            mTypography("Badge Alignment") {
                css(ComponentStyles.margin)
            }
            mGridContainer {
                css(ComponentStyles.margin)
                mGridItem {
                    mFormControl {
                        mFormLabel("Vertical")
                        mRadioGroup(value = anchorOriginVertical.toString(), onChange = {_, value ->
                                setState { anchorOriginVertical = MBadgeAnchorOriginVertical.valueOf(value) }
                        }) {
                            mRadioWithLabel("Top", value = MBadgeAnchorOriginVertical.top.toString())
                            mRadioWithLabel("Bottom", value = MBadgeAnchorOriginVertical.bottom.toString())
                        }
                    }
                }
                mGridItem {
                    mFormControl {
                        mFormLabel("Horizontal")
                        mRadioGroup(value = anchorOriginHorizontal.toString(), onChange = { _, value ->
                                setState { anchorOriginHorizontal = MBadgeAnchorOriginHorizontal.valueOf(value) }
                        }) {
                            mRadioWithLabel("Left", value = MBadgeAnchorOriginHorizontal.left.toString())
                            mRadioWithLabel("Right", value = MBadgeAnchorOriginHorizontal.right.toString())
                        }
                    }
                }
            }
            mBadgeDot(MBadgeColor.primary) {
                css(ComponentStyles.margin)
                attrs.anchorOriginHorizontal = anchorOriginHorizontal
                attrs.anchorOriginVertical = anchorOriginVertical
                mIcon("mail", color = MIconColor.action)
            }
            mBadge(4, color = MBadgeColor.primary) {
                css(ComponentStyles.margin)
                attrs.anchorOriginHorizontal = anchorOriginHorizontal
                attrs.anchorOriginVertical = anchorOriginVertical
                mIcon("mail", color = MIconColor.action)
            }
            mBadge(14, color = MBadgeColor.primary) {
                css(ComponentStyles.margin)
                attrs.anchorOriginHorizontal = anchorOriginHorizontal
                attrs.anchorOriginVertical = anchorOriginVertical
                mIcon("mail", color = MIconColor.action)
            }
        }
    }
}
