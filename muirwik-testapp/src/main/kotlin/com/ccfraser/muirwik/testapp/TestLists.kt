package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.utils.HRefOptions
import com.ccfraser.muirwik.testapp.TestLists.ComponentStyles.inline
import com.ccfraser.muirwik.testapp.TestLists.ComponentStyles.listDemoPanel
import kotlinx.css.*
import react.*
import react.dom.span
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestLists : RComponent<Props, State>() {
    private var expanded: Boolean = false
    private var checked = Array(3) { false }
    private var selected = 0

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val listDemoPanel by css {
            display = Display.inlineFlex
            padding(1.spacingUnits)
            backgroundColor = Color("#EAEEF3")
            overflowY = Overflow.hidden
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
        themeContext.Consumer { theme ->
            val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
                val list by css {
                    width = 320.px
                    backgroundColor = Color(theme.palette.background.default)
                }
            }
            demoContainer {
                demoPanel("Simple list") {
                    css(listDemoPanel)
                    list {
                        css(themeStyles.list)
                        listItemButtonWithIcon("drafts", "Drafts")
                        listItemButtonWithIcon("inbox", "Inbox") { divider() }
                        listItemButton("Trash")
                        listItemButton("Spam")
                    }
                }
                demoPanel("Simple list with more code") {
                    css(listDemoPanel)
                    // This is the same list as above but using the more Material-UI code layout... it is longer, and for the rest of the lists we will se the shorter version above.
                    list {
                        css(themeStyles.list)
                        // Using the "full versions" of the components
                        listItemButton {
                            listItemIcon {
                                icon("drafts")
                            }
                            listItemText("Drafts")
                        }
                        listItemButton {
                            listItemIcon("inbox")
                            listItemText("Inbox")
                        }
                        divider()
                        listItemButton {
                            listItemText(primary = buildElement { span { +"Trash" } })
                        }
                        listItemButton {
                            listItemText(primary = buildElement { span { +"Spam" } })
                        }
                    }
                }
            }


            demoPanel("Sub Headers") {
                css(listDemoPanel)
                list {
                    css(themeStyles.list)
                    // The sticky headers work better (for visual reasons) if the control itself or its container is scrollable
                    // without padding. Since our parent (not in this component) has padding, this makes a gap at the top
                    // which shows content behind the sticky header (with css you fix it, but I am not sure how to target
                    // the right class... so for now, we disable the sticky headers.
                    listSubheader("Subheaders") { attrs.disableSticky = true }
                    listItemButtonWithIcon("alarm", "Item 1", useAvatar = true)
                    listItemButtonWithIcon("alarm", "Item 2 with an <a>", useAvatar = true) {
                        attrs.hrefOptions = HRefOptions("https://github.com/cfnz/muirwik")
                    }

                    // We need to use the full version of listItem as dense is not in the shorter version
                    listSubheader("Dense Items") { attrs.disableSticky = true }
                    (0..1).forEach { i ->
                        listItemButton {
                            attrs.dense = true
                            listItemText(primary = "Dense Line Item ${i + 1}")
                        }
                    }
                }
            }

            demoPanel("Sticky headers") {
                css(listDemoPanel)
                list {
                    css {
                        +themeStyles.list
                        height = 240.px
                        overflowY = Overflow.scroll
                        padding(0.px)

                    }
                    listSubheader("Email Items")
                    listItemButtonWithIcon("send", "Sent mail")
                    listItemButtonWithIcon("inbox", "Inbox")
                    divider {  }
                    listItemButton("Trash")
                    listItemButton("Spam")
                    divider {  }
                    listSubheader("Cart Items")
                    listItemButtonWithIcon("add_shopping_cart", "With Primary Text", "And secondary text")
                    listItemButtonWithIcon("remove_shopping_cart", "With Primary Text", "And secondary text")
                    divider {  }
                    listSubheader("Misc Items")
                    (0..10).forEach {
                        listItemButton("Item $it")
                    }
                }
            }

            demoPanel("Nested List Items") {
                css(listDemoPanel)
                list {
                    css(themeStyles.list)
                    listItemButtonWithIcon("send", "Sent mail")
                    listItemButtonWithIcon("inbox", "Inbox") {
                        attrs.onClick = { setState { expanded = !expanded } }
                        if (expanded) icon("expand_less") else icon("expand_more")
                    }
                    collapse(show = expanded) {
                        list {
                            attrs.disablePadding = true
                            listItemButtonWithIcon("star", "Starred") { css { paddingLeft = 8.spacingUnits } }
                        }
                    }
                    listSubheader { +"Other Type of Items" }
                    listItemButtonWithIcon("add_shopping_cart", "With Primary Text", "And secondary text")
                    listItemButton {
                        listItemText(primary = "With a secondary action")
                        listItemSecondaryAction {
                            iconButton("comment")
                        }
                    }
                    listItemButton {
                        listItemText("With a secondary action 2")
                        listItemSecondaryAction {
                            iconButton("comment")
                        }
                    }
                }
            }

            demoPanel("Avatar Icons") {
                css(listDemoPanel)
                list {
                    css(themeStyles.list)
                    // "Full" version
                    listItemButton {
                        listItemAvatar {
                            avatar { icon("image") }
                        }
                        listItemText("Photos")
                    }
                    divider()
                    // Simpler version
                    listItemButtonWithIcon("work", "Business", "Using simple version", useAvatar = true)
                    listItemButtonWithIcon("beach_access", "Vacation", "Data, Line 2", useAvatar = true)
                }
            }

            demoPanel("ListItemAvatar (less height)") {
                css(listDemoPanel)
                list {
                    css(themeStyles.list)
                    (0..2).forEach {i ->
                        listItemButton {
                            listItemAvatar(src = "/images/cards/${getNameForImageNr(i)}")
                            listItemText("Simple avatar list item ${i + 1}")
                            listItemSecondaryAction {
                                checkbox(checked[i]) {
                                    attrs.onChange = {_, _ -> setState {checked[i] = !checked[i]} }
                                }
                            }
                        }
                    }
                }
            }

            demoPanel("\"Full\" ListItemAvatar (more code)") {
                css(listDemoPanel)
                list {
                    css(themeStyles.list)
                    (0..2).forEach { i ->
                        listItemButton {
                            // This is using the full listItemAvatar as is seen in Material-UI code rather than the shortened version above.
                            listItemAvatar {
                                avatar(src = "/images/cards/${getNameForImageNr(i)}")
                            }
                            listItemText("List item avatar item ${i + 1}")
                            listItemSecondaryAction {
                                checkbox(checked[i]) {
                                    attrs.onChange = { _, _ -> setState { checked[i] = !checked[i] } }
                                }
                            }
                        }
                    }
                }
            }

            demoPanel("Selectable items") {
                css(listDemoPanel)
                list {
                    css(themeStyles.list)
                    (0..4).forEach {i ->
                        listItemButton("Selectable Item $i") {
                            attrs.selected = (selected == i)
                            attrs.key = i.toString()
                            attrs.onClick = { setState { selected = i }}
                        }
                    }
                }
            }

            demoPanel("Avatar and secondary text") {
                css(listDemoPanel)
                list {
                    css(themeStyles.list)
                    listItemButtonWithAvatar("/images/cards/contemplative-reptile.jpg",
                        "Brunch this weekend?",
                        "Ali Connors  — I'll be in your neighborhood doing errands this…"
                    ) {
                        attrs.alignItems = ListItemAlignItems.flexStart
                    }


                    listItemButton {
                        attrs.alignItems = ListItemAlignItems.flexStart
                        listItemAvatar(src = "/images/cards/contemplative-reptile.jpg")
                        listItemText( buildElement { span {+"Brunch this weekend?"} }, buildElement { span {
                            typography("Ali Connors", variant = TypographyVariant.body2) {
                                attrs.component = "span"
                                css (inline)
                            }
                            +" — I'll be in your neighborhood doing errands this…"
                        } })
                    }
                    listItemButton {
                        attrs.alignItems = ListItemAlignItems.flexStart
                        listItemAvatar(src = "/images/cards/live-from-space.jpg")
                        listItemText( buildElement { span {+"Summer BBQ"} }, buildElement { span {
                            typography("Scott, Alex, Jennifer", TypographyVariant.body2) {
                                attrs.component = "span"
                                css (inline)
                            }
                            +" — Note that this is a longer item, but it has alignItems to flexStart so the icon is at the top…"
                        } })
                    }
                    listItemButton {
                        attrs.alignItems = ListItemAlignItems.center
                        listItemAvatar(src = "/images/cards/paella.jpg")
                        listItemText( buildElement { span {+"Oui Oui"} }, buildElement { span {
                            typography("Sandra Adams", TypographyVariant.body2) {
                                attrs.component = "span"
                                css (inline)
                            }
                            +" — Note that this item has AlignItems at center - note the icon position relative to the list item"
                        } })
                    }
                }
            }
        }
    }
}

fun RBuilder.testLists() = child(TestLists::class) {}

