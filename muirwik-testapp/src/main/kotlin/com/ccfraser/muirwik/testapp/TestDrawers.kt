package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.up
import com.ccfraser.muirwik.components.utils.toolbarJsCssToPartialCss
import com.ccfraser.muirwik.testapp.TestDrawers.ComponentStyles.drawer
import kotlinext.js.js
import kotlinext.js.jso
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.Transition
import kotlinx.css.properties.ms
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onKeyDownFunction
import kotlinx.html.role
import react.*
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
                        button("Open Left") { attrs.onClick = { setState { temporaryLeftOpen = true } } }
                        button("Open Right Slow") { attrs.onClick = { setState { temporaryRightOpen = true } } }
                        button("Open Top") { attrs.onClick = { setState { temporaryTopOpen = true } } }
                        button("Open Bottom") { attrs.onClick = { setState { temporaryBottomOpen = true } } }

                        drawer(temporaryLeftOpen) {
                            attrs.onClose = { setState { temporaryLeftOpen = false } }
                            mailPlaceholder(false)
                        }
                        drawer(temporaryRightOpen) {
                            attrs.anchor = DrawerAnchor.right
                            attrs.onClose = { setState { temporaryRightOpen = false } }
                            attrs.transitionDuration = SimpleTransitionDuration(1000)
                            mailPlaceholder(false)
                        }
                        drawer(temporaryTopOpen) {
                            attrs.anchor = DrawerAnchor.top
                            attrs.onClose = { setState { temporaryTopOpen = false } }
                            mailPlaceholder(true)
                        }
                        drawer(temporaryBottomOpen) {
                            attrs.anchor = DrawerAnchor.bottom
                            attrs.onClose = { setState { temporaryBottomOpen = false } }
                            mailPlaceholder(true)
                        }
                    }
                }

                fun swipeableTemporaryDrawer() {
                    demoPanel("Swipeable Temporary Drawer") {
                        button("Open Left") { attrs.onClick = { setState { swipeableLeftOpen = true } } }
                        button("Open Right") { attrs.onClick = { setState { swipeableRightOpen = true } } }
                        button("Open Top") { attrs.onClick = { setState { swipeableTopOpen = true } } }
                        button("Open Bottom") { attrs.onClick = { setState { swipeableBottomOpen = true } } }

                        swipeableDrawer(
                            swipeableLeftOpen,
                            onOpen = { setState { swipeableLeftOpen = true } },
                            onClose = { setState { swipeableLeftOpen = false } }
                        ) {
                            mailPlaceholder(false)
                        }
                        snackbar(swipeableRightOpen) {
                            attrs.autoHideDuration = 2500
                            attrs.onClose = { _, _: SnackbarOnCloseReason -> setState { swipeableRightOpen = false } }
                            alert(
                                "This interferes with the right scroll bar with how we have it arranged, so leaving it off for now",
                                severity = AlertSeverity.info
                            )
                        }
                        swipeableDrawer(swipeableTopOpen,
                            onOpen = { setState { swipeableTopOpen = true } },
                            onClose = { setState { swipeableTopOpen = false } },
                            DrawerAnchor.top
                        ) {
                            mailPlaceholder(true)
                        }
                        swipeableDrawer(swipeableBottomOpen,
                            onOpen = { setState { swipeableBottomOpen = true } },
                            onClose = { setState { swipeableBottomOpen = false } },
                            DrawerAnchor.bottom
                        ) {
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

                            appBar(AppBarPosition.absolute) {
                                toolbar {
                                    css { marginLeft = drawerWidth.px }
                                    toolbarTitle("Permanent drawer - Full Height Nav")
                                }
                            }

                            //TODO: Not sure about this style... seems to work though...
                            val pp: PaperProps = jso { }
                            pp.asDynamic().style = js { position = "relative" }
                            drawer(true, variant = DrawerVariant.permanent) {
                                attrs.anchor = DrawerAnchor.left
                                attrs.paperProps = pp
                                spacer()
                                mailPlaceholder(false)
                            }
                            styledDiv {
                                css {
                                    flexGrow = 1.0
                                    backgroundColor = Color(theme.palette.background.paper)
                                }
                                spacer()
                                typography("This is the main content area")
                            }
                        }
                    }
                }

                fun permanentDrawer2() {
                    demoPanel("Permanent drawer - Under Appbar nav") {
                        css { backgroundColor = Color(theme.palette.grey.A100) }
                        styledDiv {
                            css(drawer)

                            appBar(AppBarPosition.absolute) {
                                css {
                                    zIndex = theme.zIndex.drawer + 1
                                }
                                toolbar {
                                    toolbarTitle("Permanent drawer - Under Appbar nav")
                                }
                            }

                            val pp: PaperProps = jso { }
                            pp.asDynamic().style = js { position = "relative" }
                            drawer(true, variant = DrawerVariant.permanent) {
                                attrs.anchor = DrawerAnchor.left
                                attrs.paperProps = pp
                                spacer()
                                mailPlaceholder(false)
                            }
                            styledDiv {
                                css {
                                    flexGrow = 1.0
                                    backgroundColor = Color(theme.palette.background.paper)
                                }
                                spacer()
                                typography("This is the main content area")
                            }
                        }
                    }
                }

                fun slideOutDrawer() {
                    demoPanel("Slideout Drawer") {
                        css { backgroundColor = Color(theme.palette.grey.A100) }
                        styledDiv {
                            css(drawer)

                            appBar(AppBarPosition.absolute) {
                                css {
                                    position = Position.absolute
                                    transition += Transition("width", 195.ms, Timing.easeInOut, 0.ms)
//                        transition = "width 195ms cubic-bezier(0.4, 0, 0.6, 1) 0ms";
//                        if (slideOutDrawerOpen) put("width", "calc(100% - ${drawerWidth}px)");
                                    if (slideOutDrawerOpen) width = 100.pct - drawerWidth.px
                                }

                                toolbar {
                                    attrs.disableGutters = !slideOutDrawerOpen
                                    if (!slideOutDrawerOpen) {
                                        iconButton("menu", IconButtonColor.inherit) {
                                            attrs.onClick = { setState { slideOutDrawerOpen = true } }
                                        }
                                    }
                                    toolbarTitle("Persistent drawer - Slideout Nav")
                                }
                            }

                            val pp: PaperProps = jso { }
                            pp.asDynamic().style = js { position = "relative" }
                            drawer(slideOutDrawerOpen, DrawerAnchor.left, DrawerVariant.persistent) {
                                attrs.paperProps = pp
                                css {
                                    transition += Transition("left", 5000.ms, Timing.easeInOut, 0.ms)
                                }
                                styledDiv {
                                    css {
                                        display = Display.flex; alignItems = Align.center; justifyContent =
                                        JustifyContent.flexEnd; toolbarJsCssToPartialCss(theme.mixins.toolbar)
                                    }
                                    iconButton("chevron_left") { attrs.onClick = { setState { slideOutDrawerOpen = false } } }
                                }
                                divider()
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
                                typography("This is the main content area")
                            }
                        }
                    }
                }

                fun miniDrawer() {
                    demoPanel("Mini Drawer") {
                        css { backgroundColor = Color(theme.palette.grey.A100) }
                        styledDiv {
                            css(drawer)

                            appBar(AppBarPosition.absolute) {
                                css {
                                    transition += Transition("width", 195.ms, Timing.materialStandard, 0.ms)
                                    zIndex = theme.zIndex.drawer + 1
                                    if (miniDrawerOpen) width = 100.pct - drawerWidth.px
                                }
                                toolbar {
                                    attrs.disableGutters = !miniDrawerOpen
                                    if (!miniDrawerOpen) {
                                        iconButton("menu", IconButtonColor.inherit) {
                                            attrs.onClick = { setState { miniDrawerOpen = true } }
                                        }
                                    }
                                    toolbarTitle("Mini drawer")
                                }
                            }

                            val pp: PaperProps = jso { }
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
                            drawer(miniDrawerOpen, DrawerAnchor.left, DrawerVariant.permanent) {
                                attrs.paperProps = pp
                                styledDiv {
                                    css {
                                        display = Display.flex; alignItems = Align.center; justifyContent =
                                        JustifyContent.flexEnd; toolbarJsCssToPartialCss(theme.mixins.toolbar)
                                    }
                                    iconButton("chevron_left") { attrs.onClick = { setState { miniDrawerOpen = false } } }
                                }
                                divider()
                                mailPlaceholder(false)
                            }

                            styledDiv {
                                css {
                                    flexGrow = 1.0
                                    backgroundColor = Color(theme.palette.background.paper)
                                }
                                spacer()
                                typography("This is the main content area")
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

                    appBar(position = AppBarPosition.absolute) {
                        css {
                            position = Position.absolute
                            marginLeft = drawerWidth.px
                            media(theme.breakpoints.up(Breakpoint.md)) {
                                width = 100.pct - drawerWidth.px
                            }
                        }
                        toolbar {
                            hidden {
                                attrs.mdUp = true
                                attrs.implementation = HiddenImplementation.css
                                iconButton("menu", color = IconButtonColor.inherit) {
                                    attrs.onClick = { setState { responsiveDrawerOpen = true } }
                                }
                            }
                            toolbarTitle("Responsive Drawer")
                        }
                    }

                    val pp: PaperProps = jso { }
                    pp.asDynamic().style = js {
                        width = drawerWidth + 1
                        position = "relative"
                    }
                    hidden {
                        attrs.mdUp = true
                        drawer(responsiveDrawerOpen, variant = DrawerVariant.temporary) {
                            attrs.onClose = { setState { responsiveDrawerOpen = !responsiveDrawerOpen } }
                            attrs.paperProps = pp
                            css {
                                width = drawerWidth.px
                            }
                            mailPlaceholder(false)
                        }
                    }
                    hidden {
                        attrs.smDown = true
                        attrs.implementation = HiddenImplementation.css
                        drawer(true, variant = DrawerVariant.permanent) {
                            attrs.paperProps = pp
                            css {
                                height = 100.pc
                            }
                            spacer()
                            mailPlaceholder(false)
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
                                typography("This is the main content area")
                            }
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
            list {
                css {
                    backgroundColor = Color(theme.palette.background.paper)
                    width = if (fullWidth) LinearDimension.auto else drawerWidth.px
//                style = js { width = if (fullWidth) "auto" else drawerWidth; backgroundColor = "white" }
                }
                listItemButtonWithIcon("move_to_inbox", "Inbox")
                listItemButtonWithIcon("star", "Stared")
                listItemButtonWithIcon("send", "Send mail")
                listItemButtonWithIcon("mail", "All mail")
                listItemButtonWithIcon("delete", "Trash")
                listItemButtonWithIcon("error", "Spam")
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
            divider()
        }
    }

}
