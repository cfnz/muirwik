package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestTabs : RComponent<RProps, RState>() {
    private var tab1Value: Any = "one"
    private var tab2Value: Any = 0
    private var tab3Value: Any = 1
    private var tab4Value: Any = 1

    private object CustomTabStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val tabsRoot by css {
            borderBottom = "1px solid #e8e8e8"
        }
        val tabsIndicator by css {
            backgroundColor = Color("#1890ff")
        }
        val typography by css {
            padding(3.spacingUnits)
        }
    }

    private fun RBuilder.tabContainer(text: String) {
        mTypography(text) {
            css { padding(3.spacingUnits) }
        }
    }

    override fun RBuilder.render() {
        themeContext.Consumer { theme ->
            val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
                val tabRoot by css {
                    textTransform = TextTransform.none
                    fontWeight = FontWeight(theme.typography.fontWeightRegular.toString())
                    marginRight = 4.spacingUnits
                    hover {
                        color = Color("#40a9ff")
                        opacity = 1
                    }
                    focus {
                        color = Color("#40a9ff")
                    }
                }
                val tabSelected by css {
                    color = Color("#1890ff")
                    fontWeight = FontWeight(theme.typography.fontWeightMedium.toString())
                }
            }

            fun RBuilder.customTab(label: String, value: Int) {
                mTab(label, value) {
                    css {
                        +themeStyles.tabRoot
                        if (tab3Value == value) {
                            +themeStyles.tabSelected
                        }
                    }
                    attrs.asDynamic().disableRipple = true
                }
            }

            styledDiv {
                css { flexGrow = 1.0; backgroundColor = Color(theme.palette.background.paper) }
                mAppBar(position = MAppBarPosition.static) {
                    mTabs(tab1Value, onChange = { _, value -> setState { tab1Value = value } }) {
                        mTab("Item One with a really long name for a tab", "one")
                        mTab("Item Two", "two")
                        mTab("Item Three", "three")
                    }
                }
                when (tab1Value) {
                    "one" -> tabContainer("Item One")
                    "two" -> tabContainer("Item Two")
                    "three" -> tabContainer("Item Three")
                }
            }
            styledDiv {
                css { marginTop = 3.spacingUnits; flexGrow = 1.0; backgroundColor = Color(theme.palette.background.paper) }
                mAppBar(position = MAppBarPosition.static, color = MAppBarColor.default) {
                    mTabs(tab2Value, variant = MTabVariant.scrollable, textColor = MTabTextColor.primary, indicatorColor = MTabIndicatorColor.primary,
                            onChange = { _, value -> setState { tab2Value = value }}) {
                        mTab("Item One", 0, icon = mIcon("phone", addAsChild = false))
                        mTab("Item Two", 1, icon = mIcon("favorite", addAsChild = false))
                        mTab("Item Three", 2, icon = mIcon("person_pin", addAsChild = false))
                        mTab("Item Four", 3, icon = mIcon("help", addAsChild = false))
                        mTab("Item Five", 4, icon = mIcon("shopping_basket", addAsChild = false))
                        mTab("Item Six", 5, icon = mIcon("thumb_down", addAsChild = false))
                        mTab("Item Seven", 6, icon = mIcon("thumb_up", addAsChild = false))
                    }
                }
                when (tab2Value) {
                    0 -> tabContainer("Item One")
                    1 -> tabContainer("Item Two")
                    2 -> tabContainer("Item Three")
                    3 -> tabContainer("Item Four")
                    4 -> tabContainer("Item Five")
                    5 -> tabContainer("Item Six")
                    6 -> tabContainer("Item Seven")
                }
            }
            styledDiv {
                css { marginTop = 3.spacingUnits; flexGrow = 1.0; backgroundColor = Color(theme.palette.background.paper) }
                mTabs(tab3Value, textColor = MTabTextColor.primary, onChange = { _, value -> setState { tab3Value = value }}) {
                    css { +CustomTabStyles.tabsRoot }
                    // TODO: Not sure how to set the style of the indicator... leave it for now...
//                attrs.className = CustomTabStyles.name + "-" + "tabsIndicator"
                    customTab("Tab 1", 1)
                    customTab("Tab 2", 2)
                    customTab("Tab 3", 3)
                }
                mTypography("Ant Design") {
                    css(CustomTabStyles.typography)
                }
            }
            styledDiv {
                css {
                    marginTop = 3.spacingUnits
                    height = 224.px
                    display = Display.flex
                    flexGrow = 1.0
                    backgroundColor = Color(theme.palette.background.paper)
                }
                mTabs(tab4Value, orientation = MTabOrientation.vertical, variant = MTabVariant.scrollable, onChange = { _, value -> setState { tab4Value = value } }) {
                    css { borderRight = "1px solid ${theme.palette.divider}" }
                    mTab("Item One", 1)
                    mTab("Item Two", 2)
                    mTab("Item Three", 3)
                    mTab("Item Four", 4)
                    mTab("Item Five", 5)
                    mTab("Item Size", 6)
                }
                when (tab4Value) {
                    1 -> tabContainer("Item One - Vertical Tab")
                    2 -> tabContainer("Item Two")
                    3 -> tabContainer("Item Three")
                    4 -> tabContainer("Item Four")
                    5 -> tabContainer("Item Five")
                    6 -> tabContainer("Item Six")
                }
            }
        }
    }
}

fun RBuilder.testTabs() = child(TestTabs::class) {}
