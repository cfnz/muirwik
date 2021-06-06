package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.list.*
import com.ccfraser.muirwik.components.transitions.mCollapse
import com.ccfraser.muirwik.testapp.TestLists.ComponentStyles.inline
import com.ccfraser.muirwik.testapp.TestLists.ComponentStyles.listDiv
import kotlinx.css.*
import react.*
import react.dom.div
import react.dom.span
import styled.StyleSheet
import styled.css
import styled.styledDiv

@OptIn(ExperimentalJsExport::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class TestLists : RComponent<RProps, RState>() {
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
                    mListItemWithIcon("drafts", "Drafts", divider = false)
                    mListItemWithIcon("inbox", "Inbox")
                    mListItem("Trash (<a> with an href)", hRefOptions = HRefOptions("https://github.com/cfnz/muirwik"), divider = false)
                    mListItem("Spam", divider = false)
                }
            }

            styledDiv {
                css(listDiv)
                // This is the same list as above but using the more Material-UI code layout... it is longer, and for the rest of the lists we will se the shorter version.
                val h = builder2.div {
                    mListSubheader { +"Sub Header (more code)" }
                }
                mList(subheader = h, component = "nav") {
                    //}, style = js { width = 320; backgroundColor = "white" }) {
                    css(themeStyles.list)
                    // Using the "full versions" of the components
                    mListItem(button = true) {
                        mListItemIcon {
                            mIcon("drafts")
                        }
                        mListItemText(primary = builder2.span { +"Drafts" })
                    }
                    mListItem(button = true) {
                        mListItemIcon("inbox")
                        mListItemText(primary = builder2.span { +"Inbox" })
                    }
                    mDivider()
                    mListItem(button = true, hRefOptions = HRefOptions("https://github.com/cfnz/muirwik"), component = "a") {
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
                    mListSubheader("Compact Headers", compact = true, disableSticky = true)
                    mListItemWithIcon("alarm", "Item 1", useAvatar = true)
                    mListItemWithIcon("alarm", "Item 2", useAvatar = true)

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
                    mListItemWithIcon("send", "Sent mail")
                    mListItemWithIcon("inbox", "Inbox", onClick = { setState { expanded = !expanded } }) {
                        if (expanded) mIcon("expand_less") else mIcon("expand_more")
                    }
                    mCollapse(show = expanded) {
                        mList(disablePadding = true) {
                            mListItem(button = true) {
                                css { paddingLeft = 8.spacingUnits }
                                mIcon("star")
                                mListItemText(builder2.span { +"Starred (v1)" }, inset = true)
                            }
                            mListItemWithIcon("star", "Starred (v2)") { css { paddingLeft = 8.spacingUnits } }
                        }
                    }
                    mListSubheader { +"Other Type of Items" }
                    mListItemWithIcon("add_shopping_cart", "With Primary Text", "And secondary text")
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
                        mListItemAvatar {
                            mAvatar { mIcon("image") }
                        }
                        mListItemText("Photos")
                    }
                    mDivider()
                    // Simpler version
                    mListItemWithIcon("work", "Business", "Using simple version", useAvatar = true)
                    mListItemWithIcon("beach_access", "Vacation", "Data, Line 2", useAvatar = true)
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
                            mListItemText("Simple avatar list item ${i + 1}")
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
                            mListItemText("List item avatar item ${i + 1}")
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
                    mListItemWithAvatar("/images/cards/contemplative-reptile.jpg", "Brunch this weekend?",
                            "Ali Connors  — I'll be in your neighborhood doing errands this…",
                            alignItems = MListItemAlignItems.flexStart)

                    mListItem( alignItems = MListItemAlignItems.flexStart, button = true) {
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

