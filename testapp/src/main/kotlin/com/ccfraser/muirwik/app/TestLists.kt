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
    var expanded: Boolean = false
    var checked = Array(3, { false })


    override fun RBuilder.render() {
        // For building things that we don't want to render now (e.g. the component will render it later), we need another builder
        val builder2 = RBuilder()
        styledDiv {
            css { display = Display.flex }
            styledDiv {
                css { display = Display.inlineFlex; padding(2.spacingUnits) }
                //            val h = mListSubheader(addAsChild = false) { +"Test Sub Header" }
                val h = builder2.div {
                    mListSubheader() { +"Test Sub Header" }
                }

                mList(subheader = h, component = "nav") { //}, style = js { width = 320; backgroundColor = "white" }) {
                    css {
                        width = 320.px
                        backgroundColor = Color(currentTheme.palette.background.paper)
                    }
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
                    mListItem("Using shorter mListItem", null, "star")
                    mListItem("My Simple Two", null, "alarm")
                    mListItem("My Simple Three", "Some Data", "add_shopping_cart")

                    mListItem(button = true) {
                        mAvatar { mIcon("image") }
                        mListItemText(builder2.div { +"Photos" })
                    }
                    mListItem("My Simple One", "Secondary Data", iconName = "work", compact = false, useAvatar = true)
                    mListItem("Vacation", "Data, Line 2", iconName = "beach_access", compact = true, useAvatar = true)
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
                                mListItemText(builder2.span { +"Starred" }, inset = true)
                            }
                            mListItem("Starred", iconName = "star") { css { paddingLeft = 8.spacingUnits }}
                        }
                    }
                }
            }
            styledDiv {
                css { display = Display.inlineFlex; padding(2.spacingUnits) }
                mList { //(style = js { width = 320; backgroundColor = "white" }) {
                    css {
                        width = 320.px
                        backgroundColor = Color(currentTheme.palette.background.paper)
                    }

                    (0..2).forEach {i ->
                        mListItem(dense = true, button = true, onClick = { setState {checked[i] = !checked[i]} }) {
                            mCheckbox(checked = checked[i], disableRipple = true) { }
                            mListItemText(primary = "Dense Line Item ${i + 1}")
                            mListItemSecondaryAction {
                                mIconButton("comment", onClick = {})
                            }
                        }
                    }
                    mDivider()
                    (0..2).forEach {i ->
                        mListItem(button = true) {
                            mAvatar(src = "/static/images/cards/paella.jpg")
                            mListItemText(primary = "Avatar Item ${i + 1}")
                            mListItemSecondaryAction {
                                mCheckbox(checked[i], onChange = {_, _ -> setState {checked[i] = !checked[i]} })
                            }
                        }
                    }
                    mDivider()
                    (0..2).forEach {i ->
                        mListItem(button = true) {
                            mListItemAvatar {
                                mAvatar(src = "/static/images/cards/paella.jpg")
                            }
                            mListItemText(primary = "List item avatar item ${i + 1}")
                            mListItemSecondaryAction {
                                mCheckbox(checked[i], onChange = {_, _ -> setState {checked[i] = !checked[i]} })
                            }
                        }
                    }
                    mDivider()
                    (0..2).forEach {i ->
                        mListItem(button = true) {
                            mListItemAvatar(src = "/static/images/cards/paella.jpg")
                            mListItemText(primary = "Simple avatar list item ${i + 1}")
                            mListItemSecondaryAction {
                                mCheckbox(checked[i], onChange = {_, _ -> setState {checked[i] = !checked[i]} })
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.testLists() = child(TestLists::class) {}

