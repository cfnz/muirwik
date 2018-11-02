package com.ccfraser.muirwik.app

import com.ccfraser.muirwik.app.TestDrawers.ComponentStyles.drawer
import com.ccfraser.muirwik.wrapper.*
import com.ccfraser.muirwik.wrapper.list.mList
import com.ccfraser.muirwik.wrapper.list.mListItem
import com.ccfraser.muirwik.wrapper.styles.*
import com.ccfraser.muirwik.wrapper.transitions.SimpleTransitionDuration
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.css.Color
import kotlinx.css.properties.Timing
import kotlinx.css.properties.Transition
import kotlinx.css.properties.ms
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onKeyDownFunction
import kotlinx.html.role
import react.RBuilder
import react.RComponent
import react.RProps
import react.dom.br
import react.dom.div
import react.dom.jsStyle
import react.setState
import styled.StyleSheet
import styled.css
import styled.styledDiv

class TestDrawers : RComponent<RProps, TestOptionControls.MyTestState>() {
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
        val spacer by css {
            toolbarJsCssToPartialCss(currentTheme.mixins.toolbar)
        }
    }

    override fun RBuilder.render() {
        fun temporaryDrawer() {
            mTypography("Temporary Drawer", MTypographyVariant.h4)
            div {
                mButton("Open Left", onClick = { setState { temporaryLeftOpen = true }})
                mButton("Open Right Slow", onClick = { setState { temporaryRightOpen = true }})
                mButton("Open Top", onClick = { setState { temporaryTopOpen = true }})
                mButton("Open Bottom", onClick = { setState { temporaryBottomOpen = true }})

                mDrawer(temporaryLeftOpen, onClose = { setState { temporaryLeftOpen = false }}) {
                    mailPlaceholder(false)
                }
                mDrawer(temporaryRightOpen, MDrawerAnchor.right, onClose = { setState { temporaryRightOpen = false }}, transitionDuration = SimpleTransitionDuration(1000)) {
                    mailPlaceholder(false)
                }
                mDrawer(temporaryTopOpen, MDrawerAnchor.top, onClose = { setState { temporaryTopOpen = false }}) {
                    mailPlaceholder(true)
                }
                mDrawer(temporaryBottomOpen, MDrawerAnchor.bottom, onClose = { setState { temporaryBottomOpen = false }}) {
                    mailPlaceholder(true)
                }
            }
        }

        fun swipeableTemporaryDrawer() {
            mTypography("Swipeable Temporary Drawer", MTypographyVariant.h4)
            div {
                mButton("Open Left", onClick = { setState { swipeableLeftOpen = true }})
                mButton("Open Right", onClick = { setState { swipeableRightOpen = true }})
                mButton("Open Top", onClick = { setState { swipeableTopOpen = true }})
                mButton("Open Bottom", onClick = { setState { swipeableBottomOpen = true }})

                mSwipeableDrawer(swipeableLeftOpen,
                        onOpen = { setState { swipeableLeftOpen = true }},
                        onClose = { setState { swipeableLeftOpen = false }}) {
                    mailPlaceholder(false)
                }
                mSwipeableDrawer(swipeableRightOpen, MDrawerAnchor.right,
                        onOpen = { setState { swipeableRightOpen = true }},
                        onClose = { setState { swipeableRightOpen = false }}) {
                    mailPlaceholder(false)
                }
                mSwipeableDrawer(swipeableTopOpen, MDrawerAnchor.top,
                        onOpen = { setState { swipeableTopOpen = true }},
                        onClose = { setState { swipeableTopOpen = false }}) {
                    mailPlaceholder(true)
                }
                mSwipeableDrawer(swipeableBottomOpen, MDrawerAnchor.bottom,
                        onOpen = { setState { swipeableBottomOpen = true }},
                        onClose = { setState { swipeableBottomOpen = false }}) {
                    mailPlaceholder(true)
                }
            }
        }

        fun permanentDrawer1() {
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
                val pp: MPaperProps = jsObject {  }
                pp.asDynamic().style = js { position = "relative" }
                mDrawer(true, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = pp) {
                    spacer()
                    mailPlaceholder(false)
                }
                styledDiv {
                    css {
                        flexGrow = 1.0
                    }
                    spacer()
                    mTypography("This is the main content area")
                }
            }
        }

        fun permanentDrawer2() {
            styledDiv {
                css(drawer)

                mAppBar(position = MAppBarPosition.absolute) {
                    css {
                        zIndex = currentTheme.zIndex.drawer + 1
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
                    }
                    spacer()
                    mTypography("This is the main content area")
                }
            }
        }

        fun slideOutDrawer() {
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
                            mIconButton("menu", color = MColor.inherit, onClick = { setState { slideOutDrawerOpen = true }})
                        }
                        mToolbarTitle("Persistent drawer - Slideout Nav")
                    }
                }

                val pp: MPaperProps = jsObject { }
                pp.asDynamic().style = js { position = "relative" }
                mDrawer(slideOutDrawerOpen, MDrawerAnchor.left, MDrawerVariant.persistent, paperProps = pp) {
                    css {
                        transition += Transition("left", 5000.ms, Timing.easeInOut, 0.ms)
                    }
//                    div {
//                        attrs.jsStyle = js { display = "flex"; alignItems = "center"; justifyContent = "flex-end"; height = 64 }
//                        mIconButton("chevron_left", onClick = { setState { slideOutDrawerOpen = false }})
//                    }
                    styledDiv {
                        css { display = Display.flex; alignItems = Align.center; justifyContent = JustifyContent.flexEnd; height = 64.px }
                        mIconButton("chevron_left", onClick = { setState { slideOutDrawerOpen = false }})
                    }
                    mDivider()
                    mailPlaceholder(false)
                }

                styledDiv {
//                    attrs.jsStyle = js {
//                        flexGrow = 1
//                        transition = "margin 195ms cubic-bezier(0.4, 0, 0.6, 1) 0ms"
//                        marginLeft = -drawerWidth
//                        if (slideOutDrawerOpen) marginLeft = 0
//                    }
                    css {
                        flexGrow = 1.0
                        transition += Transition("margin", 195.ms, Timing.easeIn, 0.ms)
                        marginLeft = -drawerWidth.px
                        if (slideOutDrawerOpen) marginLeft = 0.px
                    }
                    spacer()
                    mTypography("This is the main content area")
                }
            }
        }

        fun miniDrawer() {
            styledDiv {
                css(drawer)

                mAppBar(position = MAppBarPosition.absolute) {
                    css {
                        transition += Transition("width", 195.ms, Timing.materialStandard, 0.ms)
                        zIndex = 1201
                        if (miniDrawerOpen) width = 100.pct - drawerWidth.px
                    }
                    mToolbar(disableGutters = !miniDrawerOpen) {
                        if (!miniDrawerOpen) {
                            mIconButton("menu", color = MColor.inherit, onClick = { setState { miniDrawerOpen = true }})
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
                        width = 9.spacingUnits.value
                    } else {
                        width = drawerWidth
                    }
                }
                mDrawer(miniDrawerOpen, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = pp) {
                    div {
                        attrs.jsStyle = js { display = "flex"; alignItems = "center"; justifyContent = "flex-end"; height = 64 }
                        mIconButton("chevron_left", onClick = { setState { miniDrawerOpen = false }})
                    }
                    mDivider()
                    mailPlaceholder(false)
                }

                div {
                    attrs.jsStyle = js {
                        flexGrow = 1
                    }
                    spacer()
                    mTypography("This is the main content area")
                }
            }
        }

        fun responsiveDrawer() {
            styledDiv {
                css {
                    flexGrow = 1.0
                    width = 100.pct
                    height = 430.px
                    zIndex = 1
                    overflow = Overflow.hidden
                    position = Position.relative
                    display = Display.flex
                }

                mAppBar(position = MAppBarPosition.absolute) {
                    css {
                        position = Position.absolute
                        marginLeft = drawerWidth.px
                        media(currentTheme.breakpoints.up(Breakpoint.md)) {
                            width = 100.pct - drawerWidth.px
                        }
                    }
                    mToolbar {
                        mHidden(mdUp = true, implementation = MHiddenImplementation.css) {
                            mIconButton("menu", color = MColor.inherit, onClick = { setState { responsiveDrawerOpen = true }})
                        }
                        mToolbarTitle("Responsive Drawer")
                    }
                }

                val pp: MPaperProps = jsObject { }
                pp.asDynamic().style = js {
                    width = drawerWidth
                    position = "relative"
                }
                mHidden(mdUp = true) {
                    mDrawer(responsiveDrawerOpen, variant = MDrawerVariant.temporary, onClose = { setState {responsiveDrawerOpen = !responsiveDrawerOpen}}, paperProps = pp) {
                        css { width = drawerWidth.px }
                        mailPlaceholder(false)
                    }
                }
                mHidden(smDown = true, implementation = MHiddenImplementation.css) {
                    mDrawer(true, variant = MDrawerVariant.permanent, paperProps = pp) {
                        spacer()
                        mailPlaceholder(false)
                    }
                }

                styledDiv {
                    css {
                        flexGrow = 1.0
                        backgroundColor = Color(currentTheme.palette.background.default)
                    }
                    spacer()
                    styledDiv {
                        css { padding(2.spacingUnits) }
                        mTypography("This is the main content area")
                        mTypography("Breakpoint info up(lg) ${currentTheme.breakpoints.up(Breakpoint.lg)}")
                        mTypography("Breakpoint info up(md) ${currentTheme.breakpoints.up(Breakpoint.md)}")
                        mTypography("Breakpoint info up(sm) ${currentTheme.breakpoints.up(Breakpoint.sm)}")
                        mTypography("Breakpoint info dn(lg) ${currentTheme.breakpoints.down(Breakpoint.lg)}")
                        mTypography("Breakpoint info dn(md) ${currentTheme.breakpoints.down(Breakpoint.md)}")
                        mTypography("Breakpoint info dn(sm) ${currentTheme.breakpoints.down(Breakpoint.sm)}")
                        mTypography("Breakpoint info bt(sm and md) ${currentTheme.breakpoints.between(Breakpoint.sm, Breakpoint.md)}")
                        mTypography("Breakpoint info only sm ${currentTheme.breakpoints.only(Breakpoint.sm)}")
                        mTypography("Breakpoint info width sm ${currentTheme.breakpoints.width(Breakpoint.sm)}")
                    }
                }
            }
        }

        // Just thought we would break these into functions to more easily separate them visually
        temporaryDrawer()
        br {}
        br {}
        swipeableTemporaryDrawer()
        br {}
        br {}
        permanentDrawer1()
        br {}
        br {}
        permanentDrawer2()
        br {}
        br {}
        slideOutDrawer()
        br {}
        br {}
        miniDrawer()
        br {}
        br {}
        responsiveDrawer()
    }

    // Note about these functions... they need to be either in the RBuilder.render function above or
    // be extension functions to RBuilder as below.
    fun RBuilder.mailPlaceholder(fullWidth: Boolean) {
        div {
            attrs.role = "button"
            attrs.onClickFunction = { setState { temporaryLeftOpen = false }}
            attrs.onKeyDownFunction = { setState { temporaryLeftOpen = false }}
        }
        mList {
            css {
                backgroundColor = Color(currentTheme.palette.background.paper)
                width = if (fullWidth) LinearDimension.auto else drawerWidth.px
//                style = js { width = if (fullWidth) "auto" else drawerWidth; backgroundColor = "white" }
            }
            mListItem("Inbox", iconName = "move_to_inbox", divider = false)
            mListItem("Stared", iconName = "star", divider = false)
            mListItem("Send mail", iconName = "send", divider = true)
            mListItem("All mail", iconName = "mail", divider = false)
            mListItem("Trash", iconName = "delete", divider = false)
            mListItem("Spam", iconName = "error", divider = false)
        }
    }

    fun RBuilder.spacer() {
        // This puts in a spacer to get below the AppBar.
        styledDiv {
            css(ComponentStyles.spacer)
        }
        mDivider {  }
    }

}


fun RBuilder.testDrawers() = child(TestDrawers::class) {}
