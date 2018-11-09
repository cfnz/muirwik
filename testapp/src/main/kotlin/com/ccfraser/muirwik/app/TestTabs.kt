package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.wrapper.*
import kotlinx.css.Color
import kotlinx.css.FontWeight
import kotlinx.css.TextTransform
import kotlinx.css.padding
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestTabs : RComponent<RProps, RState>() {
    var tab1Value: Any = "one"
    var tab2Value: Any = 0
    var tab3Value: Any = 1

    private object CustomTabStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val tabsRoot by css {
            borderBottom = "1px solid #e8e8e8"
        }
        val tabsIndicator by css {
            backgroundColor = Color("#1890ff")
        }
        val tabRoot by css {
            textTransform = TextTransform.none
            fontWeight = FontWeight(currentTheme.typography.fontWeightRegular.toString())
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
            fontWeight = FontWeight(currentTheme.typography.fontWeightMedium.toString())
        }
        val typography by css {
            padding(3.spacingUnits)
        }
    }

    fun RBuilder.tabContainer(text: String) {
        mTypography(text) {
            css {padding(3.spacingUnits)}
        }
    }

    private fun RBuilder.customTab(label: String, value: Int) {
        mTab(label, value) {
            css {
                +CustomTabStyles.tabRoot
                if (tab3Value == value) {
                    +CustomTabStyles.tabSelected
                }
            }
            attrs.asDynamic().disableRipple = true
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css { flexGrow = 1.0; backgroundColor = Color(currentTheme.palette.background.paper) }
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
            css { marginTop = 3.spacingUnits; flexGrow = 1.0; backgroundColor = Color(currentTheme.palette.background.paper) }
            mAppBar(position = MAppBarPosition.static, color = MColor.default) {
                mTabs(tab2Value, scrollable = true, textColor = MTabTextColor.primary, indicatorColor = MTabIndicatorColor.primary,
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
            css { marginTop = 3.spacingUnits; flexGrow = 1.0; backgroundColor = Color(currentTheme.palette.background.paper) }
            mTabs(tab3Value, textColor = MTabTextColor.primary,
                    onChange = { _, value -> setState { tab3Value = value }}) {
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
    }
}

fun RBuilder.testTabs() = child(TestTabs::class) {}
