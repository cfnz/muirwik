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
    private var leftOpen: Boolean = false
    private var rightOpen: Boolean = false
    private var topOpen: Boolean = false
    private var bottomOpen: Boolean = false
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
        fun fullPageDrawer() {
            div {
                mButton("Open Left", onClick = { setState { leftOpen = true } })
                mButton("Open Right Slow", onClick = { setState { rightOpen = true } })
                mButton("Open Top", onClick = { setState { topOpen = true } })
                mButton("Open Bottom", onClick = { setState { bottomOpen = true } })

                mDrawer(leftOpen, onClose = { setState { leftOpen = false } }) {
                    mailPlaceholder(false)
                }
                mDrawer(rightOpen, MDrawerAnchor.Right, onClose = { setState { rightOpen = false } }, transitionDuration = SimpleTransitionDuration(1000)) {
                    mailPlaceholder(false)
                }
                mDrawer(topOpen, MDrawerAnchor.Top, onClose = { setState { topOpen = false } }) {
                    mailPlaceholder(true)
                }
                mDrawer(bottomOpen, MDrawerAnchor.Bottom, onClose = { setState { bottomOpen = false } }) {
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

                mAppBar(position = MAppBarPosition.Absolute) {
                    mToolbar {
                        css { marginLeft = drawerWidth.px }
                        mToolbarTitle("Permanent drawer - Full Height Nav")
                    }
                }

                //TODO: Not sure about this style... seems to work though...
                val pp: MPaperProps = jsObject {  }
                pp.asDynamic().style = js { position = "relative" }
                mDrawer(true, MDrawerAnchor.Left, MDrawerVariant.Permanent, paperProps = pp) {
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

                mAppBar(position = MAppBarPosition.Absolute) {
                    css {
                        zIndex = currentTheme.zIndex.drawer + 1
                    }
                    mToolbar {
                        mToolbarTitle("Permanent drawer - Under Appbar nav")
                    }
                }

                val pp: MPaperProps = jsObject { }
                pp.asDynamic().style = js { position = "relative" }
                mDrawer(true, MDrawerAnchor.Left, MDrawerVariant.Permanent, paperProps = pp) {
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

                mAppBar(position = MAppBarPosition.Absolute) {
                    css {
                        position = Position.absolute
                        transition += Transition("width", 195.ms, Timing.easeInOut, 0.ms)
//                        transition = "width 195ms cubic-bezier(0.4, 0, 0.6, 1) 0ms";
//                        if (slideOutDrawerOpen) put("width", "calc(100% - ${drawerWidth}px)");
                        if (slideOutDrawerOpen) width = 100.pct - drawerWidth.px
                    }

                    mToolbar(disableGutters = !slideOutDrawerOpen) {
                        if (!slideOutDrawerOpen) {
                            mIconButton("menu", color = MColor.Inherit, onClick = { setState { slideOutDrawerOpen = true } })
                        }
                        mToolbarTitle("Persistent drawer - Slideout Nav")
                    }
                }

                val pp: MPaperProps = jsObject { }
                pp.asDynamic().style = js { position = "relative" }
                mDrawer(slideOutDrawerOpen, MDrawerAnchor.Left, MDrawerVariant.Persistent, paperProps = pp) {
                    css {
                        transition += Transition("left", 5000.ms, Timing.easeIn, 0.ms)
                    }
                    div {
                        // TODO: Could convert this to type safe css
                        attrs.jsStyle = js { display = "flex"; alignItems = "center"; justifyContent = "flex-end"; height = 64 }
                        mIconButton("chevron_left", onClick = { setState { slideOutDrawerOpen = false } })
                    }
                    mDivider()
                    mailPlaceholder(false)
                }

                div {
                    // TODO: Could convert this to type safe css
                    attrs.jsStyle = js {
                        flexGrow = 1
                        transition = "margin 195ms cubic-bezier(0.4, 0, 0.6, 1) 0ms"
                        marginLeft = -drawerWidth
                        if (slideOutDrawerOpen) marginLeft = 0
                    }
                    spacer()
                    mTypography("This is the main content area")
                }
            }
        }

        fun miniDrawer() {
            styledDiv {
                css(drawer)

                mAppBar(position = MAppBarPosition.Absolute) {
                    css {
                        transition += Transition("width", 195.ms, Timing.materialStandard, 0.ms)
                        zIndex = 1201
                        if (miniDrawerOpen) width = 100.pct - drawerWidth.px
                    }
                    mToolbar(disableGutters = !miniDrawerOpen) {
                        if (!miniDrawerOpen) {
                            mIconButton("menu", color = MColor.Inherit, onClick = { setState { miniDrawerOpen = true } })
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
                mDrawer(miniDrawerOpen, MDrawerAnchor.Left, MDrawerVariant.Permanent, paperProps = pp) {
                    div {
                        attrs.jsStyle = js { display = "flex"; alignItems = "center"; justifyContent = "flex-end"; height = 64 }
                        mIconButton("chevron_left", onClick = { setState { miniDrawerOpen = false } })
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
//                  attrs.jsStyle = js { width = "100%"; height = 430; overflow = "hidden"; position = "relative"; display = "flex" }
                css {
                    flexGrow = 1.0
                    width = 100.pct
                    height = 430.px
                    zIndex = 1
                    overflow = Overflow.hidden
                    position = Position.relative
                    display = Display.flex
                }

                mAppBar(position = MAppBarPosition.Absolute) {
                    css {
                        position = Position.absolute
                        marginLeft = drawerWidth.px
                        media(currentTheme.breakpoints.up(Breakpoint.md)) {
                            width = 100.pct - drawerWidth.px
//                            width: "calc(100% - ${drawerWidth}px)");
                        }
                    }
                    mToolbar {
                        mHidden(mdUp = true, implementation = MHiddenImplementation.css) {
                            mIconButton("menu", color = MColor.Inherit, onClick = { setState { responsiveDrawerOpen = true } })
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
                    mDrawer(responsiveDrawerOpen, variant = MDrawerVariant.Temporary, onClose = { setState {responsiveDrawerOpen = !responsiveDrawerOpen} }, paperProps = pp) {
                        css { width = drawerWidth.px }
                        mailPlaceholder(false)
                    }
                }
                mHidden(smDown = true, implementation = MHiddenImplementation.css) {
                    mDrawer(true, variant = MDrawerVariant.Permanent, paperProps = pp) {
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
        fullPageDrawer()
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
            attrs.onClickFunction = { setState { leftOpen = false } }
            attrs.onKeyDownFunction = { setState { leftOpen = false } }
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
