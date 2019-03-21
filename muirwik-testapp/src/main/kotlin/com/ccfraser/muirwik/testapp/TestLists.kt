package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.list.*
import com.ccfraser.muirwik.components.transitions.mCollapse
import com.ccfraser.muirwik.testapp.TestLists.ComponentStyles.inline
import com.ccfraser.muirwik.testapp.TestLists.ComponentStyles.listDiv
import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RProps
import react.dom.div
import react.dom.span
import react.setState
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestLists : RComponent<RProps, TestOptionControls.MyTestState>() {
    private var expanded: Boolean = false
    private var checked = Array(3) { false }
    private var selected = 0

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val listDiv by css {
            display = Display.inlineFlex
            padding(1.spacingUnits)
        }

        val inline by css {
            display = Display.inlineBlock
        }
    }

    private fun getNameForImageNr(i: Int): String {
        return when(i) {
            0 -> "contemplative-reptile.jpg"
            1 -> "paella.jpg"
            else -> "live-from-space.jpg"
        }
    }

    override fun RBuilder.render() {
        // For building things that we don't want to render now (e.g. the component will render it later), we need another builder
        val builder2 = RBuilder()

        themeContext.Consumer { theme ->
            val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
                val list by css {
                    width = 320.px
                    backgroundColor = Color(theme.palette.background.paper)
                }
            }
            styledDiv {
                css(listDiv)
                mList("Easy Sub Header") {
                    css(themeStyles.list)
                    mListItem("Drafts", null, "drafts", divider = false)
                    mListItem("Inbox", null, "inbox")
                    mListItem("Trash (<a> with an href)", href = "https://github.com/cfnz/muirwik", target = "_Blank", divider = false)
                    mListItem("Spam", divider = false)
                }
            }

            styledDiv {
                css(listDiv)
                // This is the same list as above but using the more Material-UI code layout... it is longer, and for the rest of the lists we will se the shorter version.
                val h = builder2.div {
                    mListSubheader { +"Sub Header" }
                }
                mList(subheader = h, component = "nav") {
                    //}, style = js { width = 320; backgroundColor = "white" }) {
                    css(themeStyles.list)
                    // Using the "full versions" of the components
                    mListItem(button = true) {
                        mListItemIcon {
                            mIcon("drafts")
                        }
                        mListItemText(primary = builder2.span { +"Drafts (with long mListItem)" })
                    }
                    mListItem(button = true) {
                        mListItemIcon("inbox")
                        mListItemText(primary = builder2.span { +"Inbox" })
                    }
                    mDivider()
                    mListItem(button = true, href = "https://github.com/cfnz/muirwik", component = "a") {
                        attrs.asDynamic().target = "_Blank"
                        mListItemText(primary = builder2.span { +"Trash (<a> with an href)" })
                    }
                    mListItem(button = true) {
                        mListItemText(primary = builder2.span { +"Spam" })
                    }
                }
            }

            styledDiv {
                css(listDiv)
                mList {
                    css(themeStyles.list)
                    // The sticky headers work better (for visual reasons) if the control itself or its container is scrollable
                    // without padding. Since our parent (not in this component) has padding, this makes a gap at the top
                    // which shows content behind the sticky header (with css you fix it, but I am not sure how to target
                    // the right class... so for now, we disable the sticky headers.
                    mListSubheader("Compact Items", compact = true, disableSticky = true)
                    mListItem("Item 1", null, "alarm", compact = true)
                    mListItem("Item 2", null, "alarm", compact = true)

                    // We need to use the full version of mListItem as dense is not in the shorter version
                    mListSubheader("Dense Items", compact = true, disableSticky = true)
                    (0..1).forEach { i ->
                        mListItem(dense = true, button = true) {
                            mListItemText(primary = "Dense Line Item ${i + 1}")
                        }
                    }
                }
            }

            styledDiv {
                css(listDiv)
                mList {
                    css(themeStyles.list)
                    mListSubheader { +"Nested List Items" }
                    mListItem("Sent mail", iconName = "send", compact = false)
                    mListItem("Inbox", iconName = "inbox", compact = false, onClick = { setState { expanded = !expanded } }) {
                        if (expanded) mIcon("expand_less") else mIcon("expand_more")
                    }
                    mCollapse(show = expanded) {
                        mList(disablePadding = true) {
                            mListItem(button = true) {
                                css { paddingLeft = 8.spacingUnits }
                                mIcon("star")
                                mListItemText(builder2.span { +"Starred (v1)" }, inset = true)
                            }
                            mListItem("Starred (v2)", iconName = "star") { css { paddingLeft = 8.spacingUnits } }
                        }
                    }
                    mListSubheader { +"Other Type of Items" }
                    mListItem("With Primary Text", "And secondary text", "add_shopping_cart")
                    mListItem(button = true) {
                        mListItemText(primary = "With a secondary action")
                        mListItemSecondaryAction {
                            mIconButton("comment", onClick = {})
                        }
                    }
                    mListItem(button = true) {
                        mListItemText(primary = "With a secondary action 2")
                        mListItemSecondaryAction {
                            mIconButton("comment", onClick = {})
                        }
                    }
                }
            }

            styledDiv {
                css(listDiv)
                mList {
                    css(themeStyles.list)
                    mListSubheader("Avatar Icons", disableSticky = true)
                    // "Full" version
                    mListItem(button = true) {
                        mAvatar { mIcon("image") }
                        mListItemText("Photos")
                    }
                    mDivider()
                    // Simpler version
                    mListItem("Business", "Using simple version", iconName = "work", compact = false, useAvatar = true)
                    mListItem("Vacation", "Data, Line 2", iconName = "beach_access", compact = true, useAvatar = true)
                    mDivider()
                }
            }

            styledDiv {
                css(listDiv)
                mList {
                    css(themeStyles.list)
                    mListSubheader("Avatar Images", disableSticky = true)
                    (0..2).forEach { i ->
                        mListItem(button = true) {
                            mAvatar(src = "/images/cards/${getNameForImageNr(i)}")
                            mListItemText(primary = "Avatar Item ${i + 1}")
                            mListItemSecondaryAction {
                                mCheckbox(checked[i], onChange = { _, _ -> setState { checked[i] = !checked[i] } })
                            }
                        }
                    }
                }
            }

            styledDiv {
                css(listDiv)
                mList {
                    css(themeStyles.list)
                    mListSubheader("ListItemAvatar (less height)", disableSticky = true)
                    (0..2).forEach {i ->
                        mListItem(button = true) {
                            mListItemAvatar(src = "/images/cards/${getNameForImageNr(i)}")
                            mListItemText(primary = "Simple avatar list item ${i + 1}")
                            mListItemSecondaryAction {
                                mCheckbox(checked[i], onChange = {_, _ -> setState {checked[i] = !checked[i]} })
                            }
                        }
                    }
                }
            }

            styledDiv {
                css(listDiv)
                mList {
                    css(themeStyles.list)
                    mListSubheader("\"Full\" ListItemAvatar (more code)", disableSticky = true)
                    (0..2).forEach { i ->
                        mListItem(button = true) {
                            // This is using the full mListItemAvatar as is seen in Material-UI code rather than the shortened version above.
                            mListItemAvatar {
                                mAvatar(src = "/images/cards/${getNameForImageNr(i)}")
                            }
                            mListItemText(primary = "List item avatar item ${i + 1}")
                            mListItemSecondaryAction {
                                mCheckbox(checked[i], onChange = { _, _ -> setState { checked[i] = !checked[i] } })
                            }
                        }
                    }
                }
            }

            styledDiv {
                css(listDiv)
                mList {
                    css(themeStyles.list)
                    (0..4).forEach {i ->
                        mListItem("Selectable Item $i", selected = (selected == i), key = i.toString(),
                                onClick = { setState { selected = i }})
                    }
                }
            }

            styledDiv {
                css(listDiv)
                mList {
                    css(themeStyles.list)
                    mListItem(alignItems = MListItemAlignItems.flexStart, button = true) {
                        mListItemAvatar(src = "/images/cards/contemplative-reptile.jpg")
                        mListItemText( builder2.span {+"Brunch this weekend?"}, builder2.span {
                            mTypography("Ali Connors", component = "span", variant = MTypographyVariant.body2) { css (inline) }
                            +" — I'll be in your neighborhood doing errands this…"
                        })
                    }
                    mListItem(alignItems = MListItemAlignItems.flexStart, button = true) {
                        mListItemAvatar(src = "/images/cards/live-from-space.jpg")
                        mListItemText( builder2.span {+"Summer BBQ"}, builder2.span {
                            mTypography("Scott, Alex, Jennifer", component = "span", variant = MTypographyVariant.body2) { css (inline) }
                            +" — Note that this is a longer item, but it has alignItems to flexStart so the icon is at the top…"
                        })
                    }
                    mListItem(alignItems = MListItemAlignItems.center, button = true) {
                        mListItemAvatar(src = "/images/cards/paella.jpg")
                        mListItemText( builder2.span {+"Oui Oui"}, builder2.span {
                            mTypography("Sandra Adams", component = "span", variant = MTypographyVariant.body2) { css (inline) }
                            +" — Note that this item has AlignItems at center - note the icon position relative to the list item"
                        })
                    }
                }
            }
        }
    }
}

fun RBuilder.testLists() = child(TestLists::class) {}

