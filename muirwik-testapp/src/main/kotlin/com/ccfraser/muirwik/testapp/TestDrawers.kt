package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.alert.MAlertSeverity
import com.ccfraser.muirwik.components.alert.mAlert
import com.ccfraser.muirwik.components.button.MIconButtonColor
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItemWithIcon
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.up
import com.ccfraser.muirwik.components.transitions.SimpleTransitionDuration
import com.ccfraser.muirwik.testapp.TestDrawers.ComponentStyles.drawer
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.Transition
import kotlinx.css.properties.ms
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onKeyDownFunction
import kotlinx.html.role
import react.*
import react.dom.br
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestDrawers : RComponent<Props, State>() {
    private var temporaryLeftOpen: Boolean = false
    private var temporaryRightOpen: Boolean = false
    private var temporaryTopOpen: Boolean = false
    private var temporaryBottomOpen: Boolean = false
    private var swipeableLeftOpen: Boolean = false
    private var swipeableRightOpen: Boolean = false
    private var swipeableTopOpen: Boolean = false
    private var swipeableBottomOpen: Boolean = false
    private var slideOutDrawerOpen = false
    private var miniDrawerOpen = false
    private var responsiveDrawerOpen = false
    private var drawerWidth = 240

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val drawer by css {
            width = 700.px
            height = 430.px
            overflow = Overflow.hidden
            position = Position.relative
            display = Display.flex
        }
    }

    override fun RBuilder.render() {
        demoContainer {
            themeContext.Consumer { theme ->
                fun temporaryDrawer() {
                    demoPanel("Temporary Drawer") {
                        mButton("Open Left", onClick = { setState { temporaryLeftOpen = true } })
                        mButton("Open Right Slow", onClick = { setState { temporaryRightOpen = true } })
                        mButton("Open Top", onClick = { setState { temporaryTopOpen = true } })
                        mButton("Open Bottom", onClick = { setState { temporaryBottomOpen = true } })

                        mDrawer(temporaryLeftOpen, onClose = { setState { temporaryLeftOpen = false } }) {
                            mailPlaceholder(false)
                        }
                        mDrawer(
                            temporaryRightOpen,
                            MDrawerAnchor.right,
                            onClose = { setState { temporaryRightOpen = false } },
                            transitionDuration = SimpleTransitionDuration(1000)
                        ) {
                            mailPlaceholder(false)
                        }
                        mDrawer(
                            temporaryTopOpen,
                            MDrawerAnchor.top,
                            onClose = { setState { temporaryTopOpen = false } }) {
                            mailPlaceholder(true)
                        }
                        mDrawer(
                            temporaryBottomOpen,
                            MDrawerAnchor.bottom,
                            onClose = { setState { temporaryBottomOpen = false } }) {
                            mailPlaceholder(true)
                        }
                    }
                }

                fun swipeableTemporaryDrawer() {
                    demoPanel("Swipeable Temporary Drawer") {
                        mButton("Open Left", onClick = { setState { swipeableLeftOpen = true } })
                        mButton("Open Right", onClick = { setState { swipeableRightOpen = true } })
                        mButton("Open Top", onClick = { setState { swipeableTopOpen = true } })
                        mButton("Open Bottom", onClick = { setState { swipeableBottomOpen = true } })

                        mSwipeableDrawer(swipeableLeftOpen,
                            onOpen = { setState { swipeableLeftOpen = true } },
                            onClose = { setState { swipeableLeftOpen = false } }) {
                            mailPlaceholder(false)
                        }
                        mSnackbar(swipeableRightOpen, autoHideDuration = 2500,
                            onClose = { _, _: MSnackbarOnCloseReason -> setState { swipeableRightOpen = false } }) {
                            mAlert("This interfers with the right scroll bar with how we have it arranged, so leaving it off for now", severity = MAlertSeverity.info)
                        }
//                            mSwipeableDrawer(swipeableRightOpen, MDrawerAnchor.right,
//                                onOpen = { setState { swipeableRightOpen = true } },
//                                onClose = { setState { swipeableRightOpen = false } }) {
//                                mailPlaceholder(false)
//                            }
                        mSwipeableDrawer(swipeableTopOpen, MDrawerAnchor.top,
                            onOpen = { setState { swipeableTopOpen = true } },
                            onClose = { setState { swipeableTopOpen = false } }) {
                            mailPlaceholder(true)
                        }
                        mSwipeableDrawer(swipeableBottomOpen, MDrawerAnchor.bottom,
                            onOpen = { setState { swipeableBottomOpen = true } },
                            onClose = { setState { swipeableBottomOpen = false } }) {
                            mailPlaceholder(true)
                        }
                    }
                }

                fun permanentDrawer1() {
                    demoPanel("Permanent drawer") {
                        css { backgroundColor = Color(theme.palette.grey.A100) }
                        styledDiv {
                            css {
                                +drawer
                                zIndex = 1
                            }

                            mAppBar(position = MAppBarPosition.absolute) {
                                mToolbar {
                                    css { marginLeft = drawerWidth.px }
                                    mToolbarTitle("Permanent drawer - Full Height Nav")
                                }
                            }

                            //TODO: Not sure about this style... seems to work though...
                            val pp: MPaperProps = jsObject { }
                            pp.asDynamic().style = js { position = "relative" }
                            mDrawer(true, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = pp) {
                                spacer()
                                mailPlaceholder(false)
                            }
                            styledDiv {
                                css {
                                    flexGrow = 1.0
                                    backgroundColor = Color(theme.palette.background.paper)
                                }
                                spacer()
                                mTypography("This is the main content area")
                            }
                        }
                    }
                }

                fun permanentDrawer2() {
                    demoPanel("Permanent drawer - Under Appbar nav") {
                        css { backgroundColor = Color(theme.palette.grey.A100) }
                        styledDiv {
                            css(drawer)

                            mAppBar(position = MAppBarPosition.absolute) {
                                css {
                                    zIndex = theme.zIndex.drawer + 1
                                }
                                mToolbar {
                                    mToolbarTitle("Permanent drawer - Under Appbar nav")
                                }
                            }

                            val pp: MPaperProps = jsObject { }
                            pp.asDynamic().style = js { position = "relative" }
                            mDrawer(true, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = pp) {
                                spacer()
                                mailPlaceholder(false)
                            }
                            styledDiv {
                                css {
                                    flexGrow = 1.0
                                    backgroundColor = Color(theme.palette.background.paper)
                                }
                                spacer()
                                mTypography("This is the main content area")
                            }
                        }
                    }
                }

                fun slideOutDrawer() {
                    demoPanel("Slideout Drawer") {
                        css { backgroundColor = Color(theme.palette.grey.A100) }
                        styledDiv {
                            css(drawer)

                            mAppBar(position = MAppBarPosition.absolute) {
                                css {
                                    position = Position.absolute
                                    transition += Transition("width", 195.ms, Timing.easeInOut, 0.ms)
//                        transition = "width 195ms cubic-bezier(0.4, 0, 0.6, 1) 0ms";
//                        if (slideOutDrawerOpen) put("width", "calc(100% - ${drawerWidth}px)");
                                    if (slideOutDrawerOpen) width = 100.pct - drawerWidth.px
                                }

                                mToolbar(disableGutters = !slideOutDrawerOpen) {
                                    if (!slideOutDrawerOpen) {
                                        mIconButton(
                                            "menu",
                                            color = MIconButtonColor.inherit,
                                            onClick = { setState { slideOutDrawerOpen = true } })
                                    }
                                    mToolbarTitle("Persistent drawer - Slideout Nav")
                                }
                            }

                            val pp: MPaperProps = jsObject { }
                            pp.asDynamic().style = js { position = "relative" }
                            mDrawer(
                                slideOutDrawerOpen,
                                MDrawerAnchor.left,
                                MDrawerVariant.persistent,
                                paperProps = pp
                            ) {
                                css {
                                    transition += Transition("left", 5000.ms, Timing.easeInOut, 0.ms)
                                }
                                styledDiv {
                                    css {
                                        display = Display.flex; alignItems = Align.center; justifyContent =
                                        JustifyContent.flexEnd; toolbarJsCssToPartialCss(theme.mixins.toolbar)
                                    }
                                    mIconButton("chevron_left", onClick = { setState { slideOutDrawerOpen = false } })
                                }
                                mDivider()
                                mailPlaceholder(false)
                            }

                            styledDiv {
                                css {
                                    flexGrow = 1.0
                                    transition += Transition("margin", 195.ms, Timing.easeIn, 0.ms)
                                    marginLeft = -drawerWidth.px
                                    if (slideOutDrawerOpen) marginLeft = 0.px
                                    backgroundColor = Color(theme.palette.background.paper)
                                }
                                spacer()
                                mTypography("This is the main content area")
                            }
                        }
                    }
                }

                fun miniDrawer() {
                    demoPanel("Mini Drawer") {
                        css { backgroundColor = Color(theme.palette.grey.A100) }
                        styledDiv {
                            css(drawer)

                            mAppBar(position = MAppBarPosition.absolute) {
                                css {
                                    transition += Transition("width", 195.ms, Timing.materialStandard, 0.ms)
                                    zIndex = theme.zIndex.drawer + 1
                                    if (miniDrawerOpen) width = 100.pct - drawerWidth.px
                                }
                                mToolbar(disableGutters = !miniDrawerOpen) {
                                    if (!miniDrawerOpen) {
                                        mIconButton(
                                            "menu",
                                            color = MIconButtonColor.inherit,
                                            onClick = { setState { miniDrawerOpen = true } })
                                    }
                                    mToolbarTitle("Mini drawer")
                                }
                            }

                            val pp: MPaperProps = jsObject { }
                            pp.asDynamic().style = js {
                                position = "relative"
                                transition = "width 195ms cubic-bezier(0.4, 0, 0.6, 1) 0ms"
                                if (!miniDrawerOpen) {
                                    overflowX = "hidden"
                                    width = 7.spacingUnits.value
                                } else {
                                    width = drawerWidth + 1
                                }
                            }
                            mDrawer(miniDrawerOpen, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = pp) {
                                styledDiv {
                                    css {
                                        display = Display.flex; alignItems = Align.center; justifyContent =
                                        JustifyContent.flexEnd; toolbarJsCssToPartialCss(theme.mixins.toolbar)
                                    }
                                    mIconButton("chevron_left", onClick = { setState { miniDrawerOpen = false } })
                                }
                                mDivider()
                                mailPlaceholder(false)
                            }

                            styledDiv {
                                css {
                                    flexGrow = 1.0
                                    backgroundColor = Color(theme.palette.background.paper)
                                }
                                spacer()
                                mTypography("This is the main content area")
                            }
                        }
                    }
                }

                // Just thought we would break these into functions to more easily separate them visually
                temporaryDrawer()
                swipeableTemporaryDrawer()
                permanentDrawer1()
                permanentDrawer2()
                slideOutDrawer()
                miniDrawer()
            }
        }
        // This is outside the container for the others as we want it to size differently
        responsiveDrawer()
    }

    private fun RBuilder.  responsiveDrawer() {
        themeContext.Consumer { theme ->
            demoPanel("Responsive Drawer") {
                css { backgroundColor = Color(theme.palette.grey.A100) }
                styledDiv {
                    css {
                        flexGrow = 1.0
                        width = 100.pct
                        height = 430.px
                        zIndex = 1
                        overflow = Overflow.hidden
                        position = Position.relative
                        display = Display.flex
                        backgroundColor = Color(theme.palette.background.paper)
                    }

                    mAppBar(position = MAppBarPosition.absolute) {
                        css {
                            position = Position.absolute
                            marginLeft = drawerWidth.px
                            media(theme.breakpoints.up(Breakpoint.md)) {
                                width = 100.pct - drawerWidth.px
                            }
                        }
                        mToolbar {
                            mHidden(mdUp = true, implementation = MHiddenImplementation.css) {
                                mIconButton(
                                    "menu",
                                    color = MIconButtonColor.inherit,
                                    onClick = { setState { responsiveDrawerOpen = true } })
                            }
                            mToolbarTitle("Responsive Drawer")
                        }
                    }

                    val pp: MPaperProps = jsObject { }
                    pp.asDynamic().style = js {
                        width = drawerWidth + 1
                        position = "relative"
                    }
                    mHidden(mdUp = true) {
                        mDrawer(
                            responsiveDrawerOpen,
                            variant = MDrawerVariant.temporary,
                            onClose = { setState { responsiveDrawerOpen = !responsiveDrawerOpen } },
                            paperProps = pp
                        ) {
                            css {
                                width = drawerWidth.px
                            }
                            mailPlaceholder(false)
                        }
                    }
                    mHidden(smDown = true, implementation = MHiddenImplementation.css) {
                        mDrawer(true, variant = MDrawerVariant.permanent, paperProps = pp) {
                            css {
                                height = 100.pc
                            }
                            spacer()
                            mailPlaceholder(false)
                        }
                    }

                    styledDiv {
                        css {
                            flexGrow = 1.0
                            backgroundColor = Color(theme.palette.background.default)
                        }
                        spacer()
                        styledDiv {
                            css {
                                padding(2.spacingUnits)
                                backgroundColor = Color(theme.palette.background.paper)
                            }
                            mTypography("This is the main content area")
                        }
                    }
                }
            }
        }
    }


    // Note about these functions... they need to be either in the RBuilder.render function above or
    // be extension functions to RBuilder as below.
    private fun RBuilder.mailPlaceholder(fullWidth: Boolean) {
        themeContext.Consumer { theme ->
            div {
                attrs.role = "button"
                attrs.onClickFunction = { setState { temporaryLeftOpen = false }}
                attrs.onKeyDownFunction = { setState { temporaryLeftOpen = false }}
            }
            mList {
                css {
                    backgroundColor = Color(theme.palette.background.paper)
                    width = if (fullWidth) LinearDimension.auto else drawerWidth.px
//                style = js { width = if (fullWidth) "auto" else drawerWidth; backgroundColor = "white" }
                }
                mListItemWithIcon("move_to_inbox", "Inbox", divider = false)
                mListItemWithIcon("star", "Stared", divider = false)
                mListItemWithIcon("send", "Send mail", divider = true)
                mListItemWithIcon("mail", "All mail", divider = false)
                mListItemWithIcon("delete", "Trash", divider = false)
                mListItemWithIcon("error", "Spam", divider = false)
            }
        }
    }

    private fun RBuilder.spacer() {
        themeContext.Consumer { theme ->

            // This puts in a spacer to get below the AppBar.
            styledDiv {
                css {
                    toolbarJsCssToPartialCss(theme.mixins.toolbar)
                }
            }
            mDivider { }
        }
    }

}
