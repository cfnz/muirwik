package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.down
import com.ccfraser.muirwik.components.styles.up
import com.ccfraser.muirwik.components.utils.Colors
import com.ccfraser.muirwik.components.utils.toolbarJsCssToPartialCss
import com.ccfraser.muirwik.testapp.MainFrame.ComponentStyles.demoListItemText
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv
import kotlin.reflect.KClass

external interface MainFrameProps : Props {
    var onThemeSwitch: () -> Unit
    var initialPage: Page
}

external interface MainFrameState: State {
    var page: Page
    var responsiveDrawerOpen: Boolean
}

class MainFrame(props: MainFrameProps) : RComponent<MainFrameProps, MainFrameState>(props) {
    override fun MainFrameState.init(props: MainFrameProps) {
        page = props.initialPage
        responsiveDrawerOpen = false
    }

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val demoListItemText by css {
            paddingRight = 0.px
            margin(0.px)
        }
    }

    override fun RBuilder.render() {
        mCssBaseline()

        val drawerWidth = 190.px

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

                    appBar(position = AppBarPosition.absolute) {
                        css {
                            zIndex = theme.zIndex.drawer + 1
                        }
                        toolbar {
                            hidden {
                                attrs.mdUp = true
                                attrs.implementation = HiddenImplementation.css
                                iconButton("menu", IconButtonColor.inherit) {
                                    attrs.onClick = { setState { responsiveDrawerOpen = true }}
                                }
                            }
                            toolbarTitle("Muirwik - Material-UI React Wrapper in Kotlin - Demo (or play) Area - ${ state.page.title }")
                            iconButton("lightbulb_outline") {
                                attrs.onClick = {
                                    props.onThemeSwitch()
                                }
                            }
                        }
                    }

                    val p: PaperProps = jsObject { }
                    p.asDynamic().style = js { position = "relative"; width = drawerWidth.value; display = "block"; height = "100%"; minHeight = "100vh" }
                    hidden {
                        attrs.mdUp = true
                        drawer(state.responsiveDrawerOpen, DrawerAnchor.left, DrawerVariant.temporary) {
                            attrs.paperProps = p
                            attrs.onClose = { setState { responsiveDrawerOpen = !responsiveDrawerOpen }}
                            spacer()
                            demoItems()
                        }
                    }
                    hidden {
                        attrs.smDown = true
                        attrs.implementation = HiddenImplementation.css
                        drawer(true, DrawerAnchor.left, DrawerVariant.permanent) {
                            attrs.paperProps = p
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
                            if (state.page.kClass != null) {
                                child(state.page.kClass!!) {}
                            } else if (state.page.fc != null) {
                                child(state.page.fc!!)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.demoItems() {
        fun RBuilder.addListItem(page: Page) {
            // We want to get rid of the extra right padding, so must use the longer version as below
            listItemButton {
                attrs.onClick = { setState { this.page = page; responsiveDrawerOpen = false }}
                listItemText(page.title, className = "SelectedMine2") {
                    css {
                        +demoListItemText
                        if (page == state.page) {
                            descendants {
                                color = Colors.Blue.shade500
                            }
                        }
                    }
                }
            }
        }

        themeContext.Consumer { theme ->
            list {
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

                Page.values().forEach { addListItem(it) }
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
        divider {  }
    }
}

enum class Page(val title: String, val kClass: KClass<out RComponent<Props, out State>>?, val fc: FC<Props>? = null){
    Intro("Intro", TestIntro::class),
    Accordion("Accordion", TestAccordion::class),
    Alert("Alert",  TestAlert::class),
    AppBar("App Bar", TestAppBar::class),
    AutoComplete("Auto Complete", null, testAutoComplete),
    Avatars("Avatars", TestAvatars::class),
    Badges("Badges", TestBadges::class),
    Backdrop("Backdrop", TestBackdrop::class),
    BottomNav("Bottom Nav", TestBottomNavigation::class),
    Breadcrumbs("Breadcrumbs", TestBreadcrumbs::class),
    Buttons("Buttons", TestButtons::class),
    Cards("Cards", TestCards::class),
    Checkboxes("Checkboxes", TestCheckboxes::class),
    Chips("Chips", TestChips::class),
    ClickAwayListener("Click Away Listener", TestClickAwayListener::class),
    Dialogs("Dialogs", TestDialogs::class),
    Drawers("Drawers", TestDrawers::class),
    ErrorBoundary("Error Boundary", TestErrorBoundary::class),
//    Grids("Grids", TestGrids::class),
    GridAndBreakpoints("Grid & Breakpoints", null, testGridsAndBreakpoints),
    ImageLists("Image Lists", TestImageLists::class),
    Links("Links", TestLinks::class),
    Lists("Lists", TestLists::class),
    Localization("Localization", TestLocalization::class),
    Menus("Menus", TestMenus::class),
    Popover("Popover", TestPopover::class),
    Progress("Progress", TestProgress::class),
    RadioButtons("Radio Buttons", TestRadioButtons::class),
    Ratings("Ratings", null, testRatings),
    Selects("Selects", TestSelects::class),
    Skeleton("Skeletons", TestSkeletons::class),
    Sliders("Sliders", TestSlider::class),
    Snackbars("Snackbars", TestSnackbar::class),
    SpeedDials("SpeedDials", TestSpeedDial::class),
    Styles("Styles", TestStyles::class),
    Switches("Switches", TestSwitches::class),
    Tables("Tables", TestTables::class),
    Tabs("Tabs", TestTabs::class),
    TextFields("Text Fields", TestTextFields::class),
    Themes("Themes", TestThemes::class),
    ToggleButtons("Toggle Buttons", TestToggleButtons::class),
    Tooltips("Tooltips", TestTooltips::class),
    Transitions("Transitions", TestTransitions::class);
}


fun RBuilder.mainFrame(initialView: Page, onThemeSwitch: () -> Unit) {
    child(MainFrame::class) {
        attrs.onThemeSwitch = onThemeSwitch
        attrs.initialPage = initialView
    }
}
