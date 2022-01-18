package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.spreadProps
import kotlinext.js.js
import kotlinext.js.jso
import kotlinx.css.*
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestMenus : RComponent<Props, State>() {
    private var anchorElement: Node? = null
    private var selectedOption = "Selection Example..."
    private var selectedMenuIndex: Int? = null
    private var refAnchorElement = createRef<Node>()

    private fun handleShowMenuClick(event: Event, menuIndex: Int) {
        selectedMenuIndex = menuIndex
        val currentTarget = event.currentTarget
        setState { anchorElement = currentTarget.asDynamic() }
    }

    private fun handleSimpleClick() {
        setState {
            selectedMenuIndex = null
            anchorElement = null
        }
    }

    private fun handleMenuItemClick(selectedText: String) {
        setState {
            selectedOption = selectedText
            selectedMenuIndex = null
            anchorElement = null
        }
    }

    private fun handleOnClose(reason: MenuOnCloseReason) {
        setState { anchorElement = null; selectedMenuIndex = null }
        println("Close reason: $reason")
    }

    private val options = arrayOf("Show some love to Material-UI", "Show all notification content", "Hide sensitive notification content", "Hide all notification content")
    private val options2 = arrayOf("None", "Atria", "Callisto", "Dione", "Ganymede", "Hangouts Call", "Luna", "Oberon", "Phobos", "Pyxis", "Sedna", "Titania", "Triton", "Umbriel")
    private val fadeTransitionComponent = forwardRef { props: PropsWithRef<Any>, ref -> fade { attrs.ref = ref; spreadProps(props) } }

    override fun RBuilder.render() {
        fun RBuilder.addStdSubItems() {
            menuItem("Profile") { attrs.onClick = { handleSimpleClick() } }
            menuItem("My account") { attrs.onClick = { handleSimpleClick() } }
            menuItem("Logout") { attrs.onClick = { handleSimpleClick() } }
        }

        styledDiv {
            css { padding(2.spacingUnits) }

            styledDiv {
                css { display = Display.inlineFlex }

                button("Show Menu") { attrs.onClick = { handleShowMenuClick(it, 1) }}
                div {
                    menu(selectedMenuIndex == 1) {
                        attrs.anchorEl = anchorElement
                        attrs.onClose = { _, reason -> handleOnClose(reason) }
                        addStdSubItems()
                    }
                }

                button("Anchor with Ref") {
                    attrs.onClick = { setState { selectedMenuIndex = 2 } }
                    ref = refAnchorElement
                }
                div {
                    menu(selectedMenuIndex == 2) {
                        attrs.anchorEl = refAnchorElement.current
                        attrs.onClose = { _, reason -> handleOnClose(reason) }
                        addStdSubItems()
                    }
                }

                button("Max Height Menu") { attrs.onClick = { handleShowMenuClick(it, 3) }}
                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    val menuListProps: MenuListProps = jso { }
                    menuListProps.asDynamic().style = js {
                        maxHeight = 216
                    }

                    menu(selectedMenuIndex == 3) {
                        attrs.anchorEl = anchorElement
                        attrs.onClose = { _, reason -> handleOnClose(reason) }
                        attrs.menuListProps = menuListProps

                        options2.forEach {
                            menuItem(it, selected = it == "Pyxis") {
                                attrs.onClick = { handleSimpleClick() }
                            }
                        }
                    }
                }
            }
            styledDiv {
                css { display = Display.inlineFlex }
                list {
                    listItemButton("When device is locked", selectedOption) { attrs.onClick = { handleShowMenuClick(it, 4) } }
                }
                menu(selectedMenuIndex == 4) {
                    attrs.anchorEl = anchorElement
                    attrs.onClose = { _, reason -> handleOnClose(reason) }
                    options.forEach { item ->
                        menuItem(item, selected = item == selectedOption) { attrs.onClick = { handleMenuItemClick(item) }}
                    }
                }
            }
            styledDiv {
                css { display = Display.inlineFlex }

                button("With Slow Transition") { attrs.onClick = { handleShowMenuClick(it, 5) }}
                div {
                    menu(selectedMenuIndex == 5) {
                        attrs.anchorEl = anchorElement
                        attrs.onClose = { _, reason -> handleOnClose(reason) }
                        attrs.transitionDuration = SimpleTransitionDuration(1000)
                        addStdSubItems()
                    }
                }

                button("With Fade Transition") { attrs.onClick = { handleShowMenuClick(it, 6) }}
                div {
                    menu(selectedMenuIndex == 6) {
                        attrs.anchorEl = anchorElement
                        attrs.onClose = { _, reason -> handleOnClose(reason) }
                        attrs.transitionComponent = fadeTransitionComponent
                        addStdSubItems()
                    }
                }
            }
        }

        styledDiv {
            css { display = Display.inlineFlex }

            themeContext.Consumer { theme ->
                val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
                    val menuItem by css {
                        focus { backgroundColor = Color(theme.palette.primary.main) // ("#2196f3");
                            descendants {
                                color = Color(theme.palette.common.white)
                            }
                        }
                    }
                }

                styledDiv {
                    css {
                        +themeStyles.menuItem
                    }
                    typography("Composition Example")
                    paper {
                        //                    menuList {
                        menuItemWithIcon("send", "Sent mail") {
                            css(themeStyles.menuItem)
                        }
                        menuItemWithIcon("drafts", "Drafts") {
                            css(themeStyles.menuItem)
                        }
                        menuItemWithIcon("inbox", "Inbox") {
                            css(themeStyles.menuItem)
                        }
                    }
                }
            }
        }
    }
}

