package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.mPaper
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.menu.*
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.components.themeContext
import com.ccfraser.muirwik.components.transitions.MTransitionProps
import com.ccfraser.muirwik.components.transitions.SimpleTransitionDuration
import com.ccfraser.muirwik.components.transitions.mFade
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.*
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.findDOMNode
import styled.StyleSheet
import styled.css
import styled.styledDiv


// For some reason this is a bit different to providing the snackbar a transition... this works for menu, not for snackbar
@OptIn(ExperimentalJsExport::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class FadeTransition(props: MTransitionProps) : RComponent<MTransitionProps, RState>(props) {
    override fun RBuilder.render() {
        childList.add(cloneElement(mFade(addAsChild = false), props))
    }
}

@OptIn(ExperimentalJsExport::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class TestMenus : RComponent<RProps, RState>() {
    private var anchorElement: Node? = null
    private var selectedOption = "Selection Example..."
    private var selectedMenuIndex: Int? = null
    private var refAnchorElement: Node? = null

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

    override fun RBuilder.render() {
        styledDiv {
            css { padding(2.spacingUnits)}

            styledDiv {
                css { display = Display.inlineFlex }

                mButton("Show Menu", onClick = { handleShowMenuClick(it, 1) })
                div {
                    mMenu(selectedMenuIndex == 1, anchorElement = anchorElement, onClose = { _, reason -> handleOnClose(reason) }) {
                        mMenuItem("Profile", onClick = { handleSimpleClick() })
                        mMenuItem("My account", onClick = { handleSimpleClick() })
                        mMenuItem("Logout", onClick = { handleSimpleClick() })
                    }
                }

                mButton("Anchor with Ref", onClick = { setState { selectedMenuIndex = 2 } }) {
                    ref {
                        // Docs say don't get into the habit of finding the rendered DOM node, but also says it is
                        // ok for things like positioning... and that is what we are doing. "it" is a reference
                        // to a react node instance, so we need to use findDOMNode to get the real DOM item so
                        // we can position our menu on top of it. (Note that "it" is also null sometimes.)
                        refAnchorElement = findDOMNode(it)
                    }
                }
                div {
                    mMenu(selectedMenuIndex == 2, anchorElement = refAnchorElement, onClose = { _, reason -> handleOnClose(reason) }) {
                        mMenuItem("Profile", onClick = { handleSimpleClick() })
                        mMenuItem("My account", onClick = { handleSimpleClick() })
                        mMenuItem("Logout", onClick = { handleSimpleClick() })
                    }
                }

                mButton("Max Height Menu", onClick = { handleShowMenuClick(it, 3) })
                styledDiv {
                    css { flexGrow = 1.0; padding(2.spacingUnits) }

                    val menuListProps: MMenuListProps = jsObject { }
                    menuListProps.asDynamic().style = js {
                        maxHeight = 216
                    }

                    mMenu(selectedMenuIndex == 3, anchorElement = anchorElement, onClose = { _, reason -> handleOnClose(reason) }, menuListProps = menuListProps) {
                        options2.forEach {
                            mMenuItem(primaryText = it, selected = it == "Pyxis", onClick = { handleSimpleClick() })
                        }
                    }
                }
            }
            styledDiv {
                css { display = Display.inlineFlex }
                mList {
                    mListItem(primaryText = "When device is locked", secondaryText = selectedOption, onClick = { handleShowMenuClick(it, 4) })
                }
                mMenu(selectedMenuIndex == 4, anchorElement = anchorElement, onClose = { _, reason -> handleOnClose(reason) }) {
                    options.forEach { item ->
                        mMenuItem(item, selected = item == selectedOption, onClick = { handleMenuItemClick(item) })
                    }
                }
            }
            styledDiv {
                css { display = Display.inlineFlex }

                mButton("With Slow Transition", onClick = { handleShowMenuClick(it, 5) })
                div {
                    mMenu(selectedMenuIndex == 5, anchorElement = anchorElement, onClose = { _, reason -> handleOnClose(reason) },
                            transitionDuration = SimpleTransitionDuration(1000)) {
                        mMenuItem("Profile", onClick = { handleSimpleClick() })
                        mMenuItem("My account", onClick = { handleSimpleClick() })
                        mMenuItem("Logout", onClick = { handleSimpleClick() })
                    }
                }

                mButton("With Fade Transition", onClick = { handleShowMenuClick(it, 6) })
                div {
                    mMenu(selectedMenuIndex == 6, anchorElement = anchorElement, onClose = { _, reason -> handleOnClose(reason) },
                            transitionComponent = FadeTransition::class,
                            transitionDuration = SimpleTransitionDuration(1000)) {
                        mMenuItem("Profile", onClick = { handleSimpleClick() })
                        mMenuItem("My account", onClick = { handleSimpleClick() })
                        mMenuItem("Logout", onClick = { handleSimpleClick() })
                    }
                }

                mButton("Secondary Text?", onClick = { handleShowMenuClick(it, 7) })
                div {
                    mMenu(selectedMenuIndex == 7, anchorElement = anchorElement, onClose = { _, reason -> handleOnClose(reason) }) {
                        mMenuItem("Profile", secondaryText = "Perhaps some explanation", onClick = { handleSimpleClick() })
                        mMenuItem("My account", secondaryText = "Don't know if this is needed", onClick = { handleSimpleClick() })
                        mMenuItem("Logout", onClick = { handleSimpleClick() })
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
                    mTypography("Composition Example")
                    mPaper {
                        //                    mMenuList {
                        mMenuItemWithIcon("send", "Sent mail") {
                            css(themeStyles.menuItem)
                        }
                        mMenuItemWithIcon("drafts", "Drafts") {
                            css(themeStyles.menuItem)
                        }
                        mMenuItemWithIcon("inbox", "Inbox") {
                            css(themeStyles.menuItem)
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.testMenus() = child(TestMenus::class) {}

