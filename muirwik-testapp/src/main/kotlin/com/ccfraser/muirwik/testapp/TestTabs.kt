package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestTabs : RComponent<Props, State>() {
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
        typography(text) {
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
                tab(label, value = value) {
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
                appBar(position = AppBarPosition.static) {
                    tabs(tab1Value) {
                        attrs.textColor = TabTextColor.inherit
                        attrs.indicatorColor = TabIndicatorColor.secondary
                        attrs.onChange = { _, value -> setState { tab1Value = value } }
                        tab("Item One with a really long name for a tab", "one") {
                            attrs.wrapped = true
                        }
                        tab("Item Two", "two")
                        tab("Item Three", "three")
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
                appBar(AppBarPosition.static) {
                    attrs.color = AppBarColor.default
                    tabs(tab2Value) {
                        attrs.variant = TabVariant.scrollable
                        attrs.textColor = TabTextColor.primary
                        attrs.indicatorColor = TabIndicatorColor.primary
                        attrs.onChange = { _, value -> setState { tab2Value = value }}
                        tab("Item One", 0, buildElement { icon("phone") })
                        tab("Item Two", 1, buildElement { icon("favorite") })
                        tab("Item Three", 2, buildElement { icon("person_pin") })
                        tab("Item Four", 3, buildElement { icon("help") })
                        tab("Item Five", 4, buildElement { icon("shopping_basket") })
                        tab("Item Six", 5, buildElement { icon("thumb_down") })
                        tab("Item Seven", 6, buildElement { icon("thumb_up") })
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
                tabs(tab3Value) {
                    attrs.textColor = TabTextColor.primary
                    attrs.onChange = { _, value -> setState { tab3Value = value }}
                    css { +CustomTabStyles.tabsRoot }
                    // TODO: Not sure how to set the style of the indicator... leave it for now...
//                attrs.className = CustomTabStyles.name + "-" + "tabsIndicator"
                    customTab("Tab 1", 1)
                    customTab("Tab 2", 2)
                    customTab("Tab 3", 3)
                }
                typography("Ant Design") {
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
                tabs(tab4Value) {
                    attrs.orientation = TabOrientation.vertical
                    attrs.variant = TabVariant.scrollable
                    attrs.onChange = { _, value -> setState { tab4Value = value } }
                    css { borderRight = "1px solid ${theme.palette.divider}" }
                    tab("Item One", 1)
                    tab("Item Two", 2)
                    tab("Item Three", 3)
                    tab("Item Four", 4)
                    tab("Item Five", 5)
                    tab("Item Size", 6)
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
