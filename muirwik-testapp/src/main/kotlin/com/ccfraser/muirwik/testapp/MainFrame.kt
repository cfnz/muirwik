package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
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
    var onThemeTypeChange: (themeType: String) -> Unit
}

interface MyState: RState {
    var showView: String
}

class MainFrame() : RComponent<MainFrameProps, MyState>() {
    private var themeColor = "light"
    private var responsiveDrawerOpen = false

    private val nameTestMap = hashMapOf(
//            "Intro" to RBuilder::appIntro,
        "An Intro" to RBuilder::intro,
        "App Bar" to RBuilder::testAppBar,
        "Avatars" to RBuilder::testAvatars,
        "Badges" to RBuilder::testBadges,
        "Buttons" to RBuilder::testButtons,
        "Cards" to RBuilder::testCards,
        "Chips" to RBuilder::testChips,
        "Dialogs" to RBuilder::testDialogs,
        "Drawers" to RBuilder::testDrawers,
        //                            "Gridsto RBuilder::> testGrids,
        "Grid Lists" to RBuilder::testGridLists,
        "Links" to RBuilder::testLinks,
        "Lists" to RBuilder::testLists,
        "Menus" to RBuilder::testMenus,
        "Options" to RBuilder::testOptionControls,
        "Progress" to RBuilder::testProgress,
        "Selects" to RBuilder::testSelects,
        "Snackbars" to RBuilder::testSnackbar,
        "Styles" to RBuilder::testStyles,
        "Tables" to RBuilder::testTables,
        "Tabs" to RBuilder::testTabs,
        "Text Fields" to RBuilder::testTextFields,
        "Themes" to RBuilder::testThemes,
        "Tooltips" to RBuilder::testTooltips,
        "Transitions" to RBuilder::testTransitions
    )

    override fun MyState.init() {
        showView = "An Intro"
    }

    override fun RBuilder.render() {
        mCssBaseline()

        val drawerWidth = 180.px

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
                        zIndex = currentTheme.zIndex.drawer + 1
                    }
                    mToolbar {
                        mHidden(mdUp = true, implementation = MHiddenImplementation.css) {
                            mIconButton("menu", color = MColor.inherit, onClick = { setState { responsiveDrawerOpen = true }})
                        }
                        mToolbarTitle("Muirwik - Material-UI React Wrapper in Kotlin - Demo (or play) Area - ${state.showView}")
                        mIconButton("lightbulb_outline", onClick = {
                            themeColor = if (themeColor == "light") "dark" else "light"
                            props.onThemeTypeChange(themeColor)
                        })
                    }
                }

                val p: MPaperProps = jsObject { }
                p.asDynamic().style = js { position = "relative"; width = drawerWidth.value; display = "block"; height = "100%"; minHeight = "100vh" }
                mHidden(mdUp = true) {
                    mDrawer(responsiveDrawerOpen, MDrawerAnchor.left, MDrawerVariant.temporary, paperProps = p,
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
                        backgroundColor = Color(currentTheme.palette.background.default)
                    }
                    spacer()
                    styledDiv {
                        css {
                            media(currentTheme.breakpoints.down(Breakpoint.sm)) {
                                height = 100.vh - 57.px
                            }
                            media(currentTheme.breakpoints.up(Breakpoint.sm)) {
                                height = 100.vh - 65.px
                            }

                            overflowY = Overflow.auto
                            padding(2.spacingUnits)
                            backgroundColor = Color(currentTheme.palette.background.default)
                        }
                        nameTestMap[state.showView]?.invoke(this)
                    }
                }
            }
        }
    }

    private fun RBuilder.demoItems() {
        fun RBuilder.addListItem(caption: String): Unit {
//            mListItem(caption, onClick = {setState {showView = caption}})
            // We want to get rid of the extra right padding, so must use the longer version as below
            mListItem(true, onClick = { setState { showView = caption; responsiveDrawerOpen = false }}) {
                mListItemText(caption) {
                    css {
                        paddingRight = 0.px
                        if (caption == state.showView) {
                            descendants {
                                color = Colors.Blue.shade500
                            }
                        }
                    }
                }
            }
        }

        mList {
            css {
                media(currentTheme.breakpoints.down(Breakpoint.sm)) {
                    height = 100.vh - 57.px
                }
                media(currentTheme.breakpoints.up(Breakpoint.sm)) {
                    height = 100.vh - 65.px
                }
                overflowY = Overflow.auto
                overflowX = Overflow.hidden
                wordBreak = WordBreak.keepAll
            }
            nameTestMap.keys.sorted().forEach { addListItem(it) }
        }
    }
}

private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
    val toolbar by css {
        toolbarJsCssToPartialCss( currentTheme.mixins.toolbar)
    }
}

fun RBuilder.spacer() {
    // This puts in a spacer to get below the AppBar.
    styledDiv {
        css(ComponentStyles.toolbar)
    }
    mDivider {  }
}


fun RBuilder.mainFrame(onThemeTypeChange: (themeType: String) -> Unit) = child(MainFrame::class) {attrs.onThemeTypeChange = onThemeTypeChange}
