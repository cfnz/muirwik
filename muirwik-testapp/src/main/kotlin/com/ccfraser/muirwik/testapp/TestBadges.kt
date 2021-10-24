package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestBadges : RComponent<Props, State>() {
    var anchorOriginHorizontal = BadgeAnchorOriginHorizontal.left
    var anchorOriginVertical = BadgeAnchorOriginVertical.top

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val margin by css {
            margin(horizontal = 2.spacingUnits)
        }
        val padding by css {
            padding(0.px, 2.spacingUnits)
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            demoPanel("Icon Badges") {
                badge(4, color = BadgeColor.primary) {
                    icon("mail", IconColor.action)
                }
                badge(10, color = BadgeColor.secondary) {
                    css(ComponentStyles.margin)
                    icon("mail")
                }
            }

            demoPanel("App Bar") {
                appBar(position = AppBarPosition.static) {
                    tabs(0) {
                        attrs.textColor = TabTextColor.inherit
                        attrs.indicatorColor = TabIndicatorColor.secondary
                        tab {
                            attrs.label = buildElement {
                                badge(4, color = BadgeColor.secondary) {
                                    css(ComponentStyles.padding)
                                    +"Item 1"
                                }
                            }
                        }
                        tab("Item 2")
                        tab("Item 3")
                    }
                }
            }

            demoPanel("Component Badges") {
                badge(100, color = BadgeColor.primary) {
                    css(ComponentStyles.margin)
                    typography("Typography") { css(ComponentStyles.padding) }
                }
                badge("Hello", color = BadgeColor.secondary) {
                    css(ComponentStyles.margin)
                    button("Button", variant = ButtonVariant.contained)
                }
                badgeDot(BadgeColor.secondary) {
                    css(ComponentStyles.margin)
                    typography("Dot badge")
                }
            }

            demoPanel("Overlap Prop") {
                themeContext.Consumer { theme ->
                    badgeDot(BadgeColor.secondary) {
                        css(ComponentStyles.margin)
                        styledDiv {
                            css {
                                backgroundColor = Color(theme.palette.primary.main)
                                width = 40.px
                                height = 40.px
                            }
                        }
                    }
                    badgeDot(BadgeColor.secondary) {
                        css(ComponentStyles.margin)
                        attrs.overlap = BadgeOverlap.circular
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
            }

            demoPanel("Badge Alignment") {
                gridContainer {
                    css {
                        +ComponentStyles.margin
                        marginBottom = 2.spacingUnits
                    }
                    gridItem {
                        formControl {
                            formLabel("Vertical")
                            radioGroup(value = anchorOriginVertical.toString()) {
                                attrs.onChange = { _, value -> setState { anchorOriginVertical = BadgeAnchorOriginVertical.valueOf(value) } }
                                radioWithLabel("Top", value = BadgeAnchorOriginVertical.top.toString())
                                radioWithLabel("Bottom", value = BadgeAnchorOriginVertical.bottom.toString())
                            }
                        }
                    }
                    gridItem {
                        formControl {
                            formLabel("Horizontal")
                            radioGroup(value = anchorOriginHorizontal.toString()) {
                                attrs.onChange = { _, value -> setState { anchorOriginHorizontal = BadgeAnchorOriginHorizontal.valueOf(value) }}
                                radioWithLabel("Left", value = BadgeAnchorOriginHorizontal.left.toString())
                                radioWithLabel("Right", value = BadgeAnchorOriginHorizontal.right.toString())
                            }
                        }
                    }
                }
                badgeDot(BadgeColor.primary) {
                    css(ComponentStyles.margin)
                    attrs.anchorOriginHorizontal = anchorOriginHorizontal
                    attrs.anchorOriginVertical = anchorOriginVertical
                    icon("mail", color = IconColor.action)
                }
                badge(4, color = BadgeColor.primary) {
                    css(ComponentStyles.margin)
                    attrs.anchorOriginHorizontal = anchorOriginHorizontal
                    attrs.anchorOriginVertical = anchorOriginVertical
                    icon("mail", color = IconColor.action)
                }
                badge(14, color = BadgeColor.primary) {
                    css(ComponentStyles.margin)
                    attrs.anchorOriginHorizontal = anchorOriginHorizontal
                    attrs.anchorOriginVertical = anchorOriginVertical
                    icon("mail", color = IconColor.action)
                }
            }
        }
    }
}
