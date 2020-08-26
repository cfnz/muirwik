package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.list.mListItemText
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.down
import com.ccfraser.muirwik.components.styles.up
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv

interface MainFrameProps : RProps {
    var onThemeSwitch: () -> Unit
    var initialView: String
}

interface MainFrameState: RState {
    var view: String
    var responsiveDrawerOpen: Boolean
}

class MainFrame(props: MainFrameProps) : RComponent<MainFrameProps, MainFrameState>(props) {
    override fun MainFrameState.init(props: MainFrameProps) {
        view = props.initialView
        responsiveDrawerOpen = false
    }

    private val nameToTestMap = hashMapOf(
            "Intro" to RBuilder::intro,
            "Accordion" to RBuilder::testAccordion,
            "App Bar" to RBuilder::testAppBar,
            "Avatars" to RBuilder::testAvatars,
            "Badges" to RBuilder::testBadges,
            "Backdrop" to RBuilder::testBackdrop,
            "Bottom Nav" to RBuilder::testBottomNavigation,
            "Breadcrumbs" to RBuilder::testBreadcrumbs,
            "Buttons" to RBuilder::testButtons,
            "Cards" to RBuilder::testCards,
            "Checkboxes" to RBuilder::testCheckboxes,
            "Chips" to RBuilder::testChips,
            "ClickAwayListener" to RBuilder::testClickAwayListener,
            "Dialogs" to RBuilder::testDialogs,
            "Drawers" to RBuilder::testDrawers,
            //                            "Gridsto RBuilder::> testGrids,
            "Grid Lists" to RBuilder::testGridLists,
            "Links" to RBuilder::testLinks,
            "Lists" to RBuilder::testLists,
            "Menus" to RBuilder::testMenus,
            "Popover" to RBuilder::testPopover,
            "Progress" to RBuilder::testProgress,
            "Radio Buttons" to RBuilder::testRadioButtons,
            "Selects" to RBuilder::testSelects,
            "Sliders" to RBuilder::testSliders,
            "Snackbars" to RBuilder::testSnackbar,
            "Styles" to RBuilder::testStyles,
            "Switches" to RBuilder::testSwitches,
            "Tables" to RBuilder::testTables,
            "Tabs" to RBuilder::testTabs,
            "Text Fields" to RBuilder::testTextFields,
            "Themes" to RBuilder::testThemes,
            "Tooltips" to RBuilder::testTooltips,
            "Transitions" to RBuilder::testTransitions
    )

    override fun RBuilder.render() {
        mCssBaseline()

        val drawerWidth = 180.px

        themeContext.Consumer {theme ->
            styledDiv {
                css {
                    flexGrow = 1.0
                    width = 100.pct
                    zIndex = 1
                    overflow = Overflow.hidden
                    position = Position.relative
                    display = Display.flex
                }

                styledDiv {
                    // App Frame
                    css { overflow = Overflow.hidden; position = Position.relative; display = Display.flex; width = 100.pct}

                    mAppBar(position = MAppBarPosition.absolute) {
                        css {
                            zIndex = theme.zIndex.drawer + 1
                        }
                        mToolbar {
                            mHidden(mdUp = true, implementation = MHiddenImplementation.css) {
                                mIconButton("menu", color = MColor.inherit, onClick = { setState { responsiveDrawerOpen = true }})
                            }
                            mToolbarTitle("Muirwik - Material-UI React Wrapper in Kotlin - Demo (or play) Area - ${ state.view }")
                            mIconButton("lightbulb_outline", onClick = {
                                props.onThemeSwitch()
                            })
                        }
                    }

                    val p: MPaperProps = jsObject { }
                    p.asDynamic().style = js { position = "relative"; width = drawerWidth.value; display = "block"; height = "100%"; minHeight = "100vh" }
                    mHidden(mdUp = true) {
                        mDrawer(state.responsiveDrawerOpen, MDrawerAnchor.left, MDrawerVariant.temporary, paperProps = p,
                                onClose = { setState { responsiveDrawerOpen = !responsiveDrawerOpen }}) {
                            spacer()
                            demoItems()
                        }
                    }
                    mHidden(smDown = true, implementation = MHiddenImplementation.css) {
                        mDrawer(true, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = p) {
                            spacer()
                            demoItems()
                        }
                    }

                    // Main content area
                    styledDiv {
                        css {
                            height = 100.pct
                            flexGrow = 1.0; minWidth = 0.px
                            backgroundColor = Color(theme.palette.background.default)
                        }
                        spacer()
                        styledDiv {
                            css {
                                media(theme.breakpoints.down(Breakpoint.sm)) {
                                    height = 100.vh - 57.px
                                }
                                media(theme.breakpoints.up(Breakpoint.sm)) {
                                    height = 100.vh - 65.px
                                }

                                overflowY = Overflow.auto
                                padding(2.spacingUnits)
                                backgroundColor = Color(theme.palette.background.default)
                            }
                            nameToTestMap[state.view]?.invoke(this)
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.demoItems() {
        fun RBuilder.addListItem(caption: String): Unit {
//            mListItem(caption, onClick = {setState {currentView = caption}})
            // We want to get rid of the extra right padding, so must use the longer version as below
            mListItem(true, onClick = { setState { view = caption; responsiveDrawerOpen = false }}) {
                mListItemText(caption) {
                    css {
                        paddingRight = 0.px
                        if (caption == state.view) {
                            descendants {
                                color = Colors.Blue.shade500
                            }
                        }
                    }
                }
            }
        }

        themeContext.Consumer { theme ->
            mList {
                css {
                    media(theme.breakpoints.down(Breakpoint.sm)) {
                        height = 100.vh - 57.px
                    }
                    media(theme.breakpoints.up(Breakpoint.sm)) {
                        height = 100.vh - 65.px
                    }
                    overflowY = Overflow.auto
                    overflowX = Overflow.hidden
                    wordBreak = WordBreak.keepAll
                }

                nameToTestMap.keys.sortedWith { a, b -> if (a == "Intro") -1 else if (b == "Intro") 1 else a.compareTo(b) }
                        .forEach { addListItem(it) }
            }
        }
    }
}

fun RBuilder.spacer() {
    themeContext.Consumer { theme ->
        val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
            val toolbar by css {
                toolbarJsCssToPartialCss(theme.mixins.toolbar)
            }
        }

        // This puts in a spacer to get below the AppBar.
        styledDiv {
            css(themeStyles.toolbar)
        }
        mDivider {  }
    }
}


fun RBuilder.mainFrame(initialView: String, onThemeSwitch: () -> Unit) = child(MainFrame::class) {
    attrs.onThemeSwitch = onThemeSwitch
    attrs.initialView = initialView
}
