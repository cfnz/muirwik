package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.wrapper.*
import com.ccfraser.muirwik.wrapper.list.*
import com.ccfraser.muirwik.wrapper.transitions.mCollapse
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
import styled.css
import styled.styledDiv

class TestLists : RComponent<RProps, TestOptionControls.MyTestState>() {
    private var expanded: Boolean = false
    private var checked = Array(3) { false }
    private var selected = 0

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
        styledDiv {
            css { display = Display.flex }
            styledDiv {
                css { display = Display.inlineFlex; padding(1.spacingUnits) }
                //            val h = mListSubheader(addAsChild = false) { +"Test Sub Header" }
                val h = builder2.div {
                    mListSubheader { +"Sub Header" }
                }

                mList(subheader = h, component = "nav") {
                    //}, style = js { width = 320; backgroundColor = "white" }) {
                    css {
                        width = 320.px
                        backgroundColor = Color(currentTheme.palette.background.paper)
                    }
                    // Using the "full version"
                    mListItem(button = true) {
                        mListItemIcon() {
                            mIcon("drafts")
                        }
                        mListItemText(primary = builder2.span { +"Drafts (with long mListItem)" })
                    }
                    mListItem(button = true) {
                        mListItemIcon("inbox")
                        mListItemText(primary = builder2.span { +"Inbox" })
                    }
                    mDivider()
                    mListItem(button = true, href = "http://www.ptsonline.com") {
                        mListItemText(primary = builder2.span { +"Trash" })
                    }
                    mListItem(containerComponent = "a", button = false, href = "http://www.ptsonline.com") {
                        mListItemText(primary = builder2.span { +"Spam" })
                    }
                    mDivider()

                    // The sticky headers work better (for visual reasons) if the control itself or its container is scrollable
                    // without padding. Since our parent (not in this component) has padding, this makes a gap at the top
                    // which shows content behind the sticky header (with css you fix it, but I am not sure how to target
                    // the right class... so for now, we disable the sticky headers.
                    mListSubheader("Simpler Code Versions", disableSticky = true)
                    mListItem("Drafts", null, "drafts")
                    mListItem("Inbox", null, "inbox")
                    mDivider()
                    mListSubheader("Compact Items", compact = true, disableSticky = true)
                    mListItem("Item 1", null, "alarm", compact = true)
                    mListItem("Item 2", null, "alarm", compact = true)
                    mListItem("Item 3", null, "alarm", compact = true)
                    mDivider()
                    mListItem("With Primary Text", "And secondary text", "add_shopping_cart")
                    mListSubheader() { +"Nested List Items" }
                    mListItem("Sent mail", iconName = "send", compact = false)
                    mListItem("Inbox", iconName = "inbox", compact = false, onClick = { setState { expanded = !expanded } }) {
                        if (expanded) mIcon("expand_less") else mIcon("expand_more")
                    }
                    mCollapse(show = expanded) {
                        mList(disablePadding = true) {
                            mListItem(button = true) {
                                css { paddingLeft = 8.spacingUnits}
                                mIcon("star")
                                mListItemText(builder2.span { +"Starred (v1)" }, inset = true)
                            }
                            mListItem("Starred (v2)", iconName = "star") { css { paddingLeft = 8.spacingUnits }}
                        }
                    }
                    (0..2).forEach {i ->
                        mListItem(dense = true, button = true) {
                            mListItemText(primary = "Dense Line Item ${i + 1}")
                            mListItemSecondaryAction {
                                mIconButton("comment", onClick = {})
                            }
                        }
                    }
                }
            }
            styledDiv {
                css { display = Display.inlineFlex; padding(1.spacingUnits) }
                mList { //(style = js { width = 320; backgroundColor = "white" }) {
                    css {
                        width = 320.px
                        backgroundColor = Color(currentTheme.palette.background.paper)
                    }

                    mListSubheader("Avatar Icons", disableSticky = true)
                    // "Full" version
                    mListItem(button = true) {
                        mAvatar { mIcon("image") }
                        mListItemText(builder2.div { +"Photos" })
                    }
                    // Simpler version
                    mListItem("Business", "Using simple version", iconName = "work", compact = false, useAvatar = true)
                    mListItem("Vacation", "Data, Line 2", iconName = "beach_access", compact = true, useAvatar = true)

                    mDivider()
                    mListSubheader("Avatar Images", disableSticky = true)
                    (0..2).forEach {i ->
                        mListItem(button = true) {
                            mAvatar(src = "/static/images/cards/${getNameForImageNr(i)}")
                            mListItemText(primary = "Avatar Item ${i + 1}")
                            mListItemSecondaryAction {
                                mCheckbox(checked[i], onChange = {_, _ -> setState {checked[i] = !checked[i]} })
                            }
                        }
                    }
                    mDivider()
                    mListSubheader("Avatars using ListItemAvatar", disableSticky = true)
                    (0..2).forEach {i ->
                        mListItem(button = true) {
                            mListItemAvatar {
                                mAvatar(src = "/static/images/cards/${getNameForImageNr(i)}")
                            }
                            mListItemText(primary = "List item avatar item ${i + 1}")
                            mListItemSecondaryAction {
                                mCheckbox(checked[i], onChange = {_, _ -> setState {checked[i] = !checked[i]} })
                            }
                        }
                    }
                    mDivider()
                    mListSubheader("\"Simple\" ListItemAvatars", disableSticky = true)
                    (0..2).forEach {i ->
                        mListItem(button = true) {
                            mListItemAvatar(src = "/static/images/cards/${getNameForImageNr(i)}")
                            mListItemText(primary = "Simple avatar list item ${i + 1}")
                            mListItemSecondaryAction {
                                mCheckbox(checked[i], onChange = {_, _ -> setState {checked[i] = !checked[i]} })
                            }
                        }
                    }
                }
            }
        }
        styledDiv {
            css { display = Display.inlineFlex; padding(2.spacingUnits) }
            mList {
                css {
                    width = 320.px
                    backgroundColor = Color(currentTheme.palette.background.paper)
                }
                (0..4).forEach {i ->
                    mListItem("Selectable Item $i", selected = (selected == i), key = i.toString(),
                            onClick = { setState { selected = i }})
                }
            }
        }

    }
}

fun RBuilder.testLists() = child(TestLists::class) {}

