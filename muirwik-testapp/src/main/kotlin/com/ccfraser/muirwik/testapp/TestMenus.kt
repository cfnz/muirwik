package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.list.mListItemIcon
import com.ccfraser.muirwik.components.list.mListItemText
import com.ccfraser.muirwik.components.transitions.MTransitionProps
import com.ccfraser.muirwik.components.transitions.SimpleTransitionTimeout
import com.ccfraser.muirwik.components.transitions.mFade
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.padding
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.*
import react.dom.br
import react.dom.div
import react.dom.findDOMNode
import styled.StyleSheet
import styled.css
import styled.styledDiv


class TestMenus : RComponent<RProps, RState>() {
    private var loggedIn = false
    private var anchorElement: Node? = null
    private var selectedOption = "Selection Example..."
    private var selectedItemIndex: Int? = null
    private var refAnchorElement: Node? = null

    fun handleClick(event: Event, itemIndex: Int) {
        selectedItemIndex = itemIndex
        val currentTarget = event.currentTarget
        setState { anchorElement = currentTarget.asDynamic() }
    }

    fun handleMenuItemClick(selectedText: String) {
        setState {
            selectedOption = selectedText
            selectedItemIndex = null
            anchorElement = null
        }
    }

    fun handleClose() {
        setState { anchorElement = null; selectedItemIndex = null }
    }

    val options = arrayOf("Show some love to Material-UI", "Show all notification content", "Hide sensitive notification content", "Hide all notification content")
    val options2 = arrayOf("None", "Atria", "Callisto", "Dione", "Ganymede", "Hangouts Call", "Luna", "Oberon", "Phobos", "Pyxis", "Sedna", "Titania", "Triton", "Umbriel")

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val menuItem by css {
            focus { backgroundColor = Color(currentTheme.palette.primary.main) // ("#2196f3");
                descendants() {
                    color = Color(currentTheme.palette.common.white)
                }
            }
        }
    }

    override fun RBuilder.render() {
        br {}
        br {}
        styledDiv {
            css { display = Display.inlineFlex }

            mButton("Show Menu", onClick = { handleClick(it, 1) })
            div {
                mMenu(selectedItemIndex == 1, anchorElement = anchorElement, onClose = { handleClose() }) {
                    mMenuItem("Profile", onClick = { handleClose() })
                    mMenuItem("My account", onClick = { handleClose() })
                    mMenuItem("Logout", onClick = { handleClose() })
                }
            }

            mButton("Anchor with Ref", onClick = { setState { selectedItemIndex = 2 } }) {
                ref {
                    // Docs say don't get into the habit of finding the rendered DOM node, but also says it is
                    // ok for things like positioning... and that is what we are doing. "it" is a reference
                    // to a react node instance, so we need to use findDOMNode to get the real DOM item so
                    // we can position our menu on top of it. (Note that "it" is also null sometimes.)
                    refAnchorElement = findDOMNode(it)
                }
            }
            div {
                mMenu(selectedItemIndex == 2, anchorElement = refAnchorElement, onClose = { handleClose() }) {
                    mMenuItem("Profile", onClick = { handleClose() })
                    mMenuItem("My account", onClick = { handleClose() })
                    mMenuItem("Logout", onClick = { handleClose() })
                }
            }

            mButton("Max Height Menu", onClick = { handleClick(it, 3) })
            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                val menuListProps: MMenuListProps = jsObject { }
                menuListProps.asDynamic().style = js {
                    maxHeight = 216
                }

                mMenu(selectedItemIndex == 3, anchorElement = anchorElement, onClose = { handleClose() }, menuListProps = menuListProps) {
                    options2.forEach {
                        mMenuItem(primaryText = it, selected = it == "Pyxis", onClick = { handleClose() })
                    }
                }
            }
        }
        styledDiv {
            css { display = Display.inlineFlex }
            mList {
                mListItem(primaryText = "When device is locked", secondaryText = selectedOption, onClick = { handleClick(it, 4) })
            }
            mMenu(selectedItemIndex == 4, anchorElement = anchorElement, onClose = { handleClose() }) {
                options.forEach { item ->
                    mMenuItem(item, selected = item == selectedOption, onClick = { handleMenuItemClick(item) })
                }
            }

            // For some reason this is a bit different to providing the snackbar a transition... this works for menu, not for snackbar
            class FadeTransition(props: MTransitionProps) : RComponent<MTransitionProps, RState>(props) {
                override fun RBuilder.render() {
                    childList.add(cloneElement(mFade(addAsChild = false), props))
                }
            }

            mButton("With Slow Transition", onClick = { handleClick(it, 5) })
            div {
                mMenu(selectedItemIndex == 5, anchorElement = anchorElement, onClose = { handleClose() },
                        transitionDuration = SimpleTransitionTimeout(1000)) {
                    mMenuItem("Profile", onClick = { handleClose() })
                    mMenuItem("My account", onClick = { handleClose() })
                    mMenuItem("Logout", onClick = { handleClose() })
                }
            }

            mButton("With Fade Transition", onClick = { handleClick(it, 6) })
            div {
                mMenu(selectedItemIndex == 6, anchorElement = anchorElement, onClose = { handleClose() },
                        transitionComponent = FadeTransition::class,
                        transitionDuration = SimpleTransitionTimeout(1000)) {
                    mMenuItem("Profile", onClick = { handleClose() })
                    mMenuItem("My account", onClick = { handleClose() })
                    mMenuItem("Logout", onClick = { handleClose() })
                }
            }

            mButton("Secondary Text?", onClick = { handleClick(it, 7) })
            div {
                mMenu(selectedItemIndex == 7, anchorElement = anchorElement, onClose = { handleClose() }) {
                    mMenuItem("Profile", secondaryText = "Perhaps some explanation", onClick = { handleClose() })
                    mMenuItem("My account", secondaryText = "Don't know if this is needed", onClick = { handleClose() })
                    mMenuItem("Logout", onClick = { handleClose() })
                }
            }
        }
        br {}
        br {}
        styledDiv {
            css { display = Display.inlineFlex }

            styledDiv {
                css {
                    +ComponentStyles.menuItem
                }
                mTypography("Composition Example")
                mPaper {
//                    mMenuList {
                        mMenuItem() {
                            css(ComponentStyles.menuItem)
                            mListItemIcon("send")
                            mListItemText("Sent mail", inset = true)
                        }
                        mMenuItem() {
                            css(ComponentStyles.menuItem)
                            mListItemIcon("drafts")
                            mListItemText("Drafts", inset = true)
                        }
                        mMenuItem() {
                            css(ComponentStyles.menuItem)
                            mListItemIcon("inbox")
                            mListItemText("Inbox", inset = true)
                        }
//                    }
                }
            }
        }
    }
}

fun RBuilder.testMenus() = child(TestMenus::class) {}

